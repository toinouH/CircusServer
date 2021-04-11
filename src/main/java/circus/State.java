package circus;

import circus.actions.*;

import java.awt.*;

public enum State {


    MAIN_MENU(new ActionPlay()),
    PLAY_MENU(new ActionGameBrowser()),
    GAME_BROWSER_MENU(new ActionCreateLobby()),
    MAIN_LOBBY_MENU_MOVE_SPEC(new ActionMoveToSpec()),
    MAIN_LOBBY_MENU_SET_PRESET(new ActionSetPreset()),
    MAIN_LOBBY_MENU_SET_MAP(new ActionSetMap()),
    MAIN_LOBBY_WAITING_FOR_PLAYERS(new ActionWaitingForPlayers()),
    MAIN_LOBBY_INVITE_PLAYERS(new ActionInvitePlayers()),
    WAITING_FOR_GAME(new ActionWaitingForGame());

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
        Thread.sleep(50);
    }

    public Mouse getMouse () { return this.mouse; }
}
