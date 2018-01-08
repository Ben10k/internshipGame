package lt.taurosevicius.game.server;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameHandlerTest {
    private GameHandler classUnderTest;

    @Before
    public void setup() throws Exception {
        classUnderTest = new GameHandler();

    }


    @Test
    public void getStartAGameString() throws Exception {
        assertEquals("To start a new game enter 'start'", classUnderTest.getStartAGameString());
    }

    @Test
    public void getAnswer() throws Exception {
        assertEquals("Available commands are 'start' and 'exit'", classUnderTest.getAnswer("help"));

        assertEquals("Enter a number between " + classUnderTest.getBoundLow() + " and " +
                classUnderTest.getBoundHigh() + "", classUnderTest.getAnswer("start"));

        for (int i = 1; i <= 11; i++) {
            if (i == 11) {
                fail();
            } else {
                if (classUnderTest.getAnswer(String.valueOf(i)).contains("It took")) {
                    assertTrue(true);
                    break;
                }
            }
        }

        assertTrue(classUnderTest.getAnswer("blablabla")
                .contains("You have entered an invalid command. Enter 'help' to see all available commands."));

        assertTrue(classUnderTest.getAnswer("9").contains("You have not started a new game."));


        assertEquals("Enter a number between " + classUnderTest.getBoundLow() + " and " +
                classUnderTest.getBoundHigh() + "", classUnderTest.getAnswer("start"));


        assertTrue(classUnderTest.getAnswer("156").contains("You have entered an invalid number"));
        assertTrue(classUnderTest.getAnswer("help").contains("You can also"));


    }

    @Test
    public void isAlive() throws Exception {
        assertTrue(classUnderTest.isAlive());
        assertEquals("exit", classUnderTest.getAnswer("exit"));
        assertFalse(classUnderTest.isAlive());
    }

}