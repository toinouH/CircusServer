package com.rankedcircus;

import com.rankedcircus.imaging.Position;
import com.rankedcircus.imaging.Tess;

import java.awt.event.KeyEvent;

// Global class-ish
// Singleton
public class CApplication
{
    private static final CApplication cApplication;
    private boolean isChatOpen = false;

    static
    {
        cApplication = new CApplication();
    }

    public static CApplication getInstance()
    {
        return cApplication;
    }

    public void setChatState(boolean newState)
    {
        Keyboard.sendKey( (char) KeyEvent.VK_ENTER );
        isChatOpen = newState;
    }

    public void sleepFor(int ms)
    {
        try {
            Thread.sleep( ms );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void closeChatIfOpen()
    {
        if ( this.isChatOpen )
        {
            this.setChatState(false);
        }
    }

    public void openChatIfClosed()
    {
        if ( !this.isChatOpen )
        {
            this.setChatState(true);
        }
    }

    public boolean sectorContainsString(Position captureSector, String isContained)
    {
        return Tess.getInstance().readSingleLine(captureSector.capture()).contains(isContained);
    }

}
