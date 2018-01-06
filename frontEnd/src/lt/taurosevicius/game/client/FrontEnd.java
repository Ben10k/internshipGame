package lt.taurosevicius.game.client;

import java.io.IOException;
import java.net.Socket;

public class FrontEnd {
    private static String host = "localhost";
    private static int port = 49000;
    private static String uiType = "console";


    public static void main(String args[]) {
        parseArgs(args);
        ClientHandler clientHandler = new ClientHandler(host, port, uiType);
        clientHandler.begin();

    }


    // Parse arguments if they are present
    private static void parseArgs(String[] args) {
        if (args.length == 2) {
            host = args[0];
            port = Integer.parseInt(args[1]);
        } else if (args.length == 3) {
            uiType = args[2];
        }
    }


}
