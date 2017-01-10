package myapp.io;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Dylan McGuire
 */
public interface ImageProvider {

    BufferedImage loadImage(File file) throws IOException;

}
