package com.rankedcircus;

import java.awt.*;
import java.awt.event.InputEvent;

public class Mouse
{
    private static Mouse mouse; // Singleton instance
    private static Robot robot;

    // Lol java
    static
    {
        mouse = new Mouse();
    }

    public void snapMove(int x, int y)
    {
        SRobot.getRobot().mouseMove(x, y);
    }

    public void snapRelative(int x, int y)
    {
        Point pos = MouseInfo.getPointerInfo().getLocation();
        SRobot.getRobot().mouseMove(pos.x + x, pos.y + y);
    }

    public void click()
    {
        SRobot.getRobot().mousePress(InputEvent.BUTTON1_DOWN_MASK);
        SRobot.getRobot().mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
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
