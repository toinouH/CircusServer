package circus.actions;


import java.util.ArrayList;

public class ActionCreateLobby extends Action {
    public ActionCreateLobby() {
        this.name = "CreateLobby";
        this.queue = new ArrayList<ActionPoint>() {{
            add(new ActionPoint(1719, 224));
        }};
    }
}
