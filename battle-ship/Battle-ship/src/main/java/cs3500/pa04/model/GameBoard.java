package cs3500.pa04.model;


import java.util.List;

/**
 * to represent a Game board for the BattleSalvo game
 */
public class GameBoard {
  private final int[][] ownBoard;
  private final int[][] opponentBoard;
  private List<Ship> fleet;

  /**
   * constructor for the Game board
   *
   * @param boardSize a Coord with the size of the board
   */
  public GameBoard(Coord boardSize) {
    this.ownBoard = new int[boardSize.getY()][boardSize.getX()];
    this.opponentBoard = new int[boardSize.getY()][boardSize.getX()];
  }

  /**
   * set the fleet for player using this board
   *
   * @param fleet the fleet to be saved on this board
   */
  public void setFleet(List<Ship> fleet) {
    this.fleet = fleet;
  }

  /**
   * method to return the fleet size of this board
   *
   * @return the number of ships in the fleet still alive
   */
  public int fleetSize() {
    return fleet.size();
  }

  /**
   * method to return the fleet saved in this baord
   *
   * @return the entire list of ships in the fleet in this board
   */
  public List<Ship> getFleet() {
    return fleet;
  }

  /**
   * method to return the players board with where ships are placed
   *
   * @return the array of this players board
   */
  public int[][] getOwnBoard() {
    return ownBoard;
  }

  /**
   * method to return the players fog of war view of the opponents board
   *
   * @return the array of the opponents board where shots are placed
   */
  public int[][] getOppBoard() {
    return opponentBoard;
  }

  /**
   * method to return if the player is alive
   *
   * @return true if the player is alive and false otherwise
   */
  public boolean isAlive() {
    return 1 <= fleet.size();
  }
}
