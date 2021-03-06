package lt.taurosevicius.game.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

public class Server {
    private int port = 49000;
    private int gameCount = 0;
    private AtomicBoolean running = new AtomicBoolean(false);
    private AtomicBoolean initialized = new AtomicBoolean(false);

    public Server() {
    }

    public Server(int port) {
        if (port > 1023 && port <= 65535)
            this.port = port;
    }

    public void startServer() {
        running.set(true);
        initialized.set(true);

        try {
            // listen for incoming connections on port
            ServerSocket socket = new ServerSocket(port);
            System.out.println("Server listening on port " + port);

            // loop (forever) until program is stopped
            while (running.get()) {
                // accept a new connection
                Socket client = socket.accept();
                // start a new ConnectionHandler to handle the connection and send output to the client
                Thread game = new Thread(new ConnectionHandler(client));
                gameCount++;
                game.start();
                System.out.println("Game number " + gameCount + " started.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isInitialized() {
        return initialized.get();
    }

    public boolean isRunning() {
        return running.get();
    }
}
