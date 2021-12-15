package com.rankedcircus;

import com.rankedcircus.commands.*;

public class CommandHandler
{
    public Command commandPause         = new CommandPause();
    public Command commandCurrentGame   = new CommandCurrentGame();
    public Command commandCurrentTeams  = new CommandCurrentTeams();
    public Command commandRestartMatch  = new CommandRestartMatch();
    public Command commandSayHi         = new CommandSayHi();

    public void intake(String input)
    {
        //------------------------------------------------------------------------------
        // Rather than including a name in constructor of commands, reckon it's better
        // to include documentation of each Command.java file in the form of:
        // - Name
        // - Description
        // - Trigger
        //------------------------------------------------------------------------------
        String prefix = Config.getConfig().read("circus.cmd.prefix");

        if ( input.contains(prefix + "pause") )
        {
            this.commandPause.execute();
        }
        else if ( input.contains(prefix + "this game") )
        {
            this.commandCurrentGame.execute();
        }
        else if ( input.contains(prefix + "teams") )
        {
            this.commandCurrentTeams.execute();
        }
        else if ( input.contains(prefix + "restart") )
        {
            this.commandRestartMatch.execute();
        }
        else if ( input.contains(prefix + "hi") )
        {
            this.commandSayHi.execute();
        }
    }
}
