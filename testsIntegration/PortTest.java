import lt.taurosevicius.game.client.ClientHandler;
import lt.taurosevicius.game.server.Server;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.assertEquals;


public class PortTest {

    private final int port = 5000;

    @Before
    public void startServer() {

        Thread server = new Thread(() -> {
            Server connectionHandler = new Server(port);
            connectionHandler.startServer();
        });
        server.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testConsoleExit() throws Exception {

        ByteArrayInputStream in = new ByteArrayInputStream("exit".getBytes());
        System.setIn(in);

        ClientHandler clientHandler = new ClientHandler("localhost", port, "console");
        clientHandler.begin();
        assertEquals(clientHandler.getStatus(), "Successful exit");

    }
}
