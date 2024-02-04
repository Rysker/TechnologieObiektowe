package States;

import Population.*;
import java.awt.*;


public interface IState
{
    public void handle(Person src, Person ext, int tick);

    public Color getColor();

    public String getName();
    IState copy();
}
