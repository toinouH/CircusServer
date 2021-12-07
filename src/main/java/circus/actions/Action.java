package circus.actions;

import circus.Mouse;

import java.util.ArrayList;

public abstract class Action
{
    ArrayList<ActionPoint> queue;
    String name;

    public void execute() throws InterruptedException
    {
        if (queue == null)
            return;

        for (ActionPoint ap : queue)
        {
            Mouse.getInstance().moveThenClick(ap.getX(), ap.getY());
            Thread.sleep(250);
        }

        System.out.println("[MatchServer] Executing Action" + this.name);
    }
}
