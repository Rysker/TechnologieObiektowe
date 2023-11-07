import java.util.*;
public class Polar2DAdapter implements IPolar2D, IVector
{
    private Vector2D srcVector;

    public Polar2DAdapter(Vector2D srcVector)
    {
        this.srcVector = srcVector;
    }

    @Override
    public double getAngle()
    {
        double[] components = this.getComponents();
        double radians = Math.atan2(components[0], components[1]);
        double degrees = radians * (180 / Math.PI);
        return degrees;
    }

    @Override
    public double abs()
    {
        return this.srcVector.abs();
    }

    public double cdot(IVector param)
    {
        return this.srcVector.cdot(param);
    }

    public double [] getComponents()
    {
        return this.srcVector.getComponents();
    }
}
