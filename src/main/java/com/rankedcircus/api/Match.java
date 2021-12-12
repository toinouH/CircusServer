package com.rankedcircus.api;

import java.util.ArrayList;

public class Match
{
    private int id;
    private String time_stamp;
    private ArrayList<String> blue_team;
    private ArrayList<String> red_team;
    private String map;
    private String state;
    private String outcome;
    private int blue_captain;
    private int red_captain;

    public String getTimestamp()                            { return time_stamp;            }

    public void setTimestamp(String timestamp)              { this.time_stamp = timestamp;  }

    public ArrayList<String> getBlueTeam()                  { return blue_team;             }

    public void setBlueTeam(ArrayList<String> blue_team)    { this.blue_team = blue_team;   }

    public ArrayList<String> getRedTeam()                   { return red_team;              }

    public void setRedTeam(ArrayList<String> red_team)      { this.red_team = red_team;     }

    public int getId()                                      { return id;                    }

    public void setId(int id)                               { this.id = id;                 }

    public String getMap()                                  { return this.map;              }

    // This is ass this is not going live this is going to be nuked just needed to check concept. Edit: jaja
    public int getMapId()
    {
        System.out.println("[MatchServer] getMapId() returned map id of map " + this.map);
        return switch (this.map.toLowerCase()) {
            case "hanamura" -> 1;
            case "horizon lunar colony" -> 3;
            case "anubis" -> 5;
            case "volskaya" -> 6;
            case "busan" -> 7;
            case "ilios" -> 8;
            case "lijiang" -> 9;
            case "nepal" -> 11;
            case "oasis" -> 12;
            case "dorado" -> 13;
            case "havana" -> 14;
            case "junkertown" -> 15;
            case "rialto" -> 16;
            case "route 66" -> 17;
            case "watchpoint gibraltar" -> 18;
            case "blizzard world" -> 19;
            case "eichenwalde" -> 21;
            case "hollywood" -> 23;
            case "king's row" -> 25;
            case "numbani" -> 27;
            default -> 20;
        };
    }

    public void setMap(String map)              { this.map = map;                   }

    public String getOutcome()                  { return outcome;                   }

    public void setOutcome(String outcome)      { this.outcome = outcome;           }

    public String getState()                    { return state;                     }

    public void setState(String state)          { this.state = state;               }

    public int getBlueCaptain()                 { return blue_captain;              }

    public void setBlueCaptain(int blueCaptain) { this.blue_captain= blueCaptain;   }

    public int getRedCaptain()                  { return red_captain;               }

    public void setRedCaptain(int redCaptain)   { this.red_captain= redCaptain;     }
}
