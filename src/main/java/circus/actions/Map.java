package circus.actions;


public enum Map {
    BUSAN(1, "Busan"),
    ILIOS(2, "Ilios"),
    LIJIANG_TOWER(3, "Lijiang_Tower"),
    NEPAL(5, "Nepal"),
    OASIS(6, "Oasis"),
    DORADO(7, "Dorado"),
    HAVANA(8, "Havan"),
    JUNKERTOWN(9, "Junkertown"),
    RIALTO(10, "Nepal"),
    ROUTE_66(11, "Route_66"),
    WATCHPOINT_GIBRALTAR(12, "Watchpoint_Gibraltar"),
    BLIZZARD_WORLD(13, "Blizzard_World"),
    EICHENWALDE(15, "Eichenwalde"),
    HOLLYWOOD(17, "Hollywood"),
    KINGS_ROW(19, "Kings_Row"),
    NUMBANI(21, "Numbani");


    private int id;
    private String name;

    Map(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return this.id; }
    public String getName() { return this.name; }

    public static Map valueOf(int id) {
        for (Map map : values()) {
            if (map.getId() == id) {
                return map;
            }
        }
        throw new IllegalArgumentException("Illegal arg " + id);
    }
}
