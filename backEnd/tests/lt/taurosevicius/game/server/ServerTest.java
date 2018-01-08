package lt.taurosevicius.game.server;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ServerTest {

    private Server server = new Server();


    @SuppressWarnings("StatementWithEmptyBody ")
    @Test
    public void testServerStartup() throws Exception {

        assertFalse(server.isRunning());
        Thread server = new Thread(() -> this.server.startServer());
        server.start();
        while (!this.server.isInitialized()) {
        }
        assertTrue(this.server.isRunning());
    }

}