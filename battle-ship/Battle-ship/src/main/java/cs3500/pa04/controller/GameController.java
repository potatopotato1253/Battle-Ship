package cs3500.pa04.controller;

import cs3500.pa04.model.AiPlayer;
import cs3500.pa04.model.AiShotCalculator;
import cs3500.pa04.model.Coord;
import cs3500.pa04.model.GameBoard;
import cs3500.pa04.model.InvalidUserInput;
import cs3500.pa04.model.ManualShotsCalculator;
import cs3500.pa04.model.Player;
import cs3500.pa04.model.ShipType;
import cs3500.pa04.model.ShotCalculator;
import cs3500.pa04.model.UserPlayer;
import cs3500.pa04.viewer.TerminalViewer;
import cs3500.pa04.viewer.Viewer;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * the controller for battle salvo the handles running the game
 */
public class GameController {
  private final Readable input;
  private Player ai;
  private Player user;
  private GameBoard aiBoard;
  private GameBoard userBoard;

  /**
   * constructor for the Game Controller
   *
   * @param input a readable input for the controller
   */
  public GameController(Readable input) {
    this.input = input;
  }

  /**
   * a void method to run the BattleSalvo game and communicate between model and viewer
   */
  public void run() {
    Scanner sc = new Scanner(input);
    Viewer view = new TerminalViewer(sc);

    Coord size = view.showBoardSizePrompt();
    while (InvalidUserInput.checkBoard(size)) {
      view.showInvalidResponse(
          "uh oh - Board size must have a height and width that is inclusive 6-15");
      size = view.showBoardSizePrompt();
    }

    Map<ShipType, Integer> shipSelection = view.showShipSelect();
    while (InvalidUserInput.checkShipSelction(shipSelection, size)) {
      view.showInvalidResponse("uh oh - you must have 1 of each ship and your"
          + " total number of ships is not to exceed the smallest dimension");
      shipSelection = view.showShipSelect();
    }
    aiBoard = new GameBoard(size);
    userBoard = new GameBoard(size);
    ShotCalculator manualShotCalc = new ManualShotsCalculator(sc, size);
    ShotCalculator aiShotCalc = new AiShotCalculator(size);
    ai = new AiPlayer(aiBoard, aiShotCalc);
    user = new UserPlayer(userBoard, manualShotCalc);
    aiBoard.setFleet(ai.setup(size.getY(), size.getX(), shipSelection));
    userBoard.setFleet(user.setup(size.getY(), size.getX(), shipSelection));

    view.showBoard(userBoard);
    while (aiBoard.isAlive() && userBoard.isAlive()) {
      view.showMsg("please enter shots as -> x y");
      List<Coord> userShots = user.takeShots();
      List<Coord> aiShots = ai.takeShots();
      user.successfulHits(ai.reportDamage(userShots));
      ai.successfulHits(user.reportDamage(aiShots));
      view.showBoard(userBoard);
    }
    if (userBoard.isAlive()) {
      view.showMsg("you won!");
    } else {
      view.showMsg("you lost :c");
    }
  }

  /**
   * method used in testing od the run method
   *
   * @return the ai's GameBoard
   */
  public GameBoard getAiBoard() {
    return aiBoard;
  }

  /**
   * method used in testing od the run method
   *
   * @return the user's GameBaord
   */
  public GameBoard getUserBoard() {
    return userBoard;
  }
}
