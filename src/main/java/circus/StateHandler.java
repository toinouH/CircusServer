package circus;

import circus.actions.ActionInvitePlayers;
import circus.actions.ActionSetMap;
import circus.actions.Map;
import circus.api.Api;
import circus.api.Match;
import circus.imaging.Imaging;
import circus.imaging.Tess;
import net.sourceforge.tess4j.TesseractException;

import java.awt.*;
import java.io.IOException;

public class StateHandler implements Runnable {

    private final Api api = new Api();
    private final Tess tess = new Tess();
    private final Imaging imaging = new Imaging();
    private Match match = new Match();
    private boolean hasMovedBot = false;
    private boolean hasSetPreset = false;
    private boolean hasInvitedPlayers = false;
    private boolean hasChangedMap = false;
    private int currentMatchId = 0;

    public void setCurrentMatchId(int matchId)
    {
        if (this.currentMatchId == 0)
        {
            this.match = api.getMatch(matchId);
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
            String ocrPlay = tess.readBufferedImage(imaging.captureMenuPlayButton()).toLowerCase();
            if (ocrPlay.contains("play") || ocrPlay.contains("plhy"))
            {
                return State.MAIN_MENU;
            }
            if (tess.readBufferedImage(imaging.captureFindGroupButton())
                    .toLowerCase()
                    .contains("group"))
            {

                return State.PLAY_MENU;
            }

            if (tess.readBufferedImage(imaging.captureFilterGamesButton())
                    .toLowerCase()
                    .contains("filter"))
            {

                return State.GAME_BROWSER_MENU;
            }

            if (tess.readBufferedImage(imaging.captureLobbyStartButton())
                    .toLowerCase()
                    .contains("start"))
            {
                System.out.println("Current state is in-lobby.");
                System.out.println(hasMovedBot);

                if (!this.hasMovedBot && this.currentMatchId != 0)
                {
                    this.hasMovedBot = true;
                    return State.MAIN_LOBBY_MENU_MOVE_SPEC;
                }

                if (!this.hasSetPreset && this.currentMatchId != 0) {
                    this.hasSetPreset = true;
//                    return State.MAIN_LOBBY_MENU_SET_PRESET;
                }

                if (!this.hasChangedMap && this.currentMatchId != 0) {
                    this.hasChangedMap = true;
                    return State.MAIN_LOBBY_MENU_SET_MAP;
                }

                if (!this.hasInvitedPlayers && this.currentMatchId != 0) {
                    this.hasInvitedPlayers = true;
                    return State.MAIN_LOBBY_INVITE_PLAYERS;
                }

                if (this.currentMatchId != 0)
                    return State.MAIN_LOBBY_WAITING_FOR_PLAYERS;

                return State.WAITING_FOR_GAME;
            }
        } catch (IOException | TesseractException | AWTException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void run()
    {
        // I don't like this and will change how this is handled. It's messy but I'm tired.
        while (true)
        {
            try
            {
                State currentState = this.getCurrentState();
                if (currentState != null)
                {
                    if (currentState == State.MAIN_LOBBY_INVITE_PLAYERS && hasMovedBot)
                    {
                        match = api.getMatch(this.currentMatchId);
                        new ActionInvitePlayers()
                                .inviteTeam(match.getBlueTeam(), 1)
                                .inviteTeam(match.getRedTeam(), 2);
                    }
                    if (currentState == State.MAIN_LOBBY_MENU_SET_MAP && hasInvitedPlayers)
                    {
                        new ActionSetMap()
                                .setMap(Map.valueOf(match.getMap()))
                                .changeMap();
                    }
                    currentState.goNextState();
                }
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            // Genuinely fuck Java.
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
