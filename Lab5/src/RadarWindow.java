import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RadarWindow extends JFrame
{
    private static final double scale = 0.8;
    private final RadarPanel trackingPanel;

    public RadarWindow()
    {
        this.trackingPanel = new RadarPanel(new ArrayList<>());
        initializeFrame();
        setVisible(true);
    }

    private void initializeFrame()
    {
        setTitle("Radar");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setLocationRelativeTo(null);
        add(trackingPanel);
    }

    public void updateTracking(ArrayList<Tracked> trackedObjects)
    {
        trackingPanel.update(trackedObjects);
    }

    private static class RadarPanel extends JPanel
    {
        private ArrayList<Tracked> trackedObjects;

        public RadarPanel(ArrayList<Tracked> initialTrackedObjects)
        {
            this.trackedObjects = new ArrayList<>(initialTrackedObjects);
        }

        public void update(ArrayList<Tracked> newTrackedObjects)
        {
            trackedObjects = new ArrayList<>(newTrackedObjects);
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;

            for (Tracked tracked : trackedObjects)
            {
                ArrayList<Point> path = tracked.getPreviousPositions();
                if (path.size() > 1)
                {
                    for (int i = 1; i < path.size(); i++)
                    {
                        Point prev = path.get(i - 1);
                        Point current = path.get(i);
                        drawDashedLine(g2d, (int) (prev.x * scale), (int) (prev.y * scale), (int) (current.x * scale), (int) (current.y * scale), tracked.getColor());
                    }
                }

                Point currentPos = tracked.getPosition();
                int size = 10;
                int[] xPoints = {(int) (currentPos.x * scale), (int) ((currentPos.x - size) * scale), (int) ((currentPos.x + size) * scale)};
                int[] yPoints = {(int) ((currentPos.y - size) * scale), (int) ((currentPos.y + size) * scale), (int) ((currentPos.y + size) * scale)};
                g2d.setColor(tracked.getColor());
                g2d.fillPolygon(xPoints, yPoints, 3);
            }
        }

        private void drawDashedLine(Graphics g, int x1, int y1, int x2, int y2, Color color)
        {
            double distance = Point.distance(x1, y1, x2, y2);
            int numSegments = 8;
            double segmentLength = distance / (2 * numSegments - 1);
            double gapSize = segmentLength / 2;
            double angle = Math.atan2(y2 - y1, x2 - x1);

            for (int i = 0; i < numSegments; i++)
            {
                double startX = x1 + i * 2 * segmentLength * Math.cos(angle);
                double startY = y1 + i * 2 * segmentLength * Math.sin(angle);

                double endX = startX + segmentLength * Math.cos(angle);
                double endY = startY + segmentLength * Math.sin(angle);

                g.setColor(color);
                g.drawLine((int) startX, (int) startY, (int) endX, (int) endY);

                if (i < numSegments - 1)
                {
                    startX = endX + gapSize * Math.cos(angle);
                    startY = endY + gapSize * Math.sin(angle);

                    endX = startX + gapSize * Math.cos(angle);
                    endY = startY + gapSize * Math.sin(angle);

                    g.drawLine((int) startX, (int) startY, (int) endX, (int) endY);
                }
            }
        }
    }
}
