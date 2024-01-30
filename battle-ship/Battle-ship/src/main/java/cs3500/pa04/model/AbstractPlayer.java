package cs3500.pa04.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * represnts a abstract player with the common methods all player would share
 */
public abstract class AbstractPlayer implements Player {
  /**
   * the board to be used by a player in the game
   */
  protected GameBoard board;

  /**
   * Calculate where the shot will be fired after the first round.
   */
  protected ShotCalculator shotCalculator;

  /**
   * the constructor all players will use
   *
   * @param board the Gameboard for the player to use
   */
  AbstractPlayer(GameBoard board, ShotCalculator shotCalculator) {
    this.board = board;
    this.shotCalculator = shotCalculator;
  }

  AbstractPlayer() {
  }

  @Override
  public String name() {
    return "shelton-je";
  }

  @Override
  public List<Ship> setup(int height, int width, Map<ShipType, Integer> specifications) {
    List<Ship> fleet = new ArrayList<>();

    fleet.addAll(
        PlaceShips.placeShipRandom(specifications.get(ShipType.CARRIER), height, width, board,
        ShipType.CARRIER));
    fleet.addAll(
        PlaceShips.placeShipRandom(specifications.get(ShipType.BATTLESHIP), height, width, board,
            ShipType.BATTLESHIP));
    fleet.addAll(
        PlaceShips.placeShipRandom(specifications.get(ShipType.DESTROYER), height, width, board,
        ShipType.DESTROYER));
    fleet.addAll(PlaceShips.placeShipRandom(specifications.get(ShipType.SUB), height, width, board,
        ShipType.SUB));

    board.setFleet(fleet);
    return fleet;
  }

  @Override
  public List<Coord> reportDamage(List<Coord> opponentShotsOnBoard) {
    List<Coord> result = new ArrayList<>();
    List<Ship> sunkShips = new ArrayList<>();
    for (Coord c : opponentShotsOnBoard) {
      board.getOwnBoard()[c.getY()][c.getX()] = 1;
    }
    for (Ship s : board.getFleet()) {
      List<Coord> shipDamage = s.checkShots(opponentShotsOnBoard);
      if (s.isSunk()) {
        sunkShips.add(s);
      }
      result.addAll(shipDamage);
    }
    board.getFleet().removeAll(sunkShips);
    for (Coord c : result) {
      board.getOwnBoard()[c.getY()][c.getX()] = 8;
    }
    return result;
  }

  @Override
  public void successfulHits(List<Coord> shotsThatHitOpponentShips) {
    shotCalculator.ajustForHits(shotsThatHitOpponentShips);
    for (Coord c : shotsThatHitOpponentShips) {
      board.getOppBoard()[c.getY()][c.getX()] = 8;
    }
  }

  @Override
  public void endGame(GameResult result, String reason) {
    return;
    // to be implemented in PA04
  }

  @Override
  public abstract List<Coord> takeShots();

  /**
   * Initialize a GameBoard for the game
   *
   * @param board The board object is being pass to create a board.
   */
  public void setBoard(GameBoard board) {
    this.board = board;
  }

  /**
   *Get the board that is currently being use for the current game.
   *
   * @return A GameBoard that is being use for the game.
   */
  public GameBoard getBoard() {
    return this.board;
  }

  /**
   * Sets the ShotCalculator for this the Ai player.
   *
   * @param sc The ShotCalculator .
   */
  public void setShotCalculator(ShotCalculator sc) {
    this.shotCalculator = sc;
  }
}
