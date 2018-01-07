package lt.taurosevicius.game.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ConsoleClient implements Client {

    private BufferedReader fromConsole;
    private DataOutputStream toServer;
    private BufferedReader fromServer;

    public ConsoleClient(Socket socket) throws IOException {
        // open a new BufferedReader on console input
        fromConsole = new BufferedReader(new InputStreamReader(System.in));

        // open a new DataOutputStream and BufferedReader on the socket
        toServer = new DataOutputStream(socket.getOutputStream());
        fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    // Read the first command from the server and print it out
    public void init() {
        try {
            System.out.println(fromServer.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Write and read with server until the response is "exit"
    public void playGame() {
        String result;
        while (true) {
            try {
                toServer.writeBytes(fromConsole.readLine() + '\n');
                result = fromServer.readLine();

                if (result.equalsIgnoreCase("exit")) {
                    System.out.println("You have finished the game.");
                    break;
                }
                System.out.println(result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
