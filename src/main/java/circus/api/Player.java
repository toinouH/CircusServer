package circus.api;

public class Player {
    private int id;
    private String name;
    private int rating;
    // TODO: Team object
    private String team;

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating( int rating ) {
        this.rating = rating;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam( String team ) {
        this.team = team;
    }

}
