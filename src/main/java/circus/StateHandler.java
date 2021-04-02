package circus;

import circus.imaging.Imaging;
import circus.imaging.Tess;
import net.sourceforge.tess4j.TesseractException;

import java.awt.*;
import java.io.IOException;

public class StateHandler {

    private Tess tess = new Tess();
    private Imaging imaging = new Imaging();
    private boolean hasMovedBot = false;
    private boolean hasSetPreset = false;
    private boolean hasInvitedPlayers = false;
    private boolean isWaitingForPlayers = true;

    public State getCurrentState() throws IOException, AWTException, TesseractException {
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

            if (!this.hasMovedBot) {
                this.hasMovedBot = true;
                return State.MAIN_LOBBY_MENU_MOVE_SPEC;
            }

            if (!this.hasSetPreset) {
                this.hasSetPreset = true;
                return State.MAIN_LOBBY_MENU_SET_PRESET;
            }

            if (!this.hasInvitedPlayers) {
                this.hasInvitedPlayers = true;
                return State.MAIN_LOBBY_INVITE_PLAYERS;
            }

            return State.MAIN_LOBBY_WAITING_FOR_PLAYERS;
        }


//        if (tess.readBufferedImage(imaging.captureDoneAndMoveButton())
//                .toLowerCase()
//                .contains("done")) {
//
//            return State.MOVE_BOT_SPEC_LIST;
//        }

        return null;
    }
}
