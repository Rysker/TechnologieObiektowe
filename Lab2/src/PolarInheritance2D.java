public class PolarInheritance2D extends Vector2D
{
    public PolarInheritance2D(double x, double y)
    {
        super(x, y);
    }

    public double getAngle()
    {
        double radians = Math.atan2(x, y);
        double degrees = radians * (180 / Math.PI);
        return degrees;
    }
}
