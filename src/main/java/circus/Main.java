package circus;

import circus.server.MatchServer;
import org.opencv.core.Core;

import java.io.IOException;
import java.awt.Robot;
import java.awt.*;
import java.lang.InterruptedException;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        // Load the OpenCV dll
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        StateHandler stateHandler = new StateHandler();
        Thread stateHandlerThread = new Thread(stateHandler);
        stateHandlerThread.start();

        MatchServer server = new MatchServer(stateHandler);
        Thread matchServerThread = new Thread(server);
        matchServerThread.start();
    }
}
