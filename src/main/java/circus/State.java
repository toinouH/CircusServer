package circus;

import circus.actions.*;
import circus.imaging.Imaging;

import java.awt.*;

public enum State {


    MAIN_MENU(new ActionPlay()),
    PLAY_MENU(new ActionGameBrowser()),
    GAME_BROWSER_MENU(new ActionCreateLobby()),
    MAIN_LOBBY_MENU_MOVE_SPEC(new ActionMoveToSpec()),
    MAIN_LOBBY_MENU_SET_PRESET(new ActionSetPreset()),
    MAIN_LOBBY_WAITING_FOR_PLAYERS(new ActionWaitingForPlayers()),
    MAIN_LOBBY_INVITE_PLAYERS(new ActionInvitePlayers());
//    MOVE_BOT_SPEC(364, 473, true),
//    MOVE_BOT_SPEC_LIST(1428, 468, true),
//    MOVE_BOT_SPEC_DONE(1409, 309, true),
//    SETTINGS_MAIN_MENU(1428, 468, true);
//    SETTINGS_PRESETS_MENU,
//    SETTINGS_LOBBY_MENU,
//    SETTINGS_MAPS_MENU,
//    SETTINGS_HEROES_MENU,
//    SETTINGS_MODES_MENU,
//    SETTINGS_WORKSHOP_MENU;

    private Action action;
    private Mouse mouse;

    State(Action action) {
        this.action = action;

        try {
            this.mouse = new Mouse();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void goNextState() throws InterruptedException {
        this.action.execute();
        Thread.sleep(250);
    }

    public Mouse getMouse () { return this.mouse; }
}
