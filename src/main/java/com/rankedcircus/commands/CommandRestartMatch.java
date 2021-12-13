package com.rankedcircus.commands;

import com.rankedcircus.Log;
import com.rankedcircus.overwatchclient.ClientFunctions;

public class CommandRestartMatch extends Command
{
    @Override
    public void execute()
    {
        Log.CLog("CommandRestartMatch", "Received command and executing.");
        ClientFunctions.restartOngoingMatch();
    }
}
