package circus.actions;

import circus.CFunctions;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class ActionSetMap extends Action {
    private Map map;
    private Robot robot;

    public ActionSetMap() {
        this.name = "SetMap";
        this.queue = new ArrayList<ActionPoint>() {{
            new ActionPoint(1338, 207);
        }};

        try {
            this.robot = new Robot();
            this.robot.setAutoDelay(55);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public ActionSetMap setMap(Map map) {
        this.map = map;
        return this;
    }

    public ActionSetMap changeMap() {
        // Lobby settings
        CFunctions.clickPos(1533, 313);
        // Maps menu
        CFunctions.clickPos(319, 541);
        // None button
        CFunctions.clickPos(1317, 204);

        // Starting at first map in list (Hanamura)
        this.robot.keyPress(KeyEvent.VK_DOWN);
        this.robot.keyRelease(KeyEvent.VK_DOWN);

        // TODO: Abstract keyPress/keyRelease so we don't have to manually delay each press
        for (int i = 1; i <= this.map.getId() - 1; i++) {
            this.robot.keyPress(KeyEvent.VK_DOWN);
            this.robot.keyRelease(KeyEvent.VK_DOWN);
        }

        this.robot.keyPress(KeyEvent.VK_RIGHT);
        this.robot.keyRelease(KeyEvent.VK_RIGHT);

        this.robot.keyPress(KeyEvent.VK_ESCAPE);
        this.robot.keyRelease(KeyEvent.VK_ESCAPE);
        this.robot.keyPress(KeyEvent.VK_ESCAPE);
        this.robot.keyRelease(KeyEvent.VK_ESCAPE);

        return this;
    }

    @Override
    public void execute() {
    }
}
