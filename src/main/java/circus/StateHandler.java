package circus;

import circus.actions.ActionInvitePlayers;
import circus.api.Api;
import circus.api.Match;
import circus.imaging.Imaging;
import circus.imaging.Tess;
import net.sourceforge.tess4j.TesseractException;

import java.awt.*;
import java.io.IOException;

public class StateHandler implements Runnable {

    private final Api api = new Api();
    private Tess tess = new Tess();
    private Imaging imaging = new Imaging();
    private boolean hasMovedBot = false;
    private boolean hasSetPreset = false;
    private boolean hasInvitedPlayers = false;
    private int currentMatchId = 0;

    public void setCurrentMatchId(int matchId) {
        if (this.currentMatchId == 0)
            this.currentMatchId = matchId;
        else
            System.out.println("[StateHandler] An attempt to override currentMatchId.");
    }

    public int getCurrentMatchId() {
        return this.currentMatchId;
    }

    public State getCurrentState() {
        try {
            if (tess.readBufferedImage(imaging.captureMenuPlayButton())
                    .toLowerCase()
                    .contains("play")) {

                return State.MAIN_MENU;
            }
            if (tess.readBufferedImage(imaging.captureFindGroupButton())
                    .toLowerCase()
                    .contains("group")) {

                return State.PLAY_MENU;
            }
            if (tess.readBufferedImage(imaging.captureFilterGamesButton())
                    .toLowerCase()
                    .contains("filter")) {

                return State.GAME_BROWSER_MENU;
            }
            if (tess.readBufferedImage(imaging.captureLobbyStartButton())
                    .toLowerCase()
                    .contains("start")) {

                if (!this.hasMovedBot && this.currentMatchId != 0) {
                    this.hasMovedBot = true;
                    return State.MAIN_LOBBY_MENU_MOVE_SPEC;
                }
                if (!this.hasSetPreset && this.currentMatchId != 0) {
                    this.hasSetPreset = true;
                    return State.MAIN_LOBBY_MENU_SET_PRESET;
                }
                if (!this.hasInvitedPlayers && this.currentMatchId != 0) {
                    this.hasInvitedPlayers = true;
                    return State.MAIN_LOBBY_INVITE_PLAYERS;
                }
                if (this.currentMatchId != 0)
                    return State.MAIN_LOBBY_WAITING_FOR_PLAYERS;
                return State.WAITING_FOR_GAME;
            }
        } catch (IOException | TesseractException | AWTException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void run() {
        // I don't like this and will change how this is handled. It's messy but I'm tired.
        while (true) {
            try {
                State currentState = this.getCurrentState();
                if (currentState != null) {
                    if (currentState == State.MAIN_LOBBY_INVITE_PLAYERS) {
                        Match match = api.getMatch(this.currentMatchId);
                        new ActionInvitePlayers()
                                .inviteTeam(match.getBlueTeam(), 1)
                                .inviteTeam(match.getRedTeam(), 2);
                    }
                    currentState.goNextState();
                }
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
