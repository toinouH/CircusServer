package com.rankedcircus.actions;

import com.rankedcircus.Mouse;

import java.util.ArrayList;

public class ActionSetPreset extends Action {
    public ActionSetPreset() {
        this.name = "SetPreset";
        this.queue = new ArrayList<ActionPoint>() {{
            add(new ActionPoint(1538, 307));
            add(new ActionPoint(310, 327));
            add(new ActionPoint(239, 353)); // 5th preset entry. TODO: Move this to local configuration file.
            add(new ActionPoint(1028, 661));
            add(new ActionPoint(1800, 1030));
            add(new ActionPoint(1800, 1030));
        }};
    }

    @Override
    public void execute()
    {
        for (ActionPoint ap : this.queue) {
            Mouse.getInstance().moveThenClick(ap.getX(), ap.getY());
            try {
                Thread.sleep(2100); // Blizzard server response time..
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("[MatchServer] Server created. Waiting for players.");

    }
}
