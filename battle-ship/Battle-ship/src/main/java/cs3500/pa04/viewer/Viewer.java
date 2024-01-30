package cs3500.pa04.viewer;

import cs3500.pa04.model.Coord;
import cs3500.pa04.model.GameBoard;
import cs3500.pa04.model.ShipType;
import java.util.Map;
import java.util.Scanner;

/**
 * represents a viewer interface that all viewer classes will implement
 */
public interface Viewer {

  /**
   * shows a prompt for the user to input a board size
   *
   * @return the two ints given as a Coord to be rturned to the controller
   */
  Coord showBoardSizePrompt();

  /**
   * method to be used when user enters an invalid input
   *
   * @param str the message to show the user
   */
  void showInvalidResponse(String str);

  /**
   * show a prompt to the user to enter selection for ships
   *
   * @return a Map of the selction of ships
   */
  Map<ShipType, Integer> showShipSelect();

  /**
   * shows the current Game Board
   *
   * @param b the GameBoard to show the user
   */
  void showBoard(GameBoard b);

  /**
   * show any message to the user
   *
   * @param str the message to show the user
   */
  void showMsg(String str);

  /**
   * Retrieves the Scanner instance associated with this object.
   *
   * @return A Scanner instance that can be used to read input.
   */
  Scanner getScanner();
}
