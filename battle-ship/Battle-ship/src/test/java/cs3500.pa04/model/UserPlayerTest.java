package cs3500.pa04.model;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserPlayerTest {
  UserPlayer user;
  Coord size;
  Map<ShipType, Integer> fleet = new HashMap<>();


  @BeforeEach
  void setUp() {
    size = new Coord(20, 20);
    fleet.put(ShipType.CARRIER, 1);
    fleet.put(ShipType.BATTLESHIP, 1);
    fleet.put(ShipType.DESTROYER, 1);
    fleet.put(ShipType.SUB, 1);
  }

  @Test
  void testTakeShotsFleet() {
    user = new UserPlayer(new GameBoard(size),
        new ManualShotsCalculator(new Scanner("\n1 1 2 2 3 3 4 4\n 1 1 2 2 3 3 4 4"), size));
    user.setup(size.getY(), size.getX(), fleet);
    assertEquals(4, user.takeShots().size());
    user.board.getFleet().remove(0);
    assertEquals(3, user.takeShots().size());
  }


  @Test
  void testReportDamage() {
    user = new UserPlayer(new GameBoard(size), new ManualShotsCalculator(new Scanner(""), size));
    Coord damage1 = new Coord(1, 0);
    user.setup(size.getY(), size.getX(), fleet);
    user.board.getFleet().add(new Ship(new ArrayList<>(List.of(damage1))));
    List<Coord> reportedDamage = user.reportDamage(List.of(damage1));
    assertEquals(damage1, reportedDamage.get(0));
  }
}