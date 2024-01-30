package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This record represent the response when a player have join the game
 * It contains the player name and game type that the player want to play.
 *
 * @param name the name of the player
 * @param gameType the type of game they choose
 */
public record JoinResponse(
    @JsonProperty("name") String name,
    @JsonProperty("game-type") String gameType
) {
}
