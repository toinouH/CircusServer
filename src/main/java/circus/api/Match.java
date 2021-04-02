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

    public String getMap() {
        return map;
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
