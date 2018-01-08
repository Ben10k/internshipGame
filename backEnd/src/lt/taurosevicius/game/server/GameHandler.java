package lt.taurosevicius.game.server;

import java.util.concurrent.ThreadLocalRandom;

public class GameHandler {
    private final int boundLow = 0;
    private final int boundHigh = 10;
    private final String enterANumberString = "Enter a number between " + boundLow + " and " + boundHigh + "";
    private final String startAGameString = "To start a new game enter 'start'";
    private final String availableCommandsString = "Available commands are 'start' and 'exit'";
    private int guesses;
    private int numberToGuess;
    private boolean playing = false;
    private boolean alive = true;

    public String getStartAGameString() {
        return startAGameString;
    }

    public String getAnswer(String input) {
        if (input.equalsIgnoreCase("start")) {
            startNewGame();
            return enterANumberString;
        } else if (input.equalsIgnoreCase("exit")) {
            alive = false;
            return "exit\n";
        } else if (input.equalsIgnoreCase("help")) {

            if (!playing) {
                return availableCommandsString;
            } else {
                return availableCommandsString + ". You can also " +
                        enterANumberString.substring(0, 1).toLowerCase() + enterANumberString.substring(1);
            }
        } else {
            if (input.matches("\\d+")) {
                if (playing) {
                    int currentGuess = Integer.parseInt(input);
                    if (currentGuess >= boundLow && currentGuess <= boundHigh) {
                        guesses++;
                        if (currentGuess < numberToGuess) {
                            return "X is greater than your guess";
                        } else if (currentGuess > numberToGuess) {
                            return "X is less than your guess";
                        } else {
                            playing = false;
                            return "It took " + guesses + " guesses to get the right number. " + startAGameString;
                        }
                    } else {
                        return "You have entered an invalid number. " + enterANumberString;
                    }
                } else {
                    return "You have not started a new game. " + startAGameString;
                }
            } else {
                return "You have entered an invalid command. Enter 'help' to see all available commands.";
            }
        }
    }


    private void startNewGame() {
        numberToGuess = ThreadLocalRandom.current().nextInt(boundLow, boundHigh + 1);
        playing = true;
        guesses = 0;
        System.out.println("New game:\nX is " + numberToGuess);
    }

    public boolean isAlive() {
        return alive;
    }
}
