package cs3500.pa04.model;

/**
 * represents different types of ship and there sizes as ints
 */
public enum ShipType {
  /**
   * represents a submarine with size 3
   */
  SUB(3),
  /**
   * represents a destroyer with size 4
   */
  DESTROYER(4),
  /**
   * represents a battleship with size 5
   */
  BATTLESHIP(5),
  /**
   * represents a carrier with size 6
   */
  CARRIER(6);

  private final int size;


  ShipType(int size) {
    this.size = size;
  }

  /**
   * method to return the int value of a ships size
   *
   * @return the size of the ship
   */
  public int shipSize() {
    return this.size;
  }
}
