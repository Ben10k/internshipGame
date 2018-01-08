package lt.taurosevicius.game.client;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClientTest {

    @Test
    public void testNoServerFound() throws Exception {

        ClientHandler clientHandler = new ClientHandler("localhost", 49000, "console");
        clientHandler.begin();
        assertEquals(clientHandler.getStatus(), "Server not found");

    }
}