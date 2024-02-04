import java.awt.*;

public class Blob
{
    private Color color;
    private int totalX, totalY, pixelCount;
    public Blob(Color color)
    {
        this.color = color;
        this.totalX = 0;
        this.totalY = 0;
        this.pixelCount = 0;
    }

    public Color getColor()
    {
        return color;
    }

    public Point getPosition()
    {
        if (pixelCount == 0)
            throw new IllegalStateException("Blob has no pixels.");
        return new Point(totalX / pixelCount, totalY / pixelCount);
    }

    public void updateCenter(int x, int y)
    {
        totalX += x;
        totalY += y;
        pixelCount++;
    }

}
