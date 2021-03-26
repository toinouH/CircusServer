package circus;


import circus.api.Api;
import circus.api.Player;
import circus.server.Server;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        // Testing of converting api responses to Player object
        Api api = new Api();
        Player playerWithIdOfFour = api.getPlayer( 4 );
        System.out.println( String.format(
               "%s has a rating of %s.",
                playerWithIdOfFour.getName(), playerWithIdOfFour.getRating())
        );

        // Testing server
        Server server = new Server();
    }
}
