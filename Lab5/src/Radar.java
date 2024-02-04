import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Radar
{
    private int i = 0;
    private HashMap<Integer, ArrayList<Tracked>> previous;
    private BitmapCreator bitmaps;
    private PDAF PDAF;
    private DetectBlobs blob_detector;
    private RadarWindow window;

    public Radar(BitmapCreator bitmaps, DetectBlobs blob_detector)
    {
        this.bitmaps = bitmaps;
        this.blob_detector = blob_detector;
        this.previous = new HashMap<>();
        this.PDAF = new PDAF();
        this.window = new RadarWindow();
    }

    public static void main(String[] args)
    {
        BitmapCreator bit_creator = new BitmapCreator("Skany");
        DetectBlobs detector = new DetectBlobs();
        Radar radar = new Radar(bit_creator, detector);
        radar.enable();
    }

    public void enable()
    {
        Thread scanningThread = new Thread(() -> {
            while (bitmaps.hasNext())
            {
                scanSpace();
                window.updateTracking(previous.get(i - 1));
                try
                {
                    Thread.sleep(250);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        scanningThread.start();
    }

    private void scanSpace()
    {
        Color[][] map;
        map = bitmaps.next();
        if (map != null)
        {
            ArrayList<Blob> blobs = blob_detector.detectBlobs(map);
            if (previous.isEmpty())
                firstDetection(blobs);
            else
            {
                ArrayList<Tracked> copy = new ArrayList<>(previous.get(i - 1));
                ArrayList<Blob> blobs1 = new ArrayList<>(blobs);
                ArrayList<Tracked> tmp = PDAF.perform(copy, blobs1);
                addPrevious(tmp);
            }
        }
    }

    private void firstDetection(ArrayList<Blob> blobs)
    {
        ArrayList<Tracked> tracked = new ArrayList<>();
        for (Blob blob : blobs)
        {
            Tracked tmp = new Tracked(blob.getPosition());
            tracked.add(tmp);
        }
        addPrevious(tracked);
    }

    private void addPrevious(ArrayList<Tracked> tracked)
    {
        this.previous.put(i, tracked);
        i++;
    }
}
