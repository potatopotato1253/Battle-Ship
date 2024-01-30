package cs3500.pa04.model;



import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class ShipTypeTest {

  @Test
  void shipSize() {
    assertEquals(3, ShipType.SUB.shipSize());
    assertEquals(4, ShipType.DESTROYER.shipSize());
    assertEquals(5, ShipType.BATTLESHIP.shipSize());
    assertEquals(6, ShipType.CARRIER.shipSize());
  }
}