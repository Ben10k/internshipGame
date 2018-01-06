package lt.taurosevicius.game.client;

import java.io.IOException;
import java.net.Socket;

public class FrontEnd {
    private static String host = "localhost";
    private static int port = 49000;
    private static String uiType = "console";
    private static Client client;
    private static Socket clientSocket;
    private static String status;

    public static String getStatus() {
        return status;
    }

    public static void main(String args[]) {
        parseArgs(args);
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

    // Selects which client to use based on uiType
    private static Client selectClient(String uiType) throws IOException {
        switch (uiType) {
            case "console":
                return new ConsoleClient(clientSocket);
            // TODO: create GUI client and add it to the switch
            default:
                return new ConsoleClient(clientSocket);
        }
    }

    // Parse arguments if they are presented
    private static void parseArgs(String[] args) {
        if (args.length == 2) {
            host = args[0];
            port = Integer.parseInt(args[1]);
        } else if (args.length == 3) {
            uiType = args[2];
        }
    }

    // Open a Socket connection to the server
    private static void setupConnection() throws IOException {
        clientSocket = new Socket(host, port);
    }

    // Close the Socket connection from the server
    private static void terminateConnection() throws IOException {
        clientSocket.close();
    }
}
