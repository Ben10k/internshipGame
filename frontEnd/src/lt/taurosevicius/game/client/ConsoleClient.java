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

    private Socket clientSocket;
    private String status = "Initiated";

    public ConsoleClient() {
        // open a new BufferedReader on console input
        fromConsole = new BufferedReader(new InputStreamReader(System.in));

    }

    // Read the first command from the server and print it out
    public void initGame() {
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
                status = "Server has been terminated";
            }
        }
    }

    // Open a Socket connection to the server
    public void setupConnection(String host, int port) {
        try {
            clientSocket = new Socket(host, port);
            // open a new DataOutputStream and BufferedReader on the socket
            toServer = new DataOutputStream(clientSocket.getOutputStream());
            fromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            status = "Connection established";
        } catch (IOException e) {
            status = "Server not found";
        }
    }

    // Close the Socket connection from the server
    public void terminateConnection() {
        try {
            clientSocket.close();
            status = "Successful exit";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getStatus() {
        return status;
    }
}
