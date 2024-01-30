package cs3500.pa04.model;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CoordTest {
  Coord cor1;
  Coord cor2;

  @BeforeEach
  void setUp() {
    cor1 = new Coord(1, 2);
    cor2 = new Coord(8, 7);
  }

  @Test
  void testGetHieght() {
    assertEquals(1, cor1.getY());
    assertEquals(8, cor2.getY());
  }

  @Test
  void testGetWidth() {
    assertEquals(2, cor1.getX());
    assertEquals(7, cor2.getX());
  }
}