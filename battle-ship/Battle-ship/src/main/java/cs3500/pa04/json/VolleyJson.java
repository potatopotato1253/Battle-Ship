package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.model.Coord;
import java.util.List;

/**
 * Represent a volley of coordinate by the player that will be serialized
 * to send back to the server.
 *
 * @param shots Where the shots were fire.
 */
public record VolleyJson(
    @JsonProperty("coordinates") List<Coord> shots) {

  /**
   * Get number of shots that was fired and put them in a list of coordinate
   *
   * @return A list of Coord of where the shot was fired.
   */
  public List<Coord> getShots() {
    return shots;
  }
}
