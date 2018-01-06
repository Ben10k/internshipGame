package lt.taurosevicius.game.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionHandler {
    private int port = 49000;
    private int gameCount = 0;

    public ConnectionHandler() {
    }

    public ConnectionHandler(int port) {
        if (port > 1023 && port <= 65535)
            this.port = port;
    }
    @SuppressWarnings("InfiniteLoopStatement")
    public void startServer() {

        try {
            // listen for incoming connections on port
            ServerSocket socket = new ServerSocket(port);
            System.out.println("ConnectionHandler listening on port " + port);

            // loop (forever) until program is stopped
            while (true) {
                // accept a new connection
                Socket client = socket.accept();
                // start a new GameHandler to handle the connection and send output to the client
                Thread game = new Thread(new GameHandler(client));
                gameCount++;
                game.start();
                System.out.println("Game number " + gameCount + " started.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
