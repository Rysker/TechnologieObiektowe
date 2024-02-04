import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class BitmapCreator implements Iterator
{
    private String path;
    private ArrayList<String> imageFiles;
    private int currentIndex;

    public BitmapCreator(String path)
    {
        this.path = path;
        this.imageFiles = new ArrayList<>();
        this.loadImageFiles();
        this.currentIndex = 0;
    }

    private void loadImageFiles()
    {
        File directory = new File(this.path);

        if (directory.isDirectory())
        {
            File[] files = directory.listFiles();

            if (files != null)
            {
                for (File file : files)
                {
                    if (file.isFile() && isImageFile(file.getName()))
                        this.imageFiles.add(file.getAbsolutePath());
                }
            }
        }
    }


    private Color[][] processImage(BufferedImage image)
    {
        Color[][] imageArray = new Color[image.getHeight()][image.getWidth()];

        for (int y = 0; y < image.getHeight(); y++)
        {
            for (int x = 0; x < image.getWidth(); x++)
            {
                int rgb = image.getRGB(x, y);
                Color pixelColor = new Color(rgb);
                imageArray[y][x] = pixelColor;
            }
        }
        return imageArray;
    }

    private boolean isImageFile(String fileName)
    {
        return fileName.toLowerCase().endsWith(".png") ||
                fileName.toLowerCase().endsWith(".jpg") ||
                fileName.toLowerCase().endsWith(".jpeg");
    }

    @Override
    public boolean hasNext()
    {
        if (currentIndex >= this.imageFiles.size())
            return false;
        return true;
    }

    @Override
    public Color[][] next()
    {
        if(!this.hasNext())
            return null;
        BufferedImage image = null;
        String imagePath = this.imageFiles.get(currentIndex);
        try
        {
            image = ImageIO.read(new File(imagePath));
        }
        catch(Exception e)
        {
        }
        currentIndex++;
        return processImage(image);
    }
}
