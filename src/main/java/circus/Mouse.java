package circus;

import java.awt.*;
import java.awt.event.InputEvent;

public class Mouse {

    private static Robot robot;

    public Mouse() throws AWTException {
        this.robot = new Robot();
    }

    public void snapMove(int x, int y) {
        robot.mouseMove(x, y);
    }

    public void snapRelative(int x, int y) {
        Point pos = MouseInfo.getPointerInfo().getLocation();
        robot.mouseMove(pos.x + x, pos.y + y);
    }

    public void click() {
        this.robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        this.robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
}
