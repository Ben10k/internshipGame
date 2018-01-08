package lt.taurosevicius.game.client;

public class ClientHandler {
    private String host;
    private int port;
    private String uiType;
    private Client client;
    private String status;

    public ClientHandler(String host, int port, String uiType) {
        this.host = host;
        this.port = port;
        this.uiType = uiType;
    }

    public void begin() {
        client = selectClient(uiType);
        client.setupConnection(host, port);
        status = client.getStatus();
        if (status.equals("Connection established")) {
            client.initGame();
            client.playGame();
            client.terminateConnection();
            status = "Successful exit";
        }
        else {
            System.out.println("Server not found");
        }
    }

    public String getStatus() {
        return status;
    }

    // Selects which client to use based on uiType
    private Client selectClient(String uiType) {
        switch (uiType) {
            case "console":
                return new ConsoleClient();
            case "swing":
                return new SwingClient();
            default:
                return new ConsoleClient();
        }
    }

}
