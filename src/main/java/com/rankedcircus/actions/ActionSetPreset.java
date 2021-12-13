package com.rankedcircus.actions;

import com.rankedcircus.CApplication;
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
        for (int i = 0; i < this.queue.size(); i++)
        {
            // Need to wait for presets to load. For me, (NA-WEST), it takes at least 1.5.
            if ( i == 2 )
                CApplication.getInstance().sleepFor( 2100 );
            else
                CApplication.getInstance().sleepFor( 250 );

            Mouse.getInstance().moveThenClick(this.queue.get(i).getX(), this.queue.get(i).getY());
        }
        System.out.println("[MatchServer] Server created. Waiting for players.");

    }
}
