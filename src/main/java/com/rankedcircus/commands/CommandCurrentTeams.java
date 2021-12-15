package com.rankedcircus.commands;

import com.rankedcircus.Keyboard;
import com.rankedcircus.Log;
import com.rankedcircus.api.Api;
import com.rankedcircus.api.Match;

public class CommandCurrentTeams extends Command
{
    public CommandCurrentTeams()
    {
        this.setName("CurrentTeams");
    }

    @Override
    public void execute()
    {
        Log.CLog("CurrentTeams", "Received command and executing.");
        Match currentMatch = Api.getInstance().getMatch(4);

        Keyboard.sendChat("Blue team players are");

        for (String player : currentMatch.getBlueTeam())
        {
            Keyboard.sendChat(player);
        }

        Keyboard.sendChat("Red team players are");


        for (String player : currentMatch.getRedTeam())
        {
            Keyboard.sendChat(player);
        }

    }
}
