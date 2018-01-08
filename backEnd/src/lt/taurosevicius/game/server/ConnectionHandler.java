package lt.taurosevicius.game.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ConnectionHandler extends Thread {

    private Socket client = null;
    private DataOutputStream toClient;
    private BufferedReader fromClient;

    public ConnectionHandler(Socket client) {
        this.client = client;
        try {
            // open a new DataOutputStream and BufferedReader on the socket
            toClient = new DataOutputStream(client.getOutputStream());
            fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            System.out.println("Reader and writer created.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        GameHandler game = new GameHandler();
        String input;
        try {
            toClient.writeBytes(game.getStartAGameString() + "\n");
            String command;
            while (game.isAlive()) {
                // read line from client, calculate the answer and send it to back client
                input = fromClient.readLine();
                command = game.getAnswer(input);
                toClient.writeBytes(command + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // close the connection to the client
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Output closed.");
        }
    }
}
