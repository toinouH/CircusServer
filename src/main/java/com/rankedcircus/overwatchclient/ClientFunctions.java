package com.rankedcircus.overwatchclient;

import com.rankedcircus.CApplication;
import com.rankedcircus.Keyboard;
import com.rankedcircus.Log;
import com.rankedcircus.Mouse;
import com.rankedcircus.imaging.Imaging;

import java.awt.event.KeyEvent;


public class ClientFunctions
{
    public static void restartOngoingMatch()
    {
        Keyboard.sendChat("Executing command restart match.");
        Keyboard.sendKeyEvent(KeyEvent.VK_L);
        CApplication.getInstance().sleepFor( 150 );
        Mouse.getInstance().moveThenClick(967, 921);
        Keyboard.sendKeyEvent(KeyEvent.VK_ESCAPE);
    }

    public static void ensureBorderlessDisplay()
    {
        Keyboard.sendSequentialKeys(
            new int[] { KeyEvent.VK_ESCAPE, KeyEvent.VK_TAB, KeyEvent.VK_TAB, KeyEvent.VK_SPACE },
            50
        );
        Mouse.getInstance().moveThenClick(100, 60);

        if (CApplication.getInstance().sectorContainsString(Imaging.DISPLAY_MODE, "full"))
        {
            Log.CLog("ClientFunctions", "Changing display mode to borderless windowed.");
            Mouse.getInstance().moveThenClick(1377, 214);
            Mouse.getInstance().moveThenClick(1288, 335);
        }

        Keyboard.sendSequentialKeys(
            new int[] { KeyEvent.VK_ENTER, KeyEvent.VK_ESCAPE, KeyEvent.VK_ESCAPE },
            200
        );
    }
}
