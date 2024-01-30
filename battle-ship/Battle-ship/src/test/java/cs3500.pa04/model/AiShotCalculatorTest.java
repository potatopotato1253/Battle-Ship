package cs3500.pa04.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AiShotCalculatorTest {
  AiShotCalculator asc;

  @BeforeEach
  void setUp() {
    asc = new AiShotCalculator(new Coord(10, 10));
  }

  @Test
  void getShots() {
    assertEquals(6, asc.getShots(6).size());
    assertEquals(20, asc.getShots(20).size());
  }

}