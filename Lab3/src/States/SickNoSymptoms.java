package States;

import Population.Person;
import java.awt.*;

public class SickNoSymptoms implements IState
{
    private int time_counter = -1;
    private int last_tick = 0;

    @Override
    public void handle(Person src, Person ext, int tick)
    {
        if(last_tick != tick)
        {
            time_counter += 1;
            last_tick = tick;
            if(checkIfRecovered(time_counter))
                src.setState(new Immune());
        }
    }

    public SickNoSymptoms()
    {

    }
    public SickNoSymptoms(SickNoSymptoms other)
    {
        this.last_tick = other.last_tick;
        this.time_counter = other.time_counter;
    }

    @Override
    public IState copy()
    {
        return new SickNoSymptoms(this);
    }

    @Override
    public Color getColor()
    {
        return Color.MAGENTA;
    }

    @Override
    public String getName()
    {
        return "SickNoSymptoms";
    }

    public boolean checkIfRecovered(int time)
    {
        if(time * 25 > 25000)
            return true;
        return false;
    }

}
