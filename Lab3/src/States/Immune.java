package States;

import Population.*;
import java.awt.*;

public class Immune implements IState
{

    @Override
    public void handle(Person src, Person ext, int tick)
    {

    }
    @Override
    public Color getColor()
    {
        return Color.BLUE;
    }

    public Immune()
    {

    }
    public Immune(Immune other)
    {

    }

    @Override
    public String getName()
    {
        return "Immune";
    }

    @Override
    public IState copy()
    {
        return new Immune(this);
    }

}
