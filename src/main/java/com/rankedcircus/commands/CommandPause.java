package com.rankedcircus.commands;

import com.rankedcircus.CApplication;
import com.rankedcircus.Log;
import com.rankedcircus.SRobot;

import java.awt.event.KeyEvent;

public class CommandPause extends Command
{
    public CommandPause()
    {
        this.setName("Pause");
        this.setGlobalCooldown("circus.cmd.pause.global_cooldown");
    }

    @Override
    public void execute()
    {
        if ( this.isOnCooldown() )
        {
            Log.CLog("CommandPause", "This command is currently on cooldown.");
            this.sendResponse("That command is on cooldown.");
            return;
        }

        this.setLastExecutedTimestamp();

        // Ensure chat window is closed before sending pause command.
        CApplication.getInstance().closeChatIfOpen();
        CApplication.getInstance().sleepFor( 100 );

        // Send pause command.
        // TODO: Abstract this into a new class with the rest of client keybinds as Actions.
        // TODO: Also pull the pause keybind from circus.config.
        SRobot.getRobot().setAutoDelay( 150 );
        SRobot.getRobot().keyPress(KeyEvent.VK_EQUALS);
        SRobot.getRobot().keyRelease(KeyEvent.VK_EQUALS);
        SRobot.getRobot().setAutoDelay( 100 );

        // Send response message to players.
        this.sendResponse("Executed command.");

        Log.CLog("CommandPause", "Received and executing command");
    }
}
