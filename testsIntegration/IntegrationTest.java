import lt.taurosevicius.game.client.ClientHandler;
import lt.taurosevicius.game.server.Server;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class IntegrationTest {

    @Before
    public void startServer() {

        Thread server = new Thread(() -> {
            Server connectionHandler = new Server();
            connectionHandler.startServer();
        });
        server.start();
    }


    @Test
    public void testConsoleExit() throws Exception {

        String input = "exit";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ClientHandler clientHandler = new ClientHandler("localhost", 49000, "console");
        clientHandler.begin();
        assertEquals(clientHandler.getStatus(), "Successful exit");

    }
    // TODO: port testing
    // TODO: different server location testing

}