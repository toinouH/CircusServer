package com.rankedcircus.actions;

import com.rankedcircus.Keyboard;
import com.rankedcircus.Mouse;
import com.rankedcircus.SRobot;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class ActionSetMap extends Action
{
    private Map map;

    public ActionSetMap()
    {
        this.name = "SetMap";
        this.queue = new ArrayList<ActionPoint>() {{

            new ActionPoint(1338, 207);
        }};
    }

    public ActionSetMap setMap(Map map)
    {
        this.map = map;
        return this;
    }

    public void changeMap()
    {
        System.out.println("changeMap called.");
        SRobot.getRobot().setAutoDelay(75);

        // Lobby settings
        Mouse.getInstance().moveThenClick(1533, 313);
        // Maps menu
        Mouse.getInstance().moveThenClick(319, 541);
        // None button
        Mouse.getInstance().moveThenClick(1317, 204);

        for (int i = 0; i <= this.map.getId() - 1; i++)
        {
            Keyboard.sendKeyEvent(KeyEvent.VK_DOWN);
        }

        Keyboard.sendKeyEvent(KeyEvent.VK_RIGHT);
        Keyboard.sendRepeatedInput(KeyEvent.VK_ESCAPE, 2, 55);

    }

    @Override
    public void execute() {}
}
