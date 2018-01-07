package lt.taurosevicius.game.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class GameHandler extends Thread {
    private Socket client = null;
    private DataOutputStream toClient;
    private BufferedReader fromClient;

    public GameHandler(Socket client) {
        this.client = client;
        try {
            // open a new DataOutputStream and BufferedReader on the socket
            toClient = new DataOutputStream(client.getOutputStream());
            fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            System.out.print("Reader and writer created. ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        String input;

        try {
            toClient.writeBytes("To start a new game enter 'start'\n");
            String command;

            while (true) {
                input = fromClient.readLine();

                if (input.compareTo("start") == 0) {
                    startNewGame();
                    command = "Enter a number between " + "0" + " and " + "10" + "\n";
                } else if (input.compareTo("exit") == 0) {
                    toClient.writeBytes("exit\n");
                    break;
                } else {
                    command = "Unexpected command. " + "Enter a number between " + "0" + " and " + "10" + "\n";
                }
                toClient.writeBytes(command);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void startNewGame() {
    }
}
