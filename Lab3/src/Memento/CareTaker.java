package Memento;

import Population.PopulationManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CareTaker
{
    private final Map<String, Memento> snapshots;
    private PopulationManager population;

    public CareTaker(PopulationManager population)
    {
        this.snapshots = new HashMap<>();
        this.population = population;
    }

    public void create(int tick)
    {
        this.snapshots.put(toDate(tick), new Memento(this.population.getList()));
    }

    private String toDate(int tick)
    {
        long milliseconds = tick * 40L;
        Date date = new Date(milliseconds);
        SimpleDateFormat sdfDate = new SimpleDateFormat("mm:ss.SSS");
        return sdfDate.format(date);
    }

    public String[] getKeys()
    {
        Object[] keys = this.snapshots.keySet().toArray();
        String[] stringKeys = new String[keys.length];

        for (int i = 0; i < keys.length; i++) {
            stringKeys[i] = (String) keys[i];
        }

        return stringKeys;
    }

    public Memento restore(String key)
    {
        Memento tmp = this.snapshots.get(key);
        return tmp;
    }
}
