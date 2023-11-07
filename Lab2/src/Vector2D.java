public class Vector2D implements IVector
{
    protected double x;
    protected double y;

    public Vector2D(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    @Override
    public double abs()
    {
        double [] components = this.getComponents();
        double result = 0;
        for (double x:components)
            result += x * x;
        return Math.sqrt(result);
    }

    @Override
    public double cdot(IVector param)
    {
        double [] components1 = param.getComponents();
        double [] components2 = this.getComponents();
        double result = 0;
        for(int i = 0; i < components2.length; i++)
            result += components1[i] * components2[i];
        return result;
    }

    @Override
    public double[] getComponents()
    {
        double[] components = new double[2];
        components[0] = this.x;
        components[1] = this.y;
        return components;
    }
}
