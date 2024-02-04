import java.awt.*;
import java.util.ArrayList;

public class DetectBlobs
{
    public ArrayList<Blob> detectBlobs(Color[][] imageArray)
    {
        ArrayList<Blob> blobs = new ArrayList<>();
        boolean[][] visited = new boolean[imageArray.length][imageArray[0].length];
        for (int y = 0; y < imageArray.length; y++)
        {
            for (int x = 0; x < imageArray[0].length; x++)
            {
                if (!visited[y][x] && imageArray[y][x].equals(Color.WHITE))
                {
                    Blob blob = new Blob(Color.WHITE);
                    exploreBlob(imageArray, visited, blob, x, y);
                    blobs.add(blob);
                }
            }
        }
        return blobs;
    }

    private static void exploreBlob(Color[][] imageArray, boolean[][] visited, Blob blob, int x, int y)
    {
        if (x < 0 || y < 0 || x >= imageArray[0].length || y >= imageArray.length || visited[y][x] || !imageArray[y][x].equals(blob.getColor()))
            return;

        visited[y][x] = true;
        blob.updateCenter(x, y);

        exploreBlob(imageArray, visited, blob, x + 1, y);
        exploreBlob(imageArray, visited, blob, x - 1, y);
        exploreBlob(imageArray, visited, blob, x, y + 1);
        exploreBlob(imageArray, visited, blob, x, y - 1);
    }
}
