package cs3500.pa04.model;

import java.util.List;

/**
 * represents a interface that will get shots for a player
 */
public interface ShotCalculator {

  /**
   * returns a list of coords to represent the shots to be taken by a player
   *
   * @param n the number of shots to get
   * @return a list of Coords with the shots to be used
   */
  List<Coord> getShots(int n);

  /**
   * adjusts for shots that hit a ship in a shot calculator
   *
   * @param shots shots that hit an enemy ship
   */
  void ajustForHits(List<Coord> shots);
}
