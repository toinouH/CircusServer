package com.rankedcircus.actions;

import com.rankedcircus.CApplication;
import com.rankedcircus.Mouse;

import java.util.ArrayList;

public abstract class Action
{
    ArrayList<ActionPoint> queue;
    String name;

    public void execute()
    {
        if (queue == null)
            return;

        for (ActionPoint ap : queue)
        {
            Mouse.getInstance().moveThenClick(ap.getX(), ap.getY());
            CApplication.getInstance().sleepFor( 250 );
        }

        System.out.println("[MatchServer] Executing Action" + this.name);
    }
}
