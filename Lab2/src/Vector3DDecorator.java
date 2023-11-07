public class Vector3DDecorator implements IVector
{
    private IVector srcVector;
    private double z;

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
        double[] tmp = srcVector.getComponents();
        double[] components1 = {tmp[0], tmp[1], this.z};
        double[] components2 = param.getComponents();
        double result = 0;
        for(int i = 0; i < components2.length; i++)
            result += components1[i] * components2[i];
        return result;
    }

    @Override
    public double[] getComponents()
    {
        double[] components = new double[3];
        double[] tmp = srcVector.getComponents();
        components[0] = tmp[0];
        components[1] = tmp[1];
        components[2] = this.z;
        return components;
    }

    public Vector3DDecorator(IVector srcVector)
    {
        this.srcVector = srcVector;
        this.z = 0;
    }

    public Vector3DDecorator(IVector srcVector, double z)
    {
        this.srcVector = srcVector;
        this.z = z;
    }

    public Vector3DDecorator cross(IVector param)
    {
        double[] tmp1 = this.srcVector.getComponents();
        double[] tmp2 = param.getComponents();
        if(tmp2.length == 2)
        {
            tmp2 = new double[] {tmp2[0], tmp2[1], 0};
        }
        return new Vector3DDecorator(new Vector2D(tmp1[1] * tmp2[2] - this.z * tmp2[1], this.z *tmp2[0] - tmp1[0] * tmp2[2]), tmp1[0] * tmp2[1] - tmp1[1] * tmp2[0]);
    }

    public IVector getSrcV()
    {
        return this.srcVector;
    }
}
