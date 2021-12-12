package com.rankedcircus.commands;

import com.rankedcircus.Keyboard;
import com.rankedcircus.Log;

public abstract class Command
{
    // Eventually there will be many more commands other than pause that will
    // justify a parent class.
    // For now, there's not. Tragic.
    public void sendResponse(String response)
    {
        Keyboard.sendString(response);
    }

    public void execute()
    {
        Log.CLog("Command", "Received and executing command.");
    }
}
