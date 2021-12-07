package circus;

import java.awt.*;
import java.awt.event.InputEvent;

public class Mouse {
    private static Mouse mouse; // Singleton instance
    private static Robot robot;


    // Lol java
    static {
        try {
            mouse = new Mouse();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private Mouse() throws AWTException
    {
        robot = new Robot();
    }

    public void snapMove(int x, int y)
    {
        robot.mouseMove(x, y);
    }

    public void snapRelative(int x, int y)
    {
        Point pos = MouseInfo.getPointerInfo().getLocation();
        robot.mouseMove(pos.x + x, pos.y + y);
    }

    public void click()
    {
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    public void moveThenClick(int x, int y)
    {
        this.snapMove(x, y);
        this.click();
    }

    public static Mouse getInstance()
    {
        return mouse;
    }
}
