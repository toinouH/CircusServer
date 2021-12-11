package com.rankedcircus.actions;

import java.util.ArrayList;

public class ActionMoveToSpec extends Action {
    public ActionMoveToSpec() {
        this.name = "MoveToSpec";
        this.queue = new ArrayList<ActionPoint>() {{
            add(new ActionPoint(1406, 315));
            add(new ActionPoint(364, 473));
            add(new ActionPoint(1428, 468));
            add(new ActionPoint(1404, 331));
        }};
    }
}
