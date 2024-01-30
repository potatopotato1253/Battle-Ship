package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.model.Coord;
import cs3500.pa04.model.Ship;

/**
 * This class serves as an adapter for the {@link Ship} class, allowing it to be easily serialized
 * and deserialized with Jackson. It represents a ship in the game with its starting coordinate,
 * length, and orientation.
 */
public class ShipAdapter {
  private final Coord coord;
  private final int length;
  private final Direction direction;

  /**
   *This constructor will help to create a new ShipAdapter instance where it will
   * have the starting coordinate, length, and direction. It is using JSON property,
   * so it is being annotated by the JSON Creator to help deserializes the JSON ship.
   *
   * @param coord The position the ship start with.
   * @param length How long the ship is.
   * @param direction Which way is the ship being place on the baord
   */
  @JsonCreator
  public ShipAdapter(@JsonProperty("coord") Coord coord, @JsonProperty("length") int length,
                     @JsonProperty("direction") Direction direction) {
    this.coord = coord;
    this.length = length;
    this.direction = direction;
  }

  /**
   * Making a new ShipAdapter with a ship object
   * Then extracting specific information from the ship like it
   * coord, length, and direction.
   *
   * @param s represent ta ship object
   */
  public ShipAdapter(Ship s) {
    this(s.shipStart(), s.shipLength(), s.shipDirection());
  }

  /**
   * Get the coordinate of where the ship is at
   *
   * @return the Coord of the ship
   */
  public Coord getCoord() {
    return coord;
  }

  /**
   * Get the ship length
   *
   * @return A number to represent the length of the ship
   */
  public int getLength() {
    return length;
  }

  /**
   * Get the direction of where the ship is facing
   *
   * @return A Direction of VERTICAL or HORIZONTAL
   */
  public Direction getDirection() {
    return direction;
  }
}
