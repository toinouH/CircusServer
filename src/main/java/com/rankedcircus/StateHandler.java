package com.rankedcircus;

import com.rankedcircus.actions.ActionInvitePlayers;
import com.rankedcircus.actions.ActionSetMap;
import com.rankedcircus.actions.ActionStartGame;
import com.rankedcircus.actions.Map;
import com.rankedcircus.api.Api;
import com.rankedcircus.api.Match;
import com.rankedcircus.imaging.Imaging;
import com.rankedcircus.imaging.Tess;

import java.awt.*;
import java.io.IOException;

public class StateHandler implements Runnable
{
    private CommandHandler commandHandler = new CommandHandler();
    private Match match = new Match();
    private boolean hasMovedBot = false;
    private boolean hasSetPreset = false;
    private boolean hasInvitedPlayers = false;
    private boolean hasChangedMap = false;
    private int currentMatchId = 0;
    private boolean hasGameStarted = false;

    public void setCurrentMatchId(int matchId)
    {
        if (this.currentMatchId == 0)
        {
            this.match = Api.getInstance().getMatch(matchId);
            System.out.println(this.match.getMapId());
            this.currentMatchId = matchId;
        }
        else
            System.out.println("[StateHandler] An attempt to override currentMatchId.");
    }

    public int getCurrentMatchId()
    {
        return this.currentMatchId;
    }

    public State getCurrentState()
    {
        try
        {
            // This is a bit silly and I need to change how it's handled or train a better Tesseract model.
            // Tesseract is struggling to recognize "PLAY", Blizzard changed the lighting of menu maps apparently.
            String ocrPlay = Tess.getInstance().readBufferedImage(Imaging.captureMenuPlayButton()).toLowerCase();
            if (ocrPlay.contains("play") || ocrPlay.contains("plhy"))
                return State.MAIN_MENU;

            if (Tess.getInstance().readBufferedImage(Imaging.captureFindGroupButton())
                    .toLowerCase()
                    .contains("group"))
            {
                return State.PLAY_MENU;
            }

            if (Tess.getInstance().readBufferedImage(Imaging.captureCreateGameButton())
                    .toLowerCase()
                    .contains("create"))
            {

                return State.GAME_BROWSER_MENU;
            }

            if (Tess.getInstance().readBufferedImage(Imaging.captureLobbyStartButton())
                    .toLowerCase()
                    .contains("start"))
            {

                if (!this.hasMovedBot && this.currentMatchId != 0)
                {
                    this.hasMovedBot = true;
                    return State.MAIN_LOBBY_MENU_MOVE_SPEC;
                }

                if (!this.hasSetPreset && this.currentMatchId != 0)
                {
                    this.hasSetPreset = true;
                    return State.MAIN_LOBBY_MENU_SET_PRESET;
                }

                if (!this.hasChangedMap && this.currentMatchId != 0)
                {
                    this.hasChangedMap = true;
                    return State.MAIN_LOBBY_MENU_SET_MAP;
                }

                if (!this.hasInvitedPlayers && this.currentMatchId != 0)
                {
                    this.hasInvitedPlayers = true;
                    return State.MAIN_LOBBY_INVITE_PLAYERS;
                }

                if (this.hasInvitedPlayers && this.hasChangedMap && !this.hasGameStarted)
                {
                    this.hasGameStarted = true;
                    return State.MAIN_LOBBY_START_GAME;
                }

                if (this.currentMatchId != 0)
                    return State.MAIN_LOBBY_WAITING_FOR_PLAYERS;

                return State.WAITING_FOR_GAME;
            }
            else
            {
                if (this.hasInvitedPlayers && this.hasChangedMap && this.hasGameStarted)
                    return State.IN_GAME;
            }
        }
        catch (IOException | AWTException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void run()
    {
        // I don't like this and will change how this is handled. It's messy but I'm tired.
        // noinspection InfiniteLoopStatement
        for ( ;; )
        {
            //--------------------------------------------------
            // State-specific logic.
            State currentState = this.getCurrentState();

            if (currentState == null)
                continue;

            if (currentState == State.MAIN_LOBBY_INVITE_PLAYERS && hasMovedBot)
            {
                hasInvitedPlayers = true;
                match = Api.getInstance().getMatch(this.currentMatchId);
                new ActionInvitePlayers()
                        .inviteTeam(match.getBlueTeam(), 1)
                        .inviteTeam(match.getRedTeam(), 2);
            }

            if (currentState == State.MAIN_LOBBY_MENU_SET_MAP && !hasInvitedPlayers)
            {
                new ActionSetMap()
                        .setMap(Map.valueOf(match.getMapId()))
                        .changeMap();
            }

            if (currentState == State.MAIN_LOBBY_START_GAME)
                CApplication.getInstance().sleepFor( 7500 );

            currentState.goNextState();

            // Perform imaging/read of chat sector.
            if ( currentState == State.IN_GAME )
                commandHandler.intake(Tess.getInstance().readSingleLine(Imaging.captureChatSector()));

            CApplication.getInstance().sleepFor( 100 );
        }
    }
}
