package com.rankedcircus.actions;

import java.util.ArrayList;

public class ActionPlay extends Action {
    public ActionPlay() {
        this.name = "Play";
        this.queue = new ArrayList<ActionPoint>() {{
            add(new ActionPoint(155, 247));
        }};
    }
}
