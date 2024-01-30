package cs3500.pa04.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa04.json.FleetJson;
import cs3500.pa04.json.MessageJson;
import cs3500.pa04.model.AbstractPlayer;
import cs3500.pa04.model.AiPlayer;
import cs3500.pa04.model.AiShotCalculator;
import cs3500.pa04.model.Coord;
import cs3500.pa04.model.GameBoard;
import cs3500.pa04.model.ShipType;
import cs3500.pa04.viewer.TerminalViewer;
import cs3500.pa04.viewer.Viewer;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProxyControllerTest {
  private ByteArrayOutputStream logger;
  private ObjectMapper objMapper;
  private Viewer viewer;
  private AbstractPlayer player;
  private ProxyController controller;

  @BeforeEach
  void setUp() {
    Scanner sc = new Scanner(new InputStreamReader(System.in));
    objMapper = new ObjectMapper();
    viewer = new TerminalViewer(sc);
    player = new AiPlayer();
    logger = new ByteArrayOutputStream(2048);
    assertEquals("", logger.toString(StandardCharsets.UTF_8));
  }

  private void arrangeGame() {
    // Create a valid game state for testing calls from the server
    Map<ShipType, Integer> ships = new HashMap<>();
    ships.put(ShipType.CARRIER, 1);
    ships.put(ShipType.BATTLESHIP, 1);
    ships.put(ShipType.DESTROYER, 1);
    ships.put(ShipType.SUB, 1);
    Coord size = new Coord(6, 6);  // Assuming board size is 6x6
    player.setBoard(new GameBoard(size));
    player.setShotCalculator(new AiShotCalculator(size));
    player.setup(6, 6, ships);
  }

  @Test
  void testSetUp() {
    String setUpMsg = "{\n"
        + "\t\"method-name\": \"setup\",\n"
        + "\t\"arguments\": {\n"
        + "\t\t\"width\": 10,\n"
        + "\t\t\"height\": 10,\n"
        + "\t\t\"fleet-spec\": {\n"
        + "\t\t\t\"CARRIER\": 1,\n"
        + "\t\t\t\"BATTLESHIP\": 1,\n"
        + "\t\t  \"DESTROYER\": 1,\n"
        + "\t\t\t\"SUBMARINE\": 1\n"
        + "\t\t}\n"
        + "\t}\n"
        + "}";
    JsonNode msg = objMapper.convertValue(setUpMsg, JsonNode.class);
    Mocket mocket = new Mocket(msg, logger);
    try {
      controller = new ProxyController(mocket, player, viewer);
    } catch (IOException e) {
      fail();
    }
    controller.run();
    JsonParser parser = null;
    try {
      parser = this.objMapper.getFactory().createParser(logger.toString(StandardCharsets.UTF_8));
      MessageJson msgJson = parser.readValueAs(MessageJson.class);
      objMapper.convertValue(msgJson.arguments(), FleetJson.class);
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  void testHandleJoin() {
    String joinMsg = "{\n"
        + "\t\"method-name\": \"join\",\n"
        + "\t\"arguments\": {}\n"
        + "}";
    JsonNode msg = objMapper.convertValue(joinMsg, JsonNode.class);
    Mocket mocket = new Mocket(msg, logger);
    try {
      controller = new ProxyController(mocket, player, viewer);
    } catch (IOException e) {
      fail();
    }
    controller.run();
    JsonParser parser = null;
    try {
      parser = this.objMapper.getFactory().createParser(logger.toString(StandardCharsets.UTF_8));
      MessageJson msgJson = parser.readValueAs(MessageJson.class);
      assertEquals("join", msgJson.messageName());
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  void testHandleTakeShots() {
    arrangeGame();  // Setting up the game
    String takeShotsMsg = "{\n"
        + "\t\"method-name\": \"take-shots\",\n"
        + "\t\"arguments\": {}\n"
        + "}";
    JsonNode msg = objMapper.convertValue(takeShotsMsg, JsonNode.class);
    Mocket mocket = new Mocket(msg, logger);
    try {
      controller = new ProxyController(mocket, player, viewer);
    } catch (IOException e) {
      fail();
    }
    controller.run();
    JsonParser parser = null;
    try {
      parser = this.objMapper.getFactory().createParser(logger.toString(StandardCharsets.UTF_8));
      MessageJson msgJson = parser.readValueAs(MessageJson.class);
      assertEquals("take-shots", msgJson.messageName());
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  void testHandleReportDamage() {
    arrangeGame();  // Setting up the game
    String reportDamageMsg = "{\n"
        + "\t\"method-name\": \"report-damage\",\n"
        + "\t\"arguments\": {\n"
        + "\t\t\"coordinates\": [\n"
        + "\t\t\t{\"x\": 0, \"y\": 1},\n"
        + "\t\t\t{\"x\": 3, \"y\": 2}\n"
        + "\t\t]\n"
        + "\t}\n"
        + "}";
    JsonNode msg = objMapper.convertValue(reportDamageMsg, JsonNode.class);
    Mocket mocket = new Mocket(msg, logger);
    try {
      controller = new ProxyController(mocket, player, viewer);
    } catch (IOException e) {
      fail();
    }
    controller.run();
    JsonParser parser = null;
    try {
      parser = this.objMapper.getFactory().createParser(logger.toString(StandardCharsets.UTF_8));
      MessageJson msgJson = parser.readValueAs(MessageJson.class);
      assertEquals("report-damage", msgJson.messageName());
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  void testHandleSuccessfulHits() {
    arrangeGame();  // Setting up the game
    String successfulHitsMsg = "{\n"
        + "\t\"method-name\": \"successful-hits\",\n"
        + "\t\"arguments\": {\n"
        + "\t\t\"coordinates\": [\n"
        + "\t\t\t{\"x\": 0, \"y\": 1},\n"
        + "\t\t\t{\"x\": 3, \"y\": 2}\n"
        + "\t\t]\n"
        + "\t}\n"
        + "}";
    JsonNode msg = objMapper.convertValue(successfulHitsMsg, JsonNode.class);
    Mocket mocket = new Mocket(msg, logger);
    try {
      controller = new ProxyController(mocket, player, viewer);
    } catch (IOException e) {
      fail();
    }
    controller.run();
    JsonParser parser = null;
    try {
      parser = this.objMapper.getFactory().createParser(logger.toString(StandardCharsets.UTF_8));
      MessageJson msgJson = parser.readValueAs(MessageJson.class);
      assertEquals("successful-hits", msgJson.messageName());
    } catch (IOException e) {
      fail();
    }
  }

  @Test
  void testHandleEndGame() {
    String endGameMsg = "{\n"
        + "\t\"method-name\": \"end-game\",\n"
        + "\t\"arguments\": {\"reason\": \"game over\"}\n"
        + "}";
    JsonNode msg = objMapper.convertValue(endGameMsg, JsonNode.class);
    Mocket mocket = new Mocket(msg, logger);
    try {
      controller = new ProxyController(mocket, player, viewer);
    } catch (IOException e) {
      fail();
    }
    controller.run();
    JsonParser parser = null;
    try {
      parser = this.objMapper.getFactory().createParser(logger.toString(StandardCharsets.UTF_8));
      MessageJson msgJson = parser.readValueAs(MessageJson.class);
      assertEquals("end-game", msgJson.messageName());
    } catch (IOException e) {
      fail();
    }
  }

}
