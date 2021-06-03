package circus.actions;

import circus.CFunctions;
import circus.Keyboard;

import java.awt.*;
import java.awt.event.KeyEvent;
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
            // Clean up sending clicks.
            try {
                CFunctions.clickPos(1654, 314);
                CFunctions.clickPos(900, 786);
                if (team == 1)
                    CFunctions.clickPos(866, 896);
                if (team == 2)
                    CFunctions.clickPos(824, 926);
                Thread.sleep(100);
                CFunctions.clickPos(1234, 305);
                Thread.sleep(200);

                // Iterate over every character in each battletag
                Keyboard.sendString(this.robot, player);

                CFunctions.clickPos(978, 859);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    @Override
    public void execute() { }
}
