package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * to represent a test for the join response json
 */
public class JoinResponseTest {

  private JoinResponse joinResponse;

  @BeforeEach
  public void setup() {
    joinResponse = new JoinResponse("Player1", "SINGLE");
  }

  @Test
  public void testName() {
    assertEquals("Player1", joinResponse.name());
  }

  @Test
  public void testGameType() {
    assertEquals("SINGLE", joinResponse.gameType());
  }
}
