package myapp.io.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Dylan McGuire
 */
public interface ImageProvider {

    ClassifiableImage loadImage(File file) throws IOException;

}
