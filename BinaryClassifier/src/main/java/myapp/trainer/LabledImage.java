package myapp.trainer;

import java.awt.image.BufferedImage;

/**
 * @author Dylan McGuire
 */
public class LabledImage {

    private String label;
    private BufferedImage image;


    public LabledImage(String label, BufferedImage image) {
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
