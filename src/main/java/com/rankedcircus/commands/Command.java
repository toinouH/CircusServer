package com.rankedcircus.commands;

import com.rankedcircus.CApplication;
import com.rankedcircus.Config;
import com.rankedcircus.Keyboard;
import com.rankedcircus.Log;

public abstract class Command
{
    private String name;
    private int globalCooldown = Integer.parseInt(Config.getConfig().read("circus.cmd.default.global_cooldown"));
    private long lastExecuted = 0;

    // Eventually there will be many more commands other than pause that will
    // justify a parent class.
    // For now, there's not. Tragic.
    public void sendResponse(String response)
    {
        CApplication.getInstance().openChatIfClosed();
        Keyboard.sendString(response);
        CApplication.getInstance().closeChatIfOpen();
    }

    public void setGlobalCooldown(String configProperty)
    {
        Log.CLog("Command", "Command loaded configuration property: " + configProperty);
        this.globalCooldown = Integer.parseInt(Config.getConfig().read(configProperty)) * 1000;
    }

    public int getGlobalCooldown()
    {
        return this.globalCooldown;
    }

    public void setLastExecutedTimestamp()
    {
        this.lastExecuted = System.currentTimeMillis();
    }

    public void setName(String name)
    {
        this.name = "Command" + name;
    }

    public boolean isOnCooldown()
    {
        // Apparently longs can't be compared to null in Java.
        if ( lastExecuted == 0 )
            return false;

        long nowTime = System.currentTimeMillis();
        long delta = nowTime - this.lastExecuted;

        return delta <= this.globalCooldown;
    }

    public void execute()
    {
        Log.CLog("Command", "Received and executing command.");
    }
}
