package cs3500.pa04.viewer;


import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa04.model.Coord;
import cs3500.pa04.model.ShipType;
import java.util.Map;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class TerminalViewerTest {
  TerminalViewer termView;
  Scanner sc1;
  Scanner sc2;

  @BeforeEach
  void setUp() {
    sc1 = new Scanner("6 15");
    sc2 = new Scanner("1 2 3 4");
  }

  @Test
  void showBoardSizePrompt() {
    termView = new TerminalViewer(sc1);
    Coord c = termView.showBoardSizePrompt();
    assertEquals(6, c.getY());
    assertEquals(15, c.getX());
  }


  @Test
  void showShipSelect() {
    termView = new TerminalViewer(sc2);
    Map<ShipType, Integer> m = termView.showShipSelect();
    assertEquals(1, m.get(ShipType.CARRIER));
    assertEquals(2, m.get(ShipType.BATTLESHIP));
    assertEquals(3, m.get(ShipType.DESTROYER));
    assertEquals(4, m.get(ShipType.SUB));

  }
}