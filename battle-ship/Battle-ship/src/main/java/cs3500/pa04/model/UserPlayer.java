package cs3500.pa04.model;

import java.util.List;

/**
 * represents a User player for the BattleSalvo game
 */
public class UserPlayer extends AbstractPlayer {


  /**
   * the constructor for a user player
   *
   * @param board the GameBoard that the player will use
   * @param msc  a shot calculator for the user player to use to take shots
   */
  public UserPlayer(GameBoard board, ShotCalculator msc) {
    super(board, msc);
  }


  @Override
  public List<Coord> takeShots() {
    List<Coord> userShots = shotCalculator.getShots(board.fleetSize());
    for (Coord c : userShots) {
      board.getOppBoard()[c.getY()][c.getX()] = 1;
    }
    return userShots;
  }
}
