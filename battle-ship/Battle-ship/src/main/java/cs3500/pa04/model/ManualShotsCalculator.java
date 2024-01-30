package cs3500.pa04.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * represent a class to get shots for the User player
 */
public class ManualShotsCalculator implements ShotCalculator {
  private final Scanner sc;
  private final int height;
  private final int width;

  /**
   * constructor for this shot calculator
   *
   * @param sc   scanner to get user input
   * @param size size of the board in the game
   */
  public ManualShotsCalculator(Scanner sc, Coord size) {
    this.sc = sc;
    this.height = size.getX();
    this.width = size.getY();
  }

  @Override
  public List<Coord> getShots(int n) {
    List<Coord> shots = new ArrayList<>();
    sc.nextLine();
    int shotCount = 0;
    while (shotCount < n) {
      int x = sc.nextInt();
      int y = sc.nextInt();
      Coord oneShot = new Coord(y, x);
      if (checkShot(oneShot)) {
        shots.add(oneShot);
        shotCount++;
      } else {
        System.err.println("uh oh that shot is out of bounds");
      }
    }
    return shots;
  }

  @Override
  public void ajustForHits(List<Coord> shots) {
    return;
  }

  /**
   *Check if the shot is within bound of the board
   *
   * @param shot The coordinate of the shot where it landed on the board.
   * @return Return true if the shot is within bound and false if out.
   */
  public boolean checkShot(Coord shot) {
    return (shot.getX() < this.width && shot.getY() < this.height
        && shot.getX() >= 0 && shot.getY() >= 0);
  }
}
