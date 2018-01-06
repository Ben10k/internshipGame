package lt.taurosevicius.game.client;

import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private  String host;
    private  int port;
    private  String uiType;
    private  Client client;
    private  Socket clientSocket;
    private  String status;

    public ClientHandler(String host, int port, String uiType) {
        this.host = host;
        this.port = port;
        this.uiType = uiType;
    }

    public ClientHandler() {
        this.host = "localhost";
        this.port = 49000;
        this.uiType = "console";
    }

    public void begin() {
        try {
            setupConnection();
            client = selectClient(uiType);
            client.init();
            client.playGame();
            terminateConnection();
            status = "Successful exit";
            System.out.print(status);
        } catch (IOException e) {
            status = "Server not found";
            System.out.print(status);
        }

    }

    public  String getStatus() {
        return status;
    }
    // Selects which client to use based on uiType
    private  Client selectClient(String uiType) throws IOException {
        switch (uiType) {
            case "console":
                return new ConsoleClient(clientSocket);
            // TODO: create GUI client and add it to the switch
            default:
                return new ConsoleClient(clientSocket);
        }
    }


    // Open a Socket connection to the server
    private  void setupConnection() throws IOException {
        clientSocket = new Socket(host, port);
    }

    // Close the Socket connection from the server
    private  void terminateConnection() throws IOException {
        clientSocket.close();
    }
}
