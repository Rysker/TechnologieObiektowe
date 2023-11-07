public class Vector3DInheritance extends Vector2D
{
    private double z;

    public double abs()
    {
        double [] components = this.getComponents();
        double result = 0;
        for (double x:components)
            result += x * x;
        return Math.sqrt(result);
    }

    public Vector3DInheritance(double x, double y, double z)
    {
        super(x, y);
        this.z = z;
    }

    public double cdot(IVector param)
    {
        double [] components1 = param.getComponents();
        double [] components2 = this.getComponents();
        double result = 0;
        for(int i = 0; i < components2.length; i++)
            result += components1[i] * components2[i];
        return result;
    }

    public double[] getComponents()
    {
        double[] components = new double[3];
        components[0] = super.x;
        components[1] = super.y;
        components[2] = this.z;
        return components;
    }

    public Vector3DInheritance cross(IVector param)
    {
        double[] tmp1 = super.getComponents();
        double[] tmp2 = param.getComponents();
        return new Vector3DInheritance(tmp1[1] * tmp2[2] - this.z * tmp2[1], this.z *tmp2[0] - tmp1[0] * tmp2[2], tmp1[0] * tmp2[1] - tmp1[1] * tmp2[0]);
    }

    public IVector getSrcV()
    {
        double[] components = super.getComponents();
        return new Vector2D(components[0], components[1]);
    }
}
