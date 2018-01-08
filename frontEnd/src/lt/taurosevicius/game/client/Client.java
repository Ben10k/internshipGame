package lt.taurosevicius.game.client;

public interface Client {
    void initGame();

    void playGame();

    void setupConnection(String host, int port);

    void terminateConnection();

    String getStatus();
}
