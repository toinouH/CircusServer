package circus;

import circus.actions.*;

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

    private final Action action;

    State(Action action)
    {
        this.action = action;
    }

    public void goNextState() throws InterruptedException
    {
        this.action.execute();
        Thread.sleep(50);
    }
}
