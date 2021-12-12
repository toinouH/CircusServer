package com.rankedcircus.actions;


public enum Map
{
    // This is also really stupid. If you actually end up using this let me know and I'll make it not stupid.
    // That being said just use the D port of this project as it'll be updated frequently.
    HANAMURA(    1,     "Hanamura"),
    HLC(         3,     "Horizon Lunar Colony"),
    ANUBIS(      5,     "Temple of Anubis"),
    VOLSKAYA(    6,     "Volskaya Industries"),
    BUSAN(       7,     "Busan"),
    ILIOS(       8,     "Ilios"),
    LIJIANG(     9,     "Lijiang Tower"),
    NEPAL(       11,    "Nepal"),
    OASIS(       12,    "Oasis"),
    DORADO(      13,    "Dorado"),
    HAVANA(      14,    "Havana"),
    JUNKERTOWN(  15,    "Junkertown"),
    RIALTO(      16,    "Rialto"),
    ROUTE_66(    17,    "Route 66"),
    WATCHPOINT(  18,    "Watchpoint: Gibraltar"),
    BLIZZ_WORLD( 19,    "Blizzard World"),
    EICHENWALDE( 21,    "Eichenwalde"),
    HOLLYWOOD(   23,    "Hollywood"),
    KINGS_ROW(   25,    "King's Row"),
    NUMBANI(     27,    "Numbani");


    private final int id;
    private final String name;

    Map(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public int getId()      { return this.id;   }
    public String getName() { return this.name; }

    public static Map valueOf(int id)
    {
        for (Map map : values())
        {
            if (map.getId() == id) {
                return map;
            }
        }
        throw new IllegalArgumentException("Illegal arg " + id);
    }
}
