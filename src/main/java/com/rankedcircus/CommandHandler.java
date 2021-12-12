package com.rankedcircus;

import com.rankedcircus.commands.Command;
import com.rankedcircus.commands.CommandCurrentGame;
import com.rankedcircus.commands.CommandPause;

public class CommandHandler
{
    public Command commandPause         = new CommandPause();
    public Command commandCurrentGame   = new CommandCurrentGame();

    public void intake(String input)
    {
        // Rather than including a name in constructor of commands, reckon it's better
        // to include documentation of each Command.java file in the form of:
        // - Name
        // - Description
        // - Trigger
        input = input.toLowerCase();

        if ( input.contains("pause") )
        {
            this.commandPause.execute();
        }
        else if ( input.contains("this game") )
        {
            this.commandCurrentGame.execute();
        }
    }
}
