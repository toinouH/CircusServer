package circus.actions;

import java.util.ArrayList;

public class ActionGameBrowser extends Action
{
    public ActionGameBrowser()
    {
        this.name = "GameBrowser";
        this.queue = new ArrayList<ActionPoint>() {{
            add(new ActionPoint(1485, 535));
        }};
    }
}
