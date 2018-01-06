package lt.taurosevicius.game.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class GameHandler extends Thread {
    private Socket client = null;
    private DataOutputStream output;
    private BufferedReader input;

    public GameHandler(Socket client) {
        this.client = client;
        try {
            // open a new DataOutputStream and BufferedReader on the socket
            output = new DataOutputStream(client.getOutputStream());
            input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            System.out.print("Reader and writer created. ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {


    }
}
