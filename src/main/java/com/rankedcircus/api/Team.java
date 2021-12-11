package com.rankedcircus.api;

public class Team
{
    private int id;
    private String name;
    private String icon;
    private String twitter;
    private String website;
    private String instagram;
    private String youtube;

    public int getId()                          { return id;                    }

    public void setId(int id)                   { this.id = id;                 }

    public String getName()                     { return name;                  }

    public void setName(String name)            { this.name = name;             }

    public String getIcon()                     { return icon;                  }

    public void setIcon(String icon)            { this.icon = icon;             }

    public String getTwitter()                  { return twitter;               }

    public void setTwitter(String twitter)      { this.twitter = twitter;       }

    public String getWebsite()                  { return website;               }

    public void setWebsite(String website)      { this.website = website;       }

    public String getInstagram()                { return instagram;             }

    public void setInstagram(String instagram)  { this.instagram = instagram;   }

    public String getYoutube()                  { return youtube;               }

    public void setYoutube(String youtube)      { this.youtube = youtube;       }
}
