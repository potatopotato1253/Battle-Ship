package cs3500.pa04.controller;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * to represent a fake socket for testing
 */
public class Mocket extends Socket {
  private final InputStream fakeInput;
  private final OutputStream logger;

  /**
   * constructor for the mocket
   *
   * @param input the Json node to send the cleint
   * @param logger what the client sent back
   */
  Mocket(JsonNode input, ByteArrayOutputStream logger) {
    this.fakeInput = new ByteArrayInputStream(input.asText().getBytes());
    this.logger = logger;
  }

  @Override
  public InputStream getInputStream() throws IOException {
    return this.fakeInput;
  }

  @Override
  public OutputStream getOutputStream() throws IOException {
    return logger;
  }
}
