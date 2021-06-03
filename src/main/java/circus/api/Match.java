package circus.api;

import java.util.ArrayList;

public class Match {
    private int id;
    private String timestamp;
    private ArrayList<String> blue_team;
    private ArrayList<String> red_team;
    private String map;
    private String state;
    private int blue_captain;
    private int red_captain;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public ArrayList<String> getBlueTeam() {
        return blue_team;
    }

    public void setBlueTeam(ArrayList<String> blue_team) {
        this.blue_team = blue_team;
    }

    public ArrayList<String> getRedTeam() {
        return red_team;
    }

    public void setRedTeam(ArrayList<String> red_team) {
        this.red_team = red_team;
    }

    private String outcome;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // This is ass this is not going live this is going to be nuked just needed to check concept
    public int getMap()
    {
        System.out.println("[MatchServer] Setting match.currentMap to " + this.map);
        switch (this.map.toLowerCase())
        {
            case "hanamura":
                return 1;
            case "horizon lunar colony":
                return 3;
            case "anubis":
                return 5;
            case "volskaya":
                return 6;
            case "busan":
                return 7;
            case "ilios":
                return 8;
            case "lijiang":
                return 9;
            case "nepal":
                return 11;
            case "oasis":
                return 12;
            case "dorado":
                return 13;
            case "havana":
                return 14;
            case "junkertown":
                return 15;
            case "rialto":
                return 16;
            case "route 66":
                return 17;
            case "watchpoint gibraltar":
                return 18;
            case "blizzard world":
                return 19;
            case "eichenwalde":
                return 21;
            case "hollywood":
                return 23;
            case "king's row":
                return 25;
            case "numbani":
                return 27;
            default:
                return 20;
        }
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getBlueCaptain() {
        return blue_captain;
    }

    public void setBlueCaptain(int blueCaptain) {
        this.blue_captain= blueCaptain;
    }

    public int getRedCaptain() {
        return red_captain;
    }

    public void setRedCaptain(int redCaptain) {
        this.red_captain= redCaptain;
    }


}
