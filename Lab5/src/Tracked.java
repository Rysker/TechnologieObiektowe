import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Tracked
{
    private int x, y;
    private ArrayList<Point> previous_positions = new ArrayList<>();
    private Point next_prediction;
    private Color color;

    public Tracked(Point point)
    {
        this.x = point.x;
        this.y = point.y;
        previous_positions.add(new Point(x, y));
        next_prediction = null;
        this.color = getRandomColor();
    }

    public Tracked(Tracked other)
    {
        this.x = other.x;
        this.y = other.y;
        this.previous_positions = new ArrayList<>(other.previous_positions);
        this.next_prediction = (other.next_prediction != null) ? new Point(other.next_prediction) : null;
        this.color = other.color;
    }
    
    public Point getNextPrediction()
    {
        return this.next_prediction;
    }

    public Point getPosition()
    {
        return new Point(x, y);
    }

    public ArrayList<Point> getPreviousPositions()
    {
        return this.previous_positions;
    }

    public void updatePosition(Point new_position)
    {
        this.x = new_position.x;
        this.y = new_position.y;
        previous_positions.add(this.getPosition());
        this.next_prediction = calculateNextPoint();
    }

    public Color getColor()
    {
        return this.color;
    }

    private Point calculateNextPoint()
    {
        Point last_point = previous_positions.get(previous_positions.size() - 2);
        int lastX = last_point.x;
        int lastY = last_point.y;

        int vectorX = this.x - lastX;
        int vectorY = this.y - lastY;

        int nextX = this.x + vectorX;
        int nextY = this.y + vectorY;

        return new Point(nextX, nextY);
    }

    private static Color getRandomColor()
    {
        Random random = new Random();
        Color[] predefinedColors = {
                Color.RED,
                Color.GREEN,
                Color.BLUE,
                Color.BLACK,
                new Color(128, 0, 128),
                new Color(139, 69, 19),
                new Color(255, 140, 0),
                new Color(21, 148, 132)
        };
        return predefinedColors[random.nextInt(predefinedColors.length)];
    }

}




