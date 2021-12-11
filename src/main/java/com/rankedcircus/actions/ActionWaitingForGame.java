package com.rankedcircus.actions;

import java.util.ArrayList;

public class ActionWaitingForGame extends Action {

    public ActionWaitingForGame() {
        this.name = "WaitingForGame";
        this.queue = new ArrayList<ActionPoint>();
    }
}
