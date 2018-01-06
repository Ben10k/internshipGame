package lt.taurosevicius.game.server;

public class BackEnd {

    public static void main(String args[]){
        System.out.println("I am back-end");
        // creates a new connectionHandler based on args
        ConnectionHandler connectionHandler =
                ((args.length == 1 && args[0].matches("\\d+"))
                        ? new ConnectionHandler()
                        : new ConnectionHandler(Integer.parseInt(args[0])));
        // starts the back-end server
        connectionHandler.startServer();

    }

}
