package cs3500.pa04.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameBoardTest {
  GameBoard gameBoard1;
  GameBoard gameBoard2;
  Ship ship1;
  Ship ship2;
  Ship ship3;
  List<Ship> fleet1;
  List<Ship> fleet2;

  @BeforeEach
  void setUp() {
    gameBoard1 = new GameBoard(new Coord(10, 10));
    gameBoard2 = new GameBoard(new Coord(11, 9));
    ship1 = new Ship(new ArrayList<>(Arrays.asList(new Coord(1, 1))));
    ship2 = new Ship(new ArrayList<>(Arrays.asList(new Coord(2, 2))));
    ship3 = new Ship(new ArrayList<>(Arrays.asList(new Coord(3, 3))));
    fleet1 = new ArrayList<>(Arrays.asList(ship1, ship2));
    fleet2 = new ArrayList<>(Arrays.asList(ship3));
  }

  @Test
  void setFleet() {
    assertNull(gameBoard1.getFleet());
    gameBoard1.setFleet(fleet1);
    assertEquals(fleet1, gameBoard1.getFleet());
  }

  @Test
  void fleetSize() {
    gameBoard1.setFleet(fleet1);
    assertEquals(2, gameBoard1.fleetSize());
  }

  @Test
  void getFleet() {
    gameBoard2.setFleet(fleet2);
    assertEquals(fleet2, gameBoard2.getFleet());
  }

  @Test
  void getOwnBoard() {
    assertEquals(11, gameBoard2.getOwnBoard().length);
  }

  @Test
  void getOppBoard() {
    assertEquals(10, gameBoard1.getOwnBoard().length);
  }

  @Test
  void isAlive() {
    gameBoard2.setFleet(fleet2);
    assertTrue(gameBoard2.isAlive());
    fleet2.remove(0);
    assertFalse(gameBoard2.isAlive());
  }
}