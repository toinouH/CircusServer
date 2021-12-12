package com.rankedcircus.actions;

import java.util.ArrayList;

public class ActionStartGame extends Action
{
    public ActionStartGame()
    {
        this.name = "StartGame";
        this.queue = new ArrayList<ActionPoint>() {{
            add(new ActionPoint(917, 913));
        }};
    }
}
