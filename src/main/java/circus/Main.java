package circus;


import circus.api.Api;
import circus.api.Player;
import circus.imaging.Imaging;
import circus.imaging.Tess;
import circus.server.Server;
import net.sourceforge.tess4j.TesseractException;
import org.opencv.core.Core;


import java.awt.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, AWTException, TesseractException, InterruptedException {
        // Load the OpenCV dll
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Instantiate necessary objects prior to moving forward
        Imaging imaging = new Imaging();
        Tess tess = new Tess();

        System.out.println(tess.readBufferedImage(imaging.captureMenuPlayButton()));
        Thread.sleep(3000);
        System.out.println(tess.readBufferedImage(imaging.captureFindGroupButton()));

        // Testing of converting api responses to Player object
        Api api = new Api();
        Player playerWithIdOfFour = api.getPlayer( 4 );
        System.out.println( String.format( "%s has a rating of %s.",
                playerWithIdOfFour.getName(),
                playerWithIdOfFour.getRating()) );

        // Testing server
        Server server = new Server();
    }
}
