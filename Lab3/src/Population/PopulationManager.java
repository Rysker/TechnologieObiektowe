package Population;

import java.util.ArrayList;
import java.lang.*;
import java.util.Random;

import Vectors.*;
import States.*;

public class PopulationManager
{
    private ArrayList<Person> personStorage;
    private Random random = new Random();
    private int counter;
    private int xborder;
    private int yborder;
    private boolean option;

    public PopulationManager(int i, int xborder, int yborder, boolean option)
    {
        this.xborder = xborder;
        this.yborder = yborder;
        this.counter = 0;
        this.option = option;
        this.personStorage = new ArrayList<>();
        for(int j = 0; j < i; j++)
        {
            Person tmp = this.generatePerson();
            this.addPerson(tmp);
        }
    }

    public Person generatePerson()
    {
        double x = this.random.nextDouble() * this.xborder;
        double y = this.random.nextDouble() * this.yborder;
        if(this.option == false)
            return new Person(generateVector(), new Healthy(), x, y, this.counter);
        else
            return (random.nextDouble() <= 0.8) ? new Person(generateVector(), new Healthy(), x, y, this.counter) : new Person(generateVector(), new Immune(), x, y, this.counter);
    }

    public void addPerson(Person person)
    {
        this.personStorage.add(person);
        this.counter += 1;
    }

    public void deletePerson(int index)
    {
        this.personStorage.remove(index);
        this.counter -= 1;
    }

    public ArrayList<Person> getList()
    {
        return this.personStorage;
    }

    public void simulatePopulation(int tick)
    {
        generateNewPerson(30);
        moveAllPopulation();
        handleStates(tick);
    }

    public void moveAllPopulation()
    {
        int size = this.personStorage.size();
        for(int i = 0; i < size; i++)
        {
            Person tmp = this.personStorage.get(i);
            if(random.nextDouble() < 0.008)
                tmp.setDirection(changeVector());
            if(tmp.moveAndCheck(this.xborder, this.yborder))
            {
                deletePerson(i);
                i -= 1;
                size -= 1;
            }
        }
    }

    private Vector2D changeVector()
    {
        double radius = Math.random() * 2.5;
        double newAngle = Math.toRadians((Math.random() * 360));
        double newX = radius * Math.cos(newAngle);
        double newY = radius * Math.sin(newAngle);
        return new Vector2D(newX, newY);
    }

    public void generateNewPerson(double sample)
    {
        for(int i = 0; i < sample; i++)
        {
            if(random.nextDouble() <= 0.02)
                addPerson(generateBorderPerson());
        }
    }

    public Person generateBorderPerson()
    {
        double[] coordinates = generateBorderCoordinates();
        Vector2D vector = generateBorderVector(coordinates[0], coordinates[1]);
        if (random.nextDouble() <= 0.1)
        {
            if (random.nextDouble() < 0.5)
                return new Person(vector, new SickNoSymptoms(), coordinates[0], coordinates[1], this.counter);
            else
                return new Person(vector, new SickSymptoms(), coordinates[0], coordinates[1], this.counter);

        }

        if(this.option == true)
        {
            if (random.nextDouble() <= 0.2)
            {
                return new Person(vector, new Immune(), coordinates[0], coordinates[1], this.counter);
            }

        }

        return new Person(vector, new Healthy(), coordinates[0], coordinates[1], this.counter);
    }

    public void handleStates(int tick)
    {
        for(int i = 0; i < this.personStorage.size(); i++)
        {
            for (int j = i + 1; j < this.personStorage.size(); j++)
            {
                Person src = this.personStorage.get(i);
                Person ext = this.personStorage.get(j);
                src.handleState(ext, tick);
                ext.handleState(src, tick);
            }
        }
    }
    private double[] generateBorderCoordinates()
    {
        int chosenDimension = this.random.nextInt(2);
        double x = (chosenDimension == 0) ? (this.random.nextBoolean() ? 0.0 : xborder) : (this.random.nextDouble() * xborder);
        double y = (chosenDimension == 1) ? (this.random.nextBoolean() ? 0 : yborder) : (this.random.nextDouble() * yborder);
        return new double[]{x, y};
    }

    private Vector2D generateBorderVector(double x, double y)
    {
        Vector2D tmp = generateVector();
        if(x == 0)
        {
            tmp.setY(Math.abs((tmp.getY())));
            return tmp;
        }

        if(x == 12)
        {
            tmp.setY(-Math.abs((tmp.getY())));
            return tmp;
        }

        if(y == 0)
        {
            tmp.setX(Math.abs((tmp.getX())));
            return tmp;
        }

        if(y == 12)
        {
            tmp.setX(-Math.abs((tmp.getX())));
            return tmp;
        }
        return tmp;
    }

    private Vector2D generateVector()
    {
        double angle = Math.random() * 2 * Math.PI;
        double radius = Math.random() * 2.5;
        double x = radius * Math.cos(angle);
        double y = radius * Math.sin(angle);
        return new Vector2D(x, y);
    }

    public void swapPopulation(ArrayList<Person> x)
    {
        this.personStorage.clear();
        for (Person person : x)
        {
            Person clonedPerson = new Person(person);
            this.personStorage.add(clonedPerson);
        }
        this.counter = this.personStorage.size();
    }

}
