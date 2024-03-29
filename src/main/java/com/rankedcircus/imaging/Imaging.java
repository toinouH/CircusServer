package com.rankedcircus.imaging;

import com.rankedcircus.CApplication;
import com.rankedcircus.SRobot;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Imaging
{
    // Hard coded positions for 1920x1080.
    // TODO: Provide config file support for smaller resolutions.
    private static final Position MENU_PLAY_BUTTON      = new Position(40, 210, 130, 75, 600, 300);
    private static final Position FIND_GROUP_BUTTON     = new Position(875, 910, 155, 70, 600, 200);
//    private static final Position FILTER_GAMES_BUTTON = new Position(1108, 224, 92, 60, 800, 200);
    private static final Position CREATE_GAME_BUTTON    = new Position(1680, 230, 130, 50, 800, 200);
    private static final Position START_MATCH_BUTTON    = new Position(872, 883, 100, 60, 600, 200);
    private static final Position MOVE_AND_DONE_BUTTON  = new Position(1339, 323, 100, 80, 300, 150);
    private static final Position BLUE_TEAM_LOBBY_START = new Position(291, 461, 100, 80, 300, 150);
    private static final Position CIRCUS_PRESET_1       = new Position(123, 261, 250, 100, 300, 150);
    private static final Position CHAT_SECTOR           = new Position(35, 658, 440, 35, 900, 60);

    //-------------------------------------------------------------------------
    // Publicly accessible positions are unrelated to any Action children.
    // - Most will be used in places like ClientFunctions.java
    //-------------------------------------------------------------------------
    public static final Position SHOW_LOBBY            = new Position(1110, 500, 225, 35, 600, 60);
    public static final Position DISPLAY_MODE          = new Position(1185, 190, 400, 40, 800, 60);

    private static Mat BufferedImage2Mat(BufferedImage image)
    {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", byteArrayOutputStream);
            byteArrayOutputStream.flush();
            return Imgcodecs.imdecode(new MatOfByte(byteArrayOutputStream.toByteArray()), Imgcodecs.IMREAD_UNCHANGED);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static BufferedImage Mat2BufferedImage(Mat matrix)
    {
        try {
            MatOfByte mob = new MatOfByte();
            Imgcodecs.imencode(".jpg", matrix, mob);
            return ImageIO.read(new ByteArrayInputStream(mob.toArray()));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static BufferedImage imageScreen(Position position, boolean useGray )
    {
        // Construct rectangle of dimensions using position param
        Rectangle screenRect = new Rectangle(
                position.getX(),
                position.getY(),
                position.getWidth(),
                position.getHeight()
        );
        // Capture rectangle of screen using rectangle object we just constructed
        BufferedImage capture = SRobot.getRobot().createScreenCapture(screenRect);
        // Declare & Define new Mat object for any preprocessing/resizing
        Mat flippedBuffer = BufferedImage2Mat(capture);
        // Instantiate Size object with defined values from whichever Position object we're working with
        Size size = new Size(position.getResizeW(), position.getResizeH());
        // This isn't common in Java, but OpenCV was written in C. "resize" sets "pre" to the output of "resize"
        Imgproc.resize(flippedBuffer, flippedBuffer, size, 2, 2, Imgproc.INTER_AREA);
        // cvtColor tends to help Tesseract output proper readings
        if ( useGray )
            Imgproc.cvtColor(flippedBuffer, flippedBuffer, Imgproc.COLOR_BGR2GRAY);
        else
            Imgproc.cvtColor(flippedBuffer, flippedBuffer, Imgproc.COLOR_BGR2RGB);
        // medianBlur greatly increases Tesseract's output accuracy
        Imgproc.medianBlur(flippedBuffer, flippedBuffer, 3);
        // Flip the resized Mat back to a BufferedImage and return it to the caller
        return Mat2BufferedImage(flippedBuffer);
    }

    public static BufferedImage captureMenuPlayButton() {
        return imageScreen(MENU_PLAY_BUTTON, false);
    }

    public static BufferedImage captureFindGroupButton() {
        return imageScreen(FIND_GROUP_BUTTON, false);
    }

    public static BufferedImage captureCreateGameButton() {
        return imageScreen(CREATE_GAME_BUTTON, false);
    }

    public static BufferedImage captureLobbyStartButton() {
        return imageScreen(START_MATCH_BUTTON, false);
    }

    public static BufferedImage captureDoneAndMoveButton() {
        return imageScreen(MOVE_AND_DONE_BUTTON, false);
    }

    public static BufferedImage captureBlueTeamSlot() {
        return imageScreen(BLUE_TEAM_LOBBY_START, false);
    }

    public static BufferedImage captureCircusPreset() {
        return imageScreen(CIRCUS_PRESET_1, false);
    }

    public static BufferedImage captureDisplayMode() {
        return imageScreen(DISPLAY_MODE, false);
    }

    public static BufferedImage captureShowLobby() {
        return imageScreen(SHOW_LOBBY, false);
    }

    // TODO: Think about whether or not I want to throw exceptions on the function or include try/catch.
    public static BufferedImage captureChatSector()
    {
        BufferedImage ret;
        CApplication.getInstance().openChatIfClosed();
        ret = imageScreen( CHAT_SECTOR, true );
        CApplication.getInstance().sleepFor( 200 );
        CApplication.getInstance().closeChatIfOpen();
        return ret;
    }
}
