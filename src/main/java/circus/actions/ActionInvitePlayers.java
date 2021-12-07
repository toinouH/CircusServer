package circus.actions;

import circus.Keyboard;
import circus.Mouse;

import java.awt.*;
import java.util.ArrayList;

public class ActionInvitePlayers extends Action
{
    // TODO: Make sure we actually need to instantiate this here.
    // We might already have a reference to another Robot in another class that we can pass in.
    private Robot robot;

    public ActionInvitePlayers()
    {
        this.name = "InvitePlayers";
        try
        {
            this.robot = new Robot();
            this.robot.setAutoDelay(5);
        }
        catch (AWTException e)
        {
            e.printStackTrace();
        }
    }

    // War crime function fix it
    public ActionInvitePlayers inviteTeam(ArrayList<String> players, int team)
    {
        for (String player : players) {
            System.out.println("Inviting " + player);
            // Clean up sending clicks.
            try
            {
                System.out.println("Clicking invite");
                Mouse.getInstance().moveThenClick(1654, 314);
                System.out.println("Clicking team dropdown");
                Thread.sleep(1000);
                Mouse.getInstance().moveThenClick(700, 780);
                System.out.println("Clicking team 1 or 2");
                if (team == 1)
                    Mouse.getInstance().moveThenClick(686, 882);
                if (team == 2)
                    Mouse.getInstance().moveThenClick(686, 925);
                Thread.sleep(1000);
                System.out.println("Clicking via battle-tag.");
                Mouse.getInstance().moveThenClick(1200, 300);
                Thread.sleep(1000);

                // Iterate over every character in each battletag
                System.out.println("Sending string");
                Keyboard.sendString(this.robot, player);

                System.out.println("Clicking invite");
                Mouse.getInstance().moveThenClick(1000, 870);

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    @Override
    public void execute() { }
}
