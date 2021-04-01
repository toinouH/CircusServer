package circus.actions;

import circus.CFunctions;

import java.util.ArrayList;

public class ActionSetPreset extends Action {
    public ActionSetPreset() {
        this.name = "SetPreset";
        this.queue = new ArrayList<ActionPoint>() {{
            add(new ActionPoint(1538, 307));
            add(new ActionPoint(310, 327));
            add(new ActionPoint(268, 311));
            add(new ActionPoint(1028, 661));
            add(new ActionPoint(1800, 1030));
            add(new ActionPoint(1800, 1030));
        }};
    }

    @Override
    public void execute() {
        for (ActionPoint ap : this.queue) {
            CFunctions.clickPos(ap.getX(), ap.getY());
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(String.format("[MatchServer] Server created. Waiting for players.", this.name));
    }
}
