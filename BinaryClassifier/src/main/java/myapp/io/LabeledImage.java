package myapp.io;

import java.awt.image.BufferedImage;

/**
 * @author Dylan McGuire
 */
public class LabeledImage {

    private String label;
    private BufferedImage image;


    public LabeledImage(String label, BufferedImage image) {
        this.label = label;
        this.image = image;
    }


    public BufferedImage getImage() {
        return image;
    }


    public String getLabel() {
        return label;
    }
}
