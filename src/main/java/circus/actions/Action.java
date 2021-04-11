package circus.actions;

import circus.CFunctions;

import java.util.ArrayList;

public abstract class Action {
    ArrayList<ActionPoint> queue;
    String name;

    public void execute() {
        if (queue == null) {
            return;
        }

        for (ActionPoint ap : queue) {
            CFunctions.clickPos(ap.getX(), ap.getY());
        }

        System.out.println(String.format("[MatchServer] Executing Action%s", this.name));
    }
}
