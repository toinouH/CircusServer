package com.rankedcircus;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.getExtendedKeyCodeForChar;

public class Keyboard
{
    private static boolean isSpecialChar(char c)
    {
        if ( Character.isUpperCase(c) )
            return true;

        switch ( c )
        {
            case '!':
            case '@':
            case '#':
            case '$':
            case '%':
            case '^':
            case '&':
            case '*':
            case '(':
            case ')':
                return true;
            default:
                return false;
        }
    }

    private static void sendKey(Robot robot, char key)
    {
        robot.keyPress(getExtendedKeyCodeForChar(key));
        robot.keyRelease(getExtendedKeyCodeForChar(key));
    }

    private static void sendModifiedKey(Robot robot, char key)
    {
        robot.keyPress(KeyEvent.VK_SHIFT);
        Keyboard.sendKey(robot, key);
        robot.keyRelease(KeyEvent.VK_SHIFT);
    }

    public static void sendString(Robot robot, String intake)
    {
        for ( char c : intake.toCharArray() )
        {
            if ( Keyboard.isSpecialChar(c) )
            {
                // This is a hack, and I hate it, but I hate the Java KeyEvent enum more.
                if (c == '#')
                    c = '3';

                Keyboard.sendModifiedKey(robot, c);
                continue;
            }
            Keyboard.sendKey(robot, c);
        }
    }
}