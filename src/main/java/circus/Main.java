package circus;


import circus.imaging.Tess;
import net.sourceforge.tess4j.TesseractException;
import org.opencv.core.Core;


import java.awt.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, AWTException, TesseractException, InterruptedException {
        // Load the OpenCV dll
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Instantiate necessary objects prior to moving forward
        Tess tess = new Tess();
        StateHandler stateHandler = new StateHandler();

        while (true) {
            try {
                stateHandler.getCurrentState().goNextState();
            } catch (NullPointerException e) {
                System.out.println("We've reached the end of the road..");
            }
            Thread.sleep(10);
        }


        // Testing of converting api responses to Player object
//        Api api = new Api();
//        Player playerWithIdOfFour = api.getPlayer( 4 );
//        System.out.println( String.format( "%s has a rating of %s.",
//                playerWithIdOfFour.getName(),
//                playerWithIdOfFour.getRating()) );
//
        // Testing server
//        Server server = new Server();
    }
}
