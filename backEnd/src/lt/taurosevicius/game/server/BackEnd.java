package lt.taurosevicius.game.server;

public class BackEnd {

    public static void main(String args[]) {
        System.out.println("I am back-end");
        // creates a new connectionHandler based on args
        ConnectionHandler connectionHandler;


        if (args.length == 1) {
            if (args[0].matches("\\d+"))
                connectionHandler = new ConnectionHandler(Integer.parseInt(args[0]));
            else
                connectionHandler = new ConnectionHandler();
        } else {
            connectionHandler = new ConnectionHandler();
        }

        // starts the back-end server
        connectionHandler.startServer();

    }

}
