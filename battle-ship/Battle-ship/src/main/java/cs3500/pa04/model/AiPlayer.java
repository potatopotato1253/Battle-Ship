package cs3500.pa04.model;

import java.util.List;

/**
 * represents an Ai player in the battlesalvo game
 */
public class AiPlayer extends AbstractPlayer {

  /**
   * the constructor for an Ai player
   *
   * @param board the GameBoard it will use
   * @param shotCalc a shot calculator for the Ai to use for taking shots
   */
  public AiPlayer(GameBoard board, ShotCalculator shotCalc) {
    super(board, shotCalc);
  }


  /**
   * Constructs a new AI Player instance with default parameters.
   */
  public AiPlayer() {
  }


  @Override
  public List<Coord> takeShots() {
    List<Coord> aiShots = shotCalculator.getShots(board.fleetSize());
    for (Coord c : aiShots) {
      board.getOppBoard()[c.getY()][c.getX()] = 1;
    }
    return aiShots;
  }
}
