package com.rankedcircus.commands;

import com.rankedcircus.CApplication;
import com.rankedcircus.Keyboard;

public class CommandSayHi extends Command
{
    public CommandSayHi()
    {
        this.setGlobalCooldown("circus.cmd.say_hi.cooldown");
    }

    @Override
    public void execute()
    {
        Keyboard.sendChat("Hello xD");
        CApplication.getInstance().sleepFor(1000);
        Keyboard.sendChat("Reyzr has aids.");
    }
}
