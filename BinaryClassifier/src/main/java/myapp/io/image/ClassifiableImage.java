package myapp.io.image;

import myapp.classifier.Classifiable;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Dylan McGuire
 */
public class ClassifiableImage implements Classifiable{

    private final BufferedImage image;
    private final String name;

    public ClassifiableImage(BufferedImage image, String name) {
        this.image = image;
        this.name = name;
    }


    @Override
    public String getDescriptor() {
        return "Image [" + name + "]";
    }


    @Override
    public float[] getClassifiableData() {
        return flattenImage(image);
    }


    private float[] flattenImage(BufferedImage bufferedImage) {
        final float [] flattenedImage = new float[bufferedImage.getHeight() * bufferedImage.getWidth() * 3];
        int n = 0;

        for (int r = 0; r < bufferedImage.getHeight(); r++) {
            for (int c = 0; c < bufferedImage.getWidth(); c++) {
                Color color = new Color(bufferedImage.getRGB(c, r));
                flattenedImage[n] = ((float)color.getRed())/256;
                n++;
                flattenedImage[n] = ((float)color.getGreen())/256;
                n++;
                flattenedImage[n] = ((float)color.getBlue())/256;
                n++;
            }
        }

        return flattenedImage;
    }
}
