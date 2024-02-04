package Memento;

import Population.*;

import java.util.ArrayList;

public class Memento
{
    public ArrayList<Person> population;

    public Memento(ArrayList<Person> x)
    {
        this.population = new ArrayList<>();

        for (Person person : x)
        {
            Person clonedPerson = new Person(person);
            this.population.add(clonedPerson);
        }
    }
}
