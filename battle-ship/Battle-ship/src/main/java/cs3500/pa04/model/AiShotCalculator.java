package cs3500.pa04.model;

import java.util.ArrayList;
import java.util.List;

/**
 * represents a shot calculator for an Ai player
 */
public class AiShotCalculator implements ShotCalculator {

  private final int height;
  private final int width;
  private final List<Coord> prevShots = new ArrayList<>();
  private final int[][] heatMap;

  /**
   * controctor for the Ai shot calculator
   *
   * @param size the size of the game board
   */
  public AiShotCalculator(Coord size) {
    this.height = size.getY();
    this.width = size.getX();
    this.heatMap = new int[height][width];
    this.fillHeatMap(5);
    this.fillHeatMap(4);
    this.fillHeatMap(3);
    this.fillHeatMap(2);
  }

  private void fillHeatMap(int shipSize) {
    for (int n = 0; n < height; n++) {
      for (int i = 0; i < width; i++) {
        if (n + shipSize < height) {
          for (int k = n; k <= n + shipSize; k++) {
            heatMap[k][i] = heatMap[k][i] + 4;
          }
        }
        if (i + shipSize < width) {
          for (int k = i; k <= i + shipSize; k++) {
            heatMap[n][k] = heatMap[n][k] + 4;
          }
        }
      }
    }
  }

  private Coord getLargestProb() {
    int maxvalue = heatMap[0][0];
    Coord maxProbShot = new Coord(0, 0);
    for (int j = 0; j < height; j++) {
      for (int i = 0; i < width; i++) {
        if (heatMap[j][i] > maxvalue) {
          maxvalue = heatMap[j][i];
          maxProbShot = new Coord(j, i);
        }
      }
    }
    return maxProbShot;
  }

  /**
   * will adjust the number on the heat map around cell of a hit to increase
   *
   * @param shots the list of shots that hit an enemy ship
   */
  public void ajustForHits(List<Coord> shots) {
    for (Coord c : shots) {
      ajustWeights(c, 122);
    }
  }

  private void ajustWeights(Coord maxProbShot, int weight) {
    int x = maxProbShot.getX();
    int y = maxProbShot.getY();
    if (x > 1) {
      heatMap[y][x - 1] = heatMap[y][x - 1] + weight;
    }
    if (x < width - 1) {
      heatMap[y][x + 1] = heatMap[y][x + 1] + weight;
    }
    if (y > 1) {
      heatMap[y - 1][x] = heatMap[y - 1][x] + weight;
    }
    if (y < height - 1) {
      heatMap[y + 1][x] = heatMap[y + 1][x] + weight;
    }

  }

  @Override
  public List<Coord> getShots(int n) {
    List<Coord> result = new ArrayList<>();
    while (n > 0) {
      Coord shot = getLargestProb();
      if (heatMap[shot.getY()][shot.getX()] > -1000) {
        result.add(shot);
        heatMap[shot.getY()][shot.getX()] = -50000000;
        ajustWeights(shot, -35);
      }
      n--;
    }
    return result;
  }
}
