package Population;

import Vectors.*;
import States.*;

public class Person
{
    private int id;
    private double x;
    private double y;
    private Vector2D direction;
    private IState state;

    public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public IVector getDirection()
    {
        return direction;
    }

    public void setDirection(Vector2D vector)
    {
        this.direction = new Vector2D(vector);
    }

    public IState getState()
    {
        return state;
    }

    public void setState(IState state)
    {
        this.state = state;
    }

    public Person(Vector2D direction, IState state, double x, double y, int id)
    {
        this.direction = direction;
        this.state = state;
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public Person(Person other)
    {
        this.id = other.id;
        this.direction = new Vector2D(other.direction);
        this.state = other.state.copy();
        this.x = other.x;
        this.y = other.y;
    }

    public double distance(Person pop2)
    {
        double x_distance = this.x - pop2.x;
        double y_distance = this.y - pop2.y;
        return Math.sqrt(x_distance*x_distance + y_distance*y_distance);
    }
    public void move()
    {
        double old_x = this.getDirection().getComponents()[0];
        double old_y = this.getDirection().getComponents()[1];
        double new_x = this.x + old_x * (1./25.);
        double new_y = this.y + old_y * (1./25.);
        this.setX(new_x);
        this.setY(new_y);
    }

    public boolean moveAndCheck(double xborder, double yborder)
    {
        this.move();
        return this.checkBorders(xborder, yborder);
    }
    public boolean checkBorders(double xborder, double yborder)
    {
        if(this.x >= 0 && this.x <= xborder && this.y >= 0 & this.y <= yborder)
            return false;

        double choice = Math.random();
        if(choice >= 0.5)
            return true;

        if(this.x < 0)
            this.x = 0;
        if(this.x > xborder)
            this.x = xborder;
        if(this.y < 0)
            this.y = 0;
        if(this.y > yborder)
            this.y = yborder;
        this.direction.oppositeDirection();
        return false;

    }

    public int getId()
    {
        return this.id;
    }

    public void handleState(Person ext, int tick)
    {
        this.state.handle(this, ext, tick);
    }
}
