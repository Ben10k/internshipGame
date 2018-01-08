package lt.taurosevicius.game.server;

public class BackEnd {

    public static void main(String args[]) {
        System.out.println("I am back-end");
        // creates a new server based on args
        Server server;


        if (args.length == 1) {
            if (args[0].matches("\\d+"))
                server = new Server(Integer.parseInt(args[0]));
            else
                server = new Server();
        } else {
            server = new Server();
        }

        // starts the back-end server
        server.startServer();

    }

}
