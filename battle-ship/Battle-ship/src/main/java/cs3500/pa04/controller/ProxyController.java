package cs3500.pa04.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa04.json.FleetJson;
import cs3500.pa04.json.JoinResponse;
import cs3500.pa04.json.JsonUtils;
import cs3500.pa04.json.MessageJson;
import cs3500.pa04.json.ShipAdapter;
import cs3500.pa04.json.VolleyJson;
import cs3500.pa04.model.AbstractPlayer;
import cs3500.pa04.model.AiShotCalculator;
import cs3500.pa04.model.Coord;
import cs3500.pa04.model.GameBoard;
import cs3500.pa04.model.Ship;
import cs3500.pa04.model.ShipType;
import cs3500.pa04.viewer.Viewer;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class uses the Proxy Pattern to talk to the Server and dispatch methods to the Player.
 */
public class ProxyController {

  private final Socket server;
  private final InputStream in;
  private final PrintStream out;
  private final AbstractPlayer player;
  private final Viewer viewer;
  private final ObjectMapper mapper = new ObjectMapper();

  private static final JsonNode VOID_RESPONSE =
      new ObjectMapper().getNodeFactory().textNode("void");

  /**
   * Construct an instance of a ProxyPlayer.
   *
   * @param server the socket connection to the server
   * @param player the instance of the player
   * @param viewer the instance of the viewer
   * @throws IOException if
   */
  public ProxyController(Socket server, AbstractPlayer player, Viewer viewer) throws IOException {
    this.server = server;
    this.in = server.getInputStream();
    this.out = new PrintStream(server.getOutputStream());
    this.player = player;
    this.viewer = viewer;
  }


  /**
   * Listens for messages from the server as JSON in the format of a MessageJSON. When a complete
   * message is sent by the server, the message is parsed and then delegated to the corresponding
   * helper method for each message. This method stops when the connection to the server is closed
   * or an IOException is thrown from parsing malformed JSON.
   */
  public void run() {
    try {
      JsonParser parser = this.mapper.getFactory().createParser(this.in);

      while (!this.server.isClosed()) {
        MessageJson message = parser.readValueAs(MessageJson.class);
        delegateMessage(message);
      }
    } catch (IOException e) {
      // server disconnected
    }
  }


  /**
   * Determines the type of request the server has sent and delegates to the
   * corresponding helper method with the message arguments.
   *
   * @param message the MessageJSON used to determine what the server has sent
   */
  private void delegateMessage(MessageJson message) {
    String name = message.messageName();
    JsonNode arguments = message.arguments();

    switch (name) {
      case "join" -> handleJoin();
      case "setup" -> handleSetup(arguments);
      case "take-shots" -> handleTakeShots();
      case "report-damage" -> handleReportDamage(arguments);
      case "successful-hits" -> handleSuccessfulHits(arguments);
      case "end-game" -> handleEndGame(arguments);
      default -> throw new IllegalStateException("Invalid message name");
    }
  }

  private void handleJoin() {
    JoinResponse joinJson = new JoinResponse(player.name(), "SINGLE");
    MessageJson msg = new MessageJson("join", JsonUtils.serializeRecord(joinJson));
    this.out.println(JsonUtils.serializeRecord(msg));
  }

  private void handleSetup(JsonNode arguments) {
    int height = arguments.get("height").asInt();
    int width = arguments.get("width").asInt();
    HashMap<ShipType, Integer> map = new HashMap<>();
    map.put(ShipType.CARRIER, arguments.get("fleet-spec").get("CARRIER").asInt());
    map.put(ShipType.BATTLESHIP, arguments.get("fleet-spec").get("BATTLESHIP").asInt());
    map.put(ShipType.DESTROYER, arguments.get("fleet-spec").get("DESTROYER").asInt());
    map.put(ShipType.SUB, arguments.get("fleet-spec").get("SUBMARINE").asInt());
    Coord size = new Coord(height, width);
    player.setBoard(new GameBoard(size));
    player.setShotCalculator(new AiShotCalculator(size));

    List<Ship> ships = player.setup(height, width, map);
    List<ShipAdapter> adaptedShips = new ArrayList<>();
    for (Ship s : ships) {
      ShipAdapter adaptedShip = new ShipAdapter(s);
      adaptedShips.add(adaptedShip);
    }
    FleetJson fleet = new FleetJson(adaptedShips);
    MessageJson msg = new MessageJson("setup", JsonUtils.serializeRecord(fleet));
    this.out.println(JsonUtils.serializeRecord(msg));
  }

  private void handleTakeShots() {
    viewer.showBoard(player.getBoard());
    viewer.showMsg("+".repeat(50));
    List<Coord> shots = player.takeShots();
    JsonNode volleyJson = JsonUtils.serializeRecord(new VolleyJson(shots));
    MessageJson msg = new MessageJson("take-shots", volleyJson);
    this.out.println(JsonUtils.serializeRecord(msg));
  }

  private void handleReportDamage(JsonNode arguments) {
    VolleyJson volley = this.mapper.convertValue(arguments, VolleyJson.class);
    List<Coord> opponentsShots = volley.getShots();
    List<Coord> damage = player.reportDamage(opponentsShots);

    JsonNode volleyJson = JsonUtils.serializeRecord(new VolleyJson(damage));
    MessageJson msg = new MessageJson("report-damage", volleyJson);
    this.out.println(JsonUtils.serializeRecord(msg));
  }

  private void handleSuccessfulHits(JsonNode arguments) {
    VolleyJson volley = this.mapper.convertValue(arguments, VolleyJson.class);
    List<Coord> hits = volley.getShots();
    player.successfulHits(hits);

    MessageJson msg = new MessageJson("successful-hits", mapper.createObjectNode());
    this.out.println(JsonUtils.serializeRecord(msg));
  }

  private void handleEndGame(JsonNode arguments) {
    String reason = arguments.get("reason").asText();
    System.out.println(reason);
    MessageJson msg = new MessageJson("end-game", mapper.createObjectNode());
    this.out.println(JsonUtils.serializeRecord(msg));
    try {
      server.close();
    } catch (IOException e) {
      System.err.println("Couldn't close the socket");;
    }
  }

}
