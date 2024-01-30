package cs3500.pa04;


import cs3500.pa04.controller.ProxyController;
import cs3500.pa04.model.AiPlayer;
import cs3500.pa04.viewer.TerminalViewer;
import cs3500.pa04.viewer.Viewer;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

/**
 * This is the main driver of this project.
 */
public class Driver {
  /**
   * Project entry point
   *
   * @param args - no command line args required
   */
  public static void main(String[] args) {
    String host = "0.0.0.0";
    int port = 35001;
    try {
      Driver.runClient(host, port);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void runClient(String host, int port)
      throws IOException, IllegalStateException {
    Socket server = new Socket(host, port);
    Scanner sc = new Scanner(new InputStreamReader(System.in));
    Viewer viewer = new TerminalViewer(sc);
    AiPlayer player = new AiPlayer();
    ProxyController proxyController = new ProxyController(server, player, viewer);
    proxyController.run();
  }
}