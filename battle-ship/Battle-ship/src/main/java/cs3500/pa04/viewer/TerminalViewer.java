package cs3500.pa04.viewer;

import cs3500.pa04.model.Coord;
import cs3500.pa04.model.GameBoard;
import cs3500.pa04.model.ShipType;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * represent a viewer to show in the terminal
 */
public class TerminalViewer implements Viewer {
  Scanner sc;

  /**
   * constructors for this viewer
   *
   * @param sc the scanner for this viewer to use
   */
  public TerminalViewer(Scanner sc) {
    this.sc = sc;
  }

  @Override
  public Coord showBoardSizePrompt() {
    System.out.println(
        "------- please enter Height and Width as individual integers between 6 and 15 ------- ");
    int h = sc.nextInt();
    int w = sc.nextInt();

    return new Coord(h, w);
  }

  @Override
  public void showInvalidResponse(String str) {
    System.out.println(str);
  }

  @Override
  public Map<ShipType, Integer> showShipSelect() {
    Map<ShipType, Integer> result = new HashMap<>();
    System.out.println("=".repeat(20));
    System.out.println(
        "please enter the number of ships in order of Carrier, BattleShip, Destroyer, Submarine");
    result.put(ShipType.CARRIER, sc.nextInt());
    result.put(ShipType.BATTLESHIP, sc.nextInt());
    result.put(ShipType.DESTROYER, sc.nextInt());
    result.put(ShipType.SUB, sc.nextInt());

    return result;
  }

  @Override
  public void showBoard(GameBoard gb) {
    for (int[] ints : gb.getOppBoard()) {
      for (int anInt : ints) {
        String output = translateNumber(anInt);
        System.out.print(output + " ");
      }
      System.out.println("");
    }
    System.out.println("-".repeat(40));
    for (int[] ints : gb.getOwnBoard()) {
      for (int anInt : ints) {
        String output = translateNumber(anInt);
        System.out.print(output + " ");
      }
      System.out.println("");
    }
  }

  @Override
  public void showMsg(String str) {
    System.out.println(str);
  }

  /**
   * transaltes numbers to chars
   *
   * @param n number to be translated
   * @return the char to be shown
   */
  private String translateNumber(int n) {

    switch (n) {
      case 1:
        return "#";
      case 8:
        return "X";
      case 3:
        return "S";
      case 4:
        return "D";
      case 5:
        return "B";
      case 6:
        return "C";
      default:
        return "~";
    }
  }

  @Override
  public Scanner getScanner() {
    return sc;
  }
}
