package lt.taurosevicius.game.client;

import org.junit.Test;

import static org.junit.Assert.*;

public class ClientHandlerTest {

    @Test
    public void testNoServerFound() throws Exception {

        ClientHandler clientHandler = new ClientHandler();
        clientHandler.begin();
        assertEquals(clientHandler.getStatus(), "Server not found");

    }
}