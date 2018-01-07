package lt.taurosevicius.game.server;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ServerTest {

    private ConnectionHandler connectionHandler = new ConnectionHandler();


    @SuppressWarnings("StatementWithEmptyBody ")
    @Test
    public void testServerStartup() throws Exception {

        assertFalse(connectionHandler.isRunning());
        Thread server = new Thread(() -> connectionHandler.startServer());
        server.start();
        while (!connectionHandler.isInitialized()) {
        }
        assertTrue(connectionHandler.isRunning());
    }
    // TODO: GameHandler testing


}