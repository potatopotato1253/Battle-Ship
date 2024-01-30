package cs3500.pa04.model;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AiPlayerTest {
  AiPlayer ai;
  Coord size;
  Map<ShipType, Integer> fleet1 = new HashMap<>();
  Map<ShipType, Integer> fleet2 = new HashMap<>();


  @BeforeEach
  void setUp() {
    size = new Coord(20, 20);
    ai = new AiPlayer(new GameBoard(size), new AiShotCalculator(size));
    fleet1.put(ShipType.CARRIER, 2);
    fleet1.put(ShipType.BATTLESHIP, 4);
    fleet1.put(ShipType.DESTROYER, 2);
    fleet1.put(ShipType.SUB, 4);
    fleet2.put(ShipType.CARRIER, 1);
    fleet2.put(ShipType.BATTLESHIP, 1);
    fleet2.put(ShipType.DESTROYER, 1);
    fleet2.put(ShipType.SUB, 1);
  }

  @Test
  void testTakeShotsFleet1() {
    ai.setup(size.getY(), size.getX(), fleet1);
    assertEquals(12, ai.takeShots().size());
    ai.board.getFleet().remove(0);
    assertEquals(11, ai.takeShots().size());
  }

  @Test
  void testTakeShotsFleet2() {
    ai.setup(size.getY(), size.getX(), fleet2);
    assertEquals(4, ai.takeShots().size());
    ai.board.getFleet().remove(0);
    assertEquals(3, ai.takeShots().size());
  }

  @Test
  void testReportDamage() {
    Coord damage1 = new Coord(1, 1);
    ai.setup(size.getY(), size.getX(), fleet1);
    ai.board.getFleet().add(new Ship(new ArrayList<>(List.of(damage1))));
    List<Coord> reportedDamage = ai.reportDamage(List.of(damage1));
    Coord damg1 = reportedDamage.get(0);
    assertEquals(1, damg1.getX());
    assertEquals(1, damg1.getY());

  }
}