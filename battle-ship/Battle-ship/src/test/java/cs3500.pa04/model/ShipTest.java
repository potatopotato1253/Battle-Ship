package cs3500.pa04.model;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShipTest {
  Coord cor1;
  Coord cor2;
  Coord cor3;
  List<Coord> loc1;
  List<Coord> loc2;
  Ship ship1;
  Ship ship2;

  @BeforeEach
  void setUp() {
    cor1 = new Coord(4, 4);
    cor2 = new Coord(3, 3);
    cor3 = new Coord(2, 2);
    loc1 = new ArrayList<>(List.of(cor1, cor2));
    loc2 = new ArrayList<>(List.of(cor3));
    ship1 = new Ship(loc1);
    ship2 = new Ship(loc2);
  }

  @Test
  void checkShots() {
    assertEquals(0, ship1.checkShots(loc2).size());
    assertEquals(cor1, ship1.checkShots(List.of(cor1)).get(0));
    assertEquals(cor3, ship2.checkShots(List.of(cor3)).get(0));
  }

  @Test
  void isSunk() {
    assertFalse(ship2.isSunk());
    ship2.checkShots(List.of(cor3)).get(0);
    assertTrue(ship2.isSunk());
  }
}