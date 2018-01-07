import lt.taurosevicius.game.client.ClientHandler;
import lt.taurosevicius.game.server.ConnectionHandler;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class IntegrationTest {

    @Before
    public void startServer() {

        Thread server = new Thread(() -> {
            ConnectionHandler connectionHandler = new ConnectionHandler();
            connectionHandler.startServer();
        });
        server.start();
    }


    @Test
    public void testConsoleExit() throws Exception {

        String input = "exit";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ClientHandler clientHandler = new ClientHandler();
        clientHandler.begin();
        assertEquals(clientHandler.getStatus(), "Successful exit");

    }


}