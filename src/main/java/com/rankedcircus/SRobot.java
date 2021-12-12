package com.rankedcircus;

import java.awt.*;

public class SRobot
{
    private static Robot robot;

    static
    {
        // ?Java
        try
        {
            robot = new Robot();
            robot.setAutoDelay(50);
        }
        catch (AWTException e)
        {
            e.printStackTrace();
        }
    }

    public static Robot getRobot()
    {
        return robot;
    }
}
