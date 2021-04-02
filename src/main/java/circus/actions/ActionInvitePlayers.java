package circus.actions;

import circus.CFunctions;
import circus.api.Api;
import circus.api.Match;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

public class ActionInvitePlayers extends Action {
    private Api api = new Api();
    private Robot robot;

    public ActionInvitePlayers() {
        this.name = "InvitePlayers";
        try {
            this.robot = new Robot();
            this.robot.setAutoDelay(50);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private void inviteTeam(ArrayList<String> players) {
        for (String player : players) {
            try {
                CFunctions.clickPos(1654, 314);
                CFunctions.clickPos(900, 786);
                CFunctions.clickPos(866, 896);
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
    }

    @Override
    public void execute() {
        try {
            Match match = api.getMatch(1);
            inviteTeam(match.getBlueTeam());
            inviteTeam(match.getRedTeam());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
