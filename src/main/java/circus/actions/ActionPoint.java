package circus.actions;

public class ActionPoint {
    int x;
    int y;
    String input;

    public ActionPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public ActionPoint(String input) {
        this.input = input;
    }

    public int getX() { return this.x; }
    public int getY() { return this.y; }
    public String getInput() { return this.input; }
}
