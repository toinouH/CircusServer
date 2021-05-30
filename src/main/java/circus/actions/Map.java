package circus.actions;


public enum Map {
    HANAMURA(1, "Hanamura"),
    HLC(3, "Horizon Lunar Colony"),
    ANUBIS(5, "Temple of Anubis"),
    VOLSKAYA(6, "Volskaya Industries"),
    BUSAN(7, "Busan"),
    ILIOS(8, "Ilios"),
    LIJIANG(9, "Lijiang Tower"),
    NEPAL(11, "Nepal"),
    OASIS(12, "Oasis");


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
