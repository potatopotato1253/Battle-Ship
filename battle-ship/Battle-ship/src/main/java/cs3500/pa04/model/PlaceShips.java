package cs3500.pa04.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * class to represent placing ships on a board randomly
 */
public class PlaceShips {
  /**
   * @param numOfShips how many of one type of ship to place
   * @param height     the height of the board
   * @param width      the width of the board as an int
   * @param gb         players gameBoard to place ships on
   * @param type       type of ship to place
   * @return the list of ships to return
   */
  public static List<Ship> placeShipRandom(int numOfShips, int height, int width, GameBoard gb,
                                           ShipType type) {
    List<Ship> result = new ArrayList<>();
    while (numOfShips > 0) {
      Random r = new Random();
      String orientation;

      Coord startCoord;
      if (r.nextInt(2) == 1) {
        int h;
        orientation = "vertical";
        try {
          h = r.nextInt(height - type.shipSize());
        } catch (IllegalArgumentException e) {
          h = 0;
        }
        startCoord = new Coord(h, r.nextInt(width));
      } else {
        int w;
        orientation = "horizontal";
        try {
          w = r.nextInt(width - type.shipSize());
        } catch (IllegalArgumentException e) {
          w = 0;
        }
        startCoord = new Coord(r.nextInt(height), w);
      }
      if (validSpot(startCoord, orientation, type.shipSize(), gb.getOwnBoard())) {
        Ship ship = placeOnBoard(startCoord, orientation, type.shipSize(), gb.getOwnBoard());
        result.add(ship);
        numOfShips = numOfShips - 1;
      }
    }
    return result;
  }

  /**
   * method to place a ship on a board
   *
   * @param startCoord  where to start placing the ahip
   * @param orientation whether the ship is vertical or horizontal
   * @param shipSize    the size of the ship
   * @param board       game board to place ship on
   * @return the ship with its location saved within it too
   */
  private static Ship placeOnBoard(Coord startCoord, String orientation, int shipSize,
                                   int[][] board) {
    Ship result;
    List<Coord> location = new ArrayList<>();
    if (orientation == "vertical") {
      for (int n = 0; n < shipSize; n++) {
        board[n + startCoord.getY()][startCoord.getX()] = shipSize;
        Coord c = new Coord(n + startCoord.getY(), startCoord.getX());
        location.add(c);
      }
      result = new Ship(location);
    } else {
      for (int n = 0; n < shipSize; n++) {
        board[startCoord.getY()][startCoord.getX() + n] = shipSize;
        Coord c = new Coord(startCoord.getY(), startCoord.getX() + n);
        location.add(c);
      }
      result = new Ship(location);
    }
    return result;
  }

  /**
   * checks to see if where it wants to place the ship is a valid spot on the board
   *
   * @param startCoord  where to start placing the ship
   * @param orientation whether the ship is vertical or horizontal
   * @param shipSize    the size of the ship
   * @param board       game board to check placement of ship
   * @return true if the spot to place ship is valid
   */
  private static boolean validSpot(Coord startCoord, String orientation, int shipSize,
                                   int[][] board) {
    if (orientation == "vertical") {
      for (int n = 0; n < shipSize; n++) {
        int spot = board[n + startCoord.getY()][startCoord.getX()];
        if (!(spot == 0)) {
          return false;
        }
      }
      return true;
    } else {
      for (int n = 0; n < shipSize; n++) {
        int spot = board[startCoord.getY()][startCoord.getX() + n];
        if (!(spot == 0)) {
          return false;
        }
      }
      return true;
    }
  }
}
