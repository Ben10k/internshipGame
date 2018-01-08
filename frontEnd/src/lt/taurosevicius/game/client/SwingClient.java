package lt.taurosevicius.game.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class SwingClient implements Client {
    private JButton a1Button;
    private JButton a2Button;
    private JButton a3Button;
    private JButton a4Button;
    private JButton a5Button;
    private JButton a6Button;
    private JButton a7Button;
    private JButton a8Button;
    private JButton a9Button;
    private JButton buttonStart;
    private JLabel outputLabel;
    private JPanel rootPanel;
    private JButton a10Button;


    private DataOutputStream toServer;
    private BufferedReader fromServer;
    private AtomicBoolean playing = new AtomicBoolean(false);

    @SuppressWarnings("MagicConstant")
    public SwingClient(Socket socket) throws IOException {
        // Create a JFrame and load contents into it
        JFrame frame = new JFrame("Guess a number");
        frame.setContentPane(rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(480, 272));
        frame.setResizable(false);
        frame.setVisible(true);
        frame.pack();


        // open a new DataOutputStream and BufferedReader on the socket
        toServer = new DataOutputStream(socket.getOutputStream());
        fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // Override exit procedure
        Runtime.getRuntime().addShutdownHook(new Thread(() -> playing.set(false)));

    }

    // Read the first command from the server and print it out
    public void initGame() {
        try {
            outputLabel.setText(fromServer.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String iterate(String output) {
        String result;
        try {
            toServer.writeBytes(output + "\n");
            result = fromServer.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            result = "Problem";
        }
        return result;
    }

    @SuppressWarnings("StatementWithEmptyBody ")
    // Add MouseListeners to all of the buttons
    public void playGame() {

        List<JButton> numbers = new ArrayList<>();
        numbers.addAll(Arrays.asList(buttonStart,
                a1Button, a2Button, a3Button, a4Button, a5Button,
                a6Button, a7Button, a8Button, a9Button, a10Button));

        for (int i = 0; i < numbers.size(); i++) {
            int finalI = i;
            numbers.get(i).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if (finalI == 0) {
                        outputLabel.setText(iterate("start"));
                    }
                    outputLabel.setText(iterate(Integer.toString(finalI)));
                }
            });
        }
        playing.set(true);
        while (playing.get()) {
        }
        iterate("exit");
    }
}
