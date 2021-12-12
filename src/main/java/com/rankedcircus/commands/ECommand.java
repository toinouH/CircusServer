package com.rankedcircus.commands;

// This is unused. Maybe in the future.
public enum ECommand
{
    PAUSE(new CommandPause());

    private final Command command;

    ECommand(Command command)
    {
        this.command = command;
    }

    public void executeCommand()
    {
        this.command.execute();
    }
}
