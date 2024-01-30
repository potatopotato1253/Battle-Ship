package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * This record is represented the response when they are ask for a fleet.
 * It contains the fleet of ship that is on the board.
 *
 * @param fleet The list of ShipAdapter where it contains list of ships.
 */
public record FleetJson(
    @JsonProperty("fleet") List<ShipAdapter> fleet)  {
}
