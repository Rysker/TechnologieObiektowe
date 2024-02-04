package States;

import Population.Person;

import java.awt.*;

public class SickSymptoms implements IState
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

    public SickSymptoms()
    {

    }

    public SickSymptoms(SickSymptoms other)
    {
        this.last_tick = other.last_tick;
        this.time_counter = other.time_counter;
    }

    @Override
    public IState copy()
    {
        return new SickSymptoms(this);
    }
    @Override
    public Color getColor()
    {
        return Color.RED;
    }

    @Override
    public String getName()
    {
        return "SickSymptoms";
    }

    public boolean checkIfRecovered(int time)
    {
        if(time * 25 > 25000)
            return true;
        return false;
    }
}
