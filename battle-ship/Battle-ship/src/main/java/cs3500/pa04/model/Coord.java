package cs3500.pa04.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * to represent a set of x, y coordinates on a board
 */
public class Coord {
  private final int x;
  private final int y;

  /**
   * constructor for a Coord
   *
   * @param y the height/y value for the Coord
   * @param x  the width/x value for the Coord
   */
  @JsonCreator
  public Coord(@JsonProperty("y") int y, @JsonProperty("x") int x) {
    this.x = x;
    this.y = y;
  }

  /**
   * to get the height/y from this Coord
   *
   * @return the int value
   */
  public int getY() {
    return y;
  }

  /**
   * to get the width/x from this Cooord
   *
   * @return the int value
   */
  public int getX() {
    return x;
  }
}
