package States;

import Population.*;
import java.awt.*;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class Healthy implements IState
{
    private Map<Integer, Integer> neighbours = new TreeMap<>();
    private Random random = new Random();

    public Healthy()
    {

    }
    public Healthy(Healthy other)
    {
        this.neighbours = new TreeMap<>(other.neighbours);
        this.random = new Random(other.random.nextLong());
    }
    @Override
    public void handle(Person src, Person ext, int tick)
    {
        if(src.distance(ext) <= 2.0)
        {
            if(changeMap(ext, tick))
            {
                if (tick - neighbours.get(ext.getId()) >= 75) {
                    if (checkInfection(ext.getState().getName()))
                    {
                        if (random.nextDouble() < 0.5)
                            src.setState(new SickNoSymptoms());
                         else
                             src.setState(new SickSymptoms());
                    }
                }
            }
        }
        else
        {
            if(neighbours.containsKey(ext.getId()))
                neighbours.remove(ext.getId());
        }
    }

    @Override
    public Color getColor()
    {
        return Color.GREEN;
    }

    @Override
    public IState copy()
    {
        return new Healthy(this);
    }

    @Override
    public String getName()
    {
        return "Healthy";
    }

    private boolean changeMap(Person x, int tick)
    {
        if(!neighbours.containsKey(x.getId()) && (x.getState().getName() == "SickNoSymptoms" || x.getState().getName() == "SickSymptoms"))
        {
            neighbours.put(x.getId(), tick);
            return true;
        }

        if(neighbours.containsKey(x.getId()))
            return true;
        return false;
    }

    private boolean checkInfection(String name)
    {
        if(name == "SickNoSymptoms")
            return (random.nextDouble() <= 0.5) ? true : false;
        if(name == "SickSymptoms")
            return true;
        return false;
    }
}
