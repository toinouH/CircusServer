package com.rankedcircus;

import com.rankedcircus.actions.*;

public enum State
{
    MAIN_MENU(new ActionPlay()),
    PLAY_MENU(new ActionGameBrowser()),
    GAME_BROWSER_MENU(new ActionCreateLobby()),
    MAIN_LOBBY_MENU_MOVE_SPEC(new ActionMoveToSpec()),
    MAIN_LOBBY_MENU_SET_PRESET(new ActionSetPreset()),
    MAIN_LOBBY_MENU_SET_MAP(new ActionSetMap()),
    MAIN_LOBBY_WAITING_FOR_PLAYERS(new ActionWaitingForPlayers()),
    MAIN_LOBBY_INVITE_PLAYERS(new ActionInvitePlayers()),
    MAIN_LOBBY_START_GAME(new ActionStartGame()),
    WAITING_FOR_GAME(new ActionWaitingForGame()),
    IN_GAME(new ActionInGame());

    private final Action action;

    State(Action action)
    {
        this.action = action;
    }


    public void goNextState()
    {
        this.action.execute();
        CApplication.getInstance().sleepFor( 50 );
    }
}
