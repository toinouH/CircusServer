package com.rankedcircus;

import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.getExtendedKeyCodeForChar;

public class Keyboard
{
    private static boolean isSpecialChar(char c)
    {
        if ( Character.isUpperCase(c) )
            return true;

        // TODO: Not this, I think..?
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

    public static void sendKey(char key)
    {
        SRobot.getRobot().keyPress(getExtendedKeyCodeForChar(key));
        SRobot.getRobot().keyRelease(getExtendedKeyCodeForChar(key));
    }

    private static void sendModifiedKey(char key)
    {
        SRobot.getRobot().keyPress(KeyEvent.VK_SHIFT);
        Keyboard.sendKey(key);
        SRobot.getRobot().keyRelease(KeyEvent.VK_SHIFT);
    }

    public static void sendPause()
    {
    }

    public static void sendString(String intake)
    {
        // Robot type go fast like speedracer~!!!!
        SRobot.getRobot().setAutoDelay( 15 );

        for ( char c : intake.toCharArray() )
        {
            if ( Keyboard.isSpecialChar(c) || c == ':' )
            {
                // This is a hack, and I hate it, but I hate the Java KeyEvent enum more.
                switch ( c )
                {
                    case '#': {
                        c = '3';
                        break;
                    }
                    case ':': {
                        c = ';';
                        break;
                    }
                }

                Keyboard.sendModifiedKey(c);
                continue;
            }
            Keyboard.sendKey(c);
        }

        SRobot.getRobot().setAutoDelay( 50 );
    }
}