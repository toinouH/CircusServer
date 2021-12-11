package com.rankedcircus.api;

import java.util.ArrayList;

public class Game {
    private int id;
    private String datePlayed;
    private ArrayList<Player> blueTeam;
    private ArrayList<Player> redTeam;
    private int outcome; // 0 = blue win 1 = red win

    public int getId()                                  { return id;                    }

    public void setId(int id)                           { this.id = id;                 }

    public String getDatePlayed()                       { return datePlayed;            }

    public void setDatePlayed(String datePlayed)        { this.datePlayed = datePlayed; }

    public ArrayList<Player> getBlueTeam()              { return blueTeam;              }

    public void setBlueTeam(ArrayList<Player> blueTeam) { this.blueTeam = blueTeam;     }

    public ArrayList<Player> getRedTeam()               { return redTeam;               }

    public void setRedTeam(ArrayList<Player> redTeam)   { this.redTeam = redTeam;       }

    public int getOutcome()                             { return outcome;               }

    public void setOutcome(int outcome)                 { this.outcome = outcome;       }
}
