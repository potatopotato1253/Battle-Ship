package cs3500.pa04.model;

import cs3500.pa04.json.Direction;
import java.util.ArrayList;
import java.util.List;

/**
 * represents a ship in the game of BattleSalvo
 */
public class Ship {
  private final List<Coord> location;

  /**
   * the constructor for a ship
   *
   * @param location a list of Coords with where this ship is
   */
  public Ship(List<Coord> location) {
    this.location = location;
  }

  /**
   * given a list of Coords and checks to see if any hit this ship
   *
   * @param oppShots the coord of opponents shots to check against
   * @return the list of shots that hit this ship and removes those shots from this ships location
   */
  public List<Coord> checkShots(List<Coord> oppShots) {
    List<Coord> result = new ArrayList<>();
    for (Coord oc : oppShots) {
      for (int i = 0; i < location.size(); i++) {
        if (oc.getY() == location.get(i).getY()
            && oc.getX() == location.get(i).getX()) {
          result.add(location.remove(i));
        }
      }
    }
    return result;
  }

  /**
   * checks to see if this ship is sunk
   *
   * @return true if this ship is sunk when it doesn't have any Coords in its location
   */
  public boolean isSunk() {
    return location.size() == 0;
  }

  /**
   * Get the location where the ship is starting
   *
   * @return the current location where the ship is at
   */
  public Coord shipStart() {
    return location.get(0);
  }

  /**
   * Get the length of a ship on the current boards
   *
   * @return The size of the ship by using it location
   */
  public int shipLength() {
    return location.size();
  }

  /**
   * Returns if this ship is vertical or horizontal
   *
   * @return the direction of the ship
   */
  public Direction shipDirection() {
    if (location.get(0).getX() == location.get(1).getX()) {
      return Direction.VERTICAL;
    } else {
      return Direction.HORIZONTAL;
    }
  }
}
