package cs3500.pa04.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ManualShotsCalculatorTest {
  ManualShotsCalculator msc;

  @BeforeEach
  void setUp() {
  }

  @Test
  void getShots() {
    msc = new ManualShotsCalculator(new Scanner("\n 1 1 2 2 3 3"), new Coord(6, 6));
    List<Coord> shots = msc.getShots(2);
    assertEquals(1, shots.get(0).getY());
    assertEquals(2, shots.get(1).getY());
  }

  @Test
  void checkShot() {
    msc = new ManualShotsCalculator(new Scanner("\n 1 1 2 2 3 3"), new Coord(6, 6));
    assertFalse(msc.checkShot(new Coord(8, 4)));
    assertTrue(msc.checkShot(new Coord(3, 4)));
  }
}