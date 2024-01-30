package cs3500.pa04.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.StringReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameControllerTest {
  GameController gc;

  @BeforeEach
  void setUp() {
    gc = new GameController(new StringReader("6 5 6 6 1 1 1 1 1" + "\n 1 1 ".repeat(100)));
  }

  @Test
  void run() {
    gc.run();

    assertEquals(4, gc.getAiBoard().fleetSize());
    assertEquals(0, gc.getUserBoard().fleetSize());
    assertFalse(gc.getUserBoard().isAlive());
    assertTrue(gc.getAiBoard().isAlive());
  }
}