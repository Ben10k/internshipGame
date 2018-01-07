package lt.taurosevicius.game.client;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClientTest {

    @Test
    public void testNoServerFound() throws Exception {

        ClientHandler clientHandler = new ClientHandler();
        clientHandler.begin();
        assertEquals(clientHandler.getStatus(), "Server not found");

    }
}