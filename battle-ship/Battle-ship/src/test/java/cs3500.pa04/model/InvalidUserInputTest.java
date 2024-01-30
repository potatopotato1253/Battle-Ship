package cs3500.pa04.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class InvalidUserInputTest {

  @Test
  void checkBoard() {
    assertTrue(InvalidUserInput.checkBoard(new Coord(1, 8)));
    assertTrue(InvalidUserInput.checkBoard(new Coord(8, 1)));
    assertFalse(InvalidUserInput.checkBoard(new Coord(8, 8)));


  }

  @Test
  void checkShipSelction() {
    Map<ShipType, Integer> m = new HashMap<>();
    m.put(ShipType.CARRIER, 1);
    m.put(ShipType.BATTLESHIP, 1);
    m.put(ShipType.DESTROYER, 4);
    m.put(ShipType.SUB, 1);
    assertTrue(InvalidUserInput.checkShipSelction(m, new Coord(8, 6)));
    assertFalse(InvalidUserInput.checkShipSelction(m, new Coord(8, 8)));
    Map<ShipType, Integer> m2 = new HashMap<>();
    m2.put(ShipType.CARRIER, 1);
    m2.put(ShipType.BATTLESHIP, 1);
    m2.put(ShipType.DESTROYER, 0);
    m2.put(ShipType.SUB, 1);
    assertTrue(InvalidUserInput.checkShipSelction(m2, new Coord(8, 8)));
  }
}