package com.rankedcircus;

import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.getExtendedKeyCodeForChar;

public class Keyboard
{
    private static boolean isSpecialChar(char c)
    {
        if ( Character.isUpperCase(c) )
            return true;

        return switch (c) {
            case '!', '@', '#', '$', '%', '^', '&', '*', '(', ')' -> true;
            default -> false;
        };
    }

    public static void sendKey(char key)
    {
        SRobot.getRobot().keyPress(getExtendedKeyCodeForChar(key));
        SRobot.getRobot().keyRelease(getExtendedKeyCodeForChar(key));
    }

    // This is a convenience function that shouldn't necessarily be needed,
    // however, I don't think KeyEvent keycodes are 1:1 aligned with
    // the integer value for chars, for example.
    // Meaning that this becomes much more helpful and is justified.
    // If they are, this isn't justifiable and should be removed.
    // But I'm pretty sure they're not.
    public static void sendKeyEvent(int key)
    {
        SRobot.getRobot().keyPress(key);
        SRobot.getRobot().keyRelease(key);
    }

    private static void sendModifiedKey(char key)
    {
        SRobot.getRobot().keyPress(KeyEvent.VK_SHIFT);
        Keyboard.sendKey(key);
        SRobot.getRobot().keyRelease(KeyEvent.VK_SHIFT);
    }

    public static void sendRepeatedInput(int key, int repeatCount, int msDelay)
    {
        for (int i = 1; i <= repeatCount; i++)
        {
            sendKeyEvent( key );
            if ( msDelay > 0 )
                CApplication.getInstance().sleepFor( msDelay );
        }
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