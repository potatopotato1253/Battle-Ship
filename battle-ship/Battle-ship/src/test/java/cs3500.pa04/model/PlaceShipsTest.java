package cs3500.pa04.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class PlaceShipsTest {


  @Test
  void placeShipRandom() {
    PlaceShips ps = new PlaceShips();
    List<Ship> fleet =
        ps.placeShipRandom(3, 10, 10, new GameBoard(new Coord(10, 10)), ShipType.CARRIER);
    assertEquals(3, fleet.size());
    List<Ship> fleet2 =
        ps.placeShipRandom(5, 10, 10, new GameBoard(new Coord(10, 10)), ShipType.SUB);
    assertEquals(5, fleet2.size());
  }
}