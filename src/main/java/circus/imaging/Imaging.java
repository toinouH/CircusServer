package circus.imaging;

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

public class Imaging {

    private final Position MENU_PLAY_BUTTON = new Position(34, 200, 150, 95, 600, 300);
    private final Position FIND_GROUP_BUTTON = new Position(885, 920, 145, 40, 600, 200);
    private final Position FILTER_GAMES_BUTTON = new Position(1116, 204, 80, 40, 600, 200);
    private final Position START_MATCH_BUTTON = new Position(872, 883, 100, 60, 600, 200);
    private final Position MOVE_AND_DONE_BUTTON = new Position(1339, 323, 100, 80, 300, 150);
    private final Position BLUE_TEAM_LOBBY_START = new Position(291, 461, 100, 80, 300, 150);
    private final Position CIRCUS_PRESET_1 = new Position(123, 261, 250, 100, 300, 150);

    private static Mat BufferedImage2Mat(BufferedImage image) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", byteArrayOutputStream);
        byteArrayOutputStream.flush();
        return Imgcodecs.imdecode(new MatOfByte(byteArrayOutputStream.toByteArray()), Imgcodecs.IMREAD_UNCHANGED);
    }

    private static BufferedImage Mat2BufferedImage(Mat matrix)throws IOException {
        MatOfByte mob=new MatOfByte();
        Imgcodecs.imencode(".jpg", matrix, mob);
        return ImageIO.read(new ByteArrayInputStream(mob.toArray()));
    }

    private static BufferedImage imageScreen(Position position) throws AWTException, IOException {
        // Construct rectangle of dimensions using position param
        Rectangle screenRect = new Rectangle(
                position.getX(),
                position.getY(),
                position.getWidth(),
                position.getHeight()
        );
        // Capture rectangle of screen using rectangle object we just constructed
        BufferedImage capture = new Robot().createScreenCapture(screenRect);
        // Declare & Define new Mat object for any preprocessing/resizing
        Mat flippedBuffer = BufferedImage2Mat(capture);
        // Instantiate Size object with defined values from whichever Position object we're working with
        Size size = new Size(position.getResizeW(), position.getResizeH());
        // This isn't common in Java, but OpenCV was written in C. "resize" sets "pre" to the output of "resize"
        Imgproc.resize(flippedBuffer, flippedBuffer, size, 2, 2, Imgproc.INTER_AREA);
        // cvtColor tends to help Tesseract output proper readings
        Imgproc.cvtColor(flippedBuffer, flippedBuffer, Imgproc.COLOR_BGR2RGB);
        // medianBlur greatly increases Tesseract's output accuracy
        Imgproc.medianBlur(flippedBuffer, flippedBuffer, 3);
        // Flip the resized Mat back to a BufferedImage and return it to the caller
        return Mat2BufferedImage(flippedBuffer);
    }

    public BufferedImage captureMenuPlayButton() throws AWTException, IOException {
        return imageScreen(this.MENU_PLAY_BUTTON);
    }

    public BufferedImage captureFindGroupButton() throws AWTException, IOException {
        return imageScreen(this.FIND_GROUP_BUTTON);
    }

    public BufferedImage captureFilterGamesButton() throws AWTException, IOException {
        return imageScreen(this.FILTER_GAMES_BUTTON);
    }

    public BufferedImage captureLobbyStartButton() throws AWTException, IOException {
        return imageScreen(this.START_MATCH_BUTTON);
    }

    public BufferedImage captureDoneAndMoveButton() throws AWTException, IOException {
        return imageScreen(this.MOVE_AND_DONE_BUTTON);
    }

    public BufferedImage captureBlueTeamSlot() throws AWTException, IOException {
        return imageScreen(this.BLUE_TEAM_LOBBY_START);
    }

    public BufferedImage captureCircusPreset() throws AWTException, IOException {
        return imageScreen(this.CIRCUS_PRESET_1);
    }
}
