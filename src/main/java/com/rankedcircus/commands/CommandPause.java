package com.rankedcircus.commands;

import com.rankedcircus.CApplication;
import com.rankedcircus.Log;
import com.rankedcircus.SRobot;

import java.awt.event.KeyEvent;

public class CommandPause extends Command
{
    @Override
    public void execute()
    {
        Log.CLog("CommandPause", "Received and executing command");

        // Ensure chat window is closed before sending pause command.
        CApplication.getInstance().closeChatIfOpen();

        SRobot.getRobot().setAutoDelay( 150 );

        // Send pause command.
        // TODO: Abstract this into a new class with the rest of client keybinds as Actions.
        SRobot.getRobot().keyPress(KeyEvent.VK_EQUALS);
        SRobot.getRobot().keyRelease(KeyEvent.VK_EQUALS);

        SRobot.getRobot().setAutoDelay( 100 );

        // Send response message to players.
        CApplication.getInstance().openChatIfClosed();
        this.sendResponse("Executed command.");
        CApplication.getInstance().closeChatIfOpen();
    }
}
