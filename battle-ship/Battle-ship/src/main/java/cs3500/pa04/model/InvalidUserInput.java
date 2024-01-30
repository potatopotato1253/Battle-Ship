package cs3500.pa04.model;

import java.util.Map;

/**
 * checks if a given input from the controller is invalid
 */
public class InvalidUserInput {

  /**
   * checks to make sure input for board size is valid
   *
   * @param size the Coord with the size of the baord
   * @return true if the input is invalid
   */
  public static boolean checkBoard(Coord size) {
    return (size.getX() < 6 || size.getY() < 6 || size.getX() > 15
        || size.getY() > 15);
  }

  /**
   * checks to see if the user entered ship selection is vlaid
   *
   * @param shipSelection the map containing the ship selection
   * @param size          size of the board
   * @return true if input is invalid
   */
  public static boolean checkShipSelction(Map<ShipType, Integer> shipSelection, Coord size) {
    int minSize = Math.min(size.getY(), size.getX());
    int total = 0;

    for (Map.Entry<ShipType, Integer> entry : shipSelection.entrySet()) {
      int numOfShips = entry.getValue();
      if (numOfShips < 1) {
        return true;
      }
      if (numOfShips + total > minSize) {
        return true;
      }
      total = total + numOfShips;
    }
    return false;
  }
}
