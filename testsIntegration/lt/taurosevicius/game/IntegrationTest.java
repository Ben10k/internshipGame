package lt.taurosevicius.game;

import lt.taurosevicius.game.client.FrontEnd;
import lt.taurosevicius.game.server.ConnectionHandler;
import org.junit.After;
import org.junit.Before;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class IntegrationTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final String noArgs[] = {};

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }


    @Test
    public void testNoServerFound() throws Exception {

        FrontEnd.main(noArgs);
        assertEquals(FrontEnd.getStatus(), "Server not found");

    }

    @Test
    public void testConsoleExit() throws Exception {

        ConnectionHandler.main(noArgs);

        String input = "exit";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        FrontEnd.main(noArgs);
        assertEquals(FrontEnd.getStatus(), "Successful exit");
    }


    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }
}