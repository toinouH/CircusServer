package com.rankedcircus.actions;

import com.rankedcircus.Keyboard;
import com.rankedcircus.Mouse;

import java.util.ArrayList;

public class ActionInvitePlayers extends Action
{
    public ActionInvitePlayers()
    {
        this.name = "InvitePlayers";
    }

    // War crime function fix it
    public ActionInvitePlayers inviteTeam(ArrayList<String> players, int team)
    {
        for (String player : players)
        {
            System.out.println("Inviting " + player);
            // Clean up sending clicks.
            try
            {
                Mouse.getInstance().moveThenClick(1654, 314);
                Thread.sleep(1000);
                Mouse.getInstance().moveThenClick(700, 780);

                if (team == 1)
                    Mouse.getInstance().moveThenClick(686, 882);
                if (team == 2)
                    Mouse.getInstance().moveThenClick(686, 925);

                Thread.sleep(1000);
                Mouse.getInstance().moveThenClick(1200, 300);
                Thread.sleep(1000);

                // Iterate over every character in each battletag
                Keyboard.sendString(player);

                Mouse.getInstance().moveThenClick(1000, 870);

                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        return this;
    }

    @Override
    public void execute() { }
}
