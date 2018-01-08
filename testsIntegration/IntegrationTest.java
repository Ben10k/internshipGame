import lt.taurosevicius.game.client.ClientHandler;
import lt.taurosevicius.game.server.Server;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.assertEquals;

public class IntegrationTest {

    @Before
    public void startServer() {

        Thread server = new Thread(() -> {
            Server connectionHandler = new Server();
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

        ClientHandler clientHandler = new ClientHandler("localhost", 49000, "console");
        clientHandler.begin();
        assertEquals(clientHandler.getStatus(), "Successful exit");

    }

}