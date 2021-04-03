package circus.actions;

import circus.CFunctions;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class ActionInvitePlayers extends Action {
    private Robot robot;

    public ActionInvitePlayers() {
        this.name = "InvitePlayers";
        try {
            this.robot = new Robot();
            this.robot.setAutoDelay(5);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public ActionInvitePlayers inviteTeam(ArrayList<String> players, int team) {
        for (String player : players) {
            try {
                CFunctions.clickPos(1654, 314);
                CFunctions.clickPos(900, 786);
                if (team == 1)
                    CFunctions.clickPos(866, 896);
                if (team == 2)
                    CFunctions.clickPos(824, 926);
                Thread.sleep(100);
                CFunctions.clickPos(1234, 305);
                Thread.sleep(200);
                for (char c : player.toCharArray()) {
                    if (c == '#') {
                        this.robot.keyPress(KeyEvent.VK_SHIFT);
                        this.robot.keyPress('3');
                        this.robot.keyRelease('3');
                        this.robot.keyRelease(KeyEvent.VK_SHIFT);
                    }
                    if (Character.isUpperCase(c)) {
                        this.robot.keyPress(KeyEvent.VK_SHIFT);
                        this.robot.keyPress(c);
                        this.robot.keyRelease(c);
                        this.robot.keyRelease(KeyEvent.VK_SHIFT);
                    } else {
                        c = Character.toUpperCase(c);
                        this.robot.keyPress(c);
                        this.robot.keyRelease(c);
                    }
                }
                CFunctions.clickPos(978, 859);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    @Override
    public void execute() { }
}
