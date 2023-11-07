public class Main
{
    public static void main(String[] args)
    {
        Vector2D wektor1 = new Vector2D(4, 3);
        Polar2DAdapter wektor2 = new Polar2DAdapter(new Vector2D(2, 5));
        Vector3DDecorator wektor3 = new Vector3DDecorator(new Vector2D(1, 1), 1);

        System.out.println("Wektor 1");
        Polar2DAdapter tmp = new Polar2DAdapter(wektor1);
        System.out.printf("Wspolrzedne: %.2f; %.2f%n", wektor1.getComponents()[0], wektor1.getComponents()[1]);
        System.out.println("Układ biegunowy:");
        System.out.printf("r:  %.2f%n", tmp.abs());
        System.out.printf("theta:  %.2f%n", tmp.getAngle());
        System.out.println("-----------------------");

        System.out.println("Wektor 2");
        System.out.printf("Wspolrzedne: %.2f; %.2f%n", wektor2.getComponents()[0], wektor2.getComponents()[1]);
        System.out.println("Układ biegunowy:");
        System.out.printf("r:  %.2f%n", wektor2.abs());
        System.out.printf("theta:  %.2f%n", wektor2.getAngle());
        System.out.println("-----------------------");

        System.out.println("Wektor 3");
        Polar2DAdapter tmp2 = new Polar2DAdapter((Vector2D)wektor3.getSrcV());
        System.out.printf("Wspolrzedne: %.2f; %.2f%n", wektor3.getComponents()[0], wektor3.getComponents()[1], wektor3.getComponents());
        System.out.println("Układ biegunowy:");
        System.out.printf("r:  %.2f%n", wektor3.abs());
        System.out.printf("theta:  %.2f%n", tmp2.getAngle());
        System.out.println("-----------------------");

        System.out.println("Wektor 1 i Wektor 2");
        System.out.printf("Iloczyn skalarny: %.2f%n", wektor1.cdot(wektor2));
        Vector3DDecorator tmp3 = new Vector3DDecorator(wektor1).cross(wektor2);
        System.out.printf("Iloczyn wektorowy: %.2f; %.2f; %.2f%n", tmp3.getComponents()[0], tmp3.getComponents()[1], tmp3.getComponents()[2]);
        System.out.println("-----------------------");

        System.out.println("Wektor 1 i Wektor 3");
        System.out.printf("Iloczyn skalarny: %.2f%n", wektor1.cdot(wektor3));
        Vector3DDecorator tmp4 = new Vector3DDecorator(wektor1).cross(wektor3);
        System.out.printf("Iloczyn wektorowy: %.2f; %.2f; %.2f%n", tmp4.getComponents()[0], tmp4.getComponents()[1], tmp4.getComponents()[2]);
        System.out.println("-----------------------");

        System.out.println("Wektor 2 i Wektor 3");
        System.out.printf("Iloczyn skalarny: %.2f%n", wektor2.cdot(wektor3));
        Vector3DDecorator tmp5 = new Vector3DDecorator(wektor2).cross(wektor3);
        System.out.printf("Iloczyn wektorowy: %.2f; %.2f; %.2f%n", tmp5.getComponents()[0], tmp5.getComponents()[1], tmp5.getComponents()[2]);
        System.out.println("-----------------------");




    }
}