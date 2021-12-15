package com.rankedcircus.actions;

import java.util.ArrayList;

public class ActionGameBrowser extends Action
{
    public ActionGameBrowser()
    {
        this.name = "GameBrowser";
        this.queue = new ArrayList<ActionPoint>() {{
            // Click game browser menu
            add(new ActionPoint(1442, 541));
            // Click "All Games", since Blizzard turned the game browser into a social media platform.
            add(new ActionPoint(600, 248));
        }};
    }
}
