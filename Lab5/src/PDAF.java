import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class PDAF
{
    private static final double DISTANCE_THRESHOLD = 0.4;
    private static final double DIVIDER = 100.0;
    private static final double SCALE_FACTOR = 0.8;

    public PDAF() {}

    public ArrayList<Tracked> perform(ArrayList<Tracked> tracked, ArrayList<Blob> detected)
    {
        ArrayList<Tracked> newTrackedList = new ArrayList<>();

        Iterator<Tracked> trackedIterator = tracked.iterator();
        while (trackedIterator.hasNext())
        {
            Tracked track = trackedIterator.next();
            double maxProbability = 0.0;
            Blob bestMatch = null;

            Iterator<Blob> detectedIterator = detected.iterator();
            while (detectedIterator.hasNext())
            {
                Blob blob = detectedIterator.next();
                double probability = calculateProbability(track, blob);

                if (probability > maxProbability)
                {
                    maxProbability = probability;
                    bestMatch = blob;
                }
            }

            if (bestMatch != null)
            {
                Point predictedPoint = track.getNextPrediction();
                if (predictedPoint == null || !isOutRange(bestMatch.getPosition(), predictedPoint))
                {
                    Tracked tmp = new Tracked(track);
                    tmp.updatePosition(bestMatch.getPosition());
                    newTrackedList.add(tmp);
                    detected.remove(bestMatch);
                }
            }
            else
            {
                Point predictedPoint = track.getNextPrediction();
                if (predictedPoint != null && isOutsideBorder(predictedPoint))
                    trackedIterator.remove();
            }
        }

        for (Blob blob : detected)
            newTrackedList.add(new Tracked(blob.getPosition()));

        return newTrackedList;
    }

    private double calculateProbability(Tracked track, Blob blob)
    {
        Point predictedPoint = track.getNextPrediction();

        if (predictedPoint != null)
        {
            if (isOutRange(blob.getPosition(), predictedPoint) || outOfPredicted(blob.getPosition(), predictedPoint))
                return 0;
            else
            {
                double jointProbability = calculateJointProbability(blob.getPosition(), predictedPoint, 0.2, 0.2);
                return jointProbability;
            }
        }
        else
        {
            double distanceProbability = calculateDistanceProbability(blob.getPosition(), track.getPosition());
            return distanceProbability;
        }
    }

    private double calculateJointProbability(Point detectedPoint, Point predictedPoint, double sigma_x, double sigma_y)
    {
        double probX = calculateGaussianProbability(detectedPoint.x, predictedPoint.x, sigma_x);
        double probY = calculateGaussianProbability(detectedPoint.y, predictedPoint.y, sigma_y);
        return probX * probY;
    }

    private double calculateGaussianProbability(double x, double mean, double sigma)
    {
        double exponent = -(Math.pow(x - mean, 2) / (2 * Math.pow(sigma, 2)));
        return Math.exp(exponent) / (Math.sqrt(2 * Math.PI) * sigma);
    }

    private double calculateDistanceProbability(Point detectedPoint, Point trackedPoint)
    {
        double distance = detectedPoint.distance(trackedPoint);
        distance = distance / (DIVIDER * SCALE_FACTOR);
        return Math.max(0, (DISTANCE_THRESHOLD - distance) / DISTANCE_THRESHOLD);
    }

    private boolean isOutRange(Point detection, Point prediction)
    {
        return (detection.distance(prediction) / (DIVIDER * SCALE_FACTOR)) > DISTANCE_THRESHOLD;
    }

    private boolean isOutsideBorder(Point prediction)
    {
        int borderX = (int) (1000 * SCALE_FACTOR);
        int borderY = (int) (1000 * SCALE_FACTOR);
        return prediction.x < 0 || prediction.y < 0 || prediction.x >= borderX || prediction.y >= borderY;
    }

    private boolean outOfPredicted(Point prediction, Point detection)
    {
        return (detection.distance(prediction) / (DIVIDER * SCALE_FACTOR)) > 0.2;
    }
}
