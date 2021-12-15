package com.rankedcircus.commands;

import com.rankedcircus.CApplication;
import com.rankedcircus.Keyboard;
import com.rankedcircus.Log;
import com.rankedcircus.api.Api;
import com.rankedcircus.api.Match;

public class CommandCurrentGame extends Command
{
    public CommandCurrentGame()
    {
        this.setName("CurrentGame");
        this.setGlobalCooldown("circus.cmd.current_game.global_cooldown");
    }

    @Override
    public void execute()
    {
        Log.CLog("CurrentGame","Received and executing command.");
        Match currentMatch = Api.getInstance().getMatch(4);
        CApplication.getInstance().openChatIfClosed();
        Keyboard.sendString("Current match started at " + currentMatch.getTimestamp());
        Keyboard.sendString("    Current map " + currentMatch.getMap());
        CApplication.getInstance().closeChatIfOpen();
    }
}
