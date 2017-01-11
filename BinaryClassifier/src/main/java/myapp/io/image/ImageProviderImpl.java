package myapp.io.image;

import myapp.classifier.Classifiable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Dylan McGuire
 */
public class ImageProviderImpl implements ImageProvider{

    private ImageConfig imageConfig;


    public ImageProviderImpl(ImageConfig imageConfig) {
        this.imageConfig = imageConfig;
    }


    @Override
    public ClassifiableImage loadImage(File file) throws IOException {
        final BufferedImage bufferedImage = ImageIO.read(file);
        return new ClassifiableImage(scaleImage(bufferedImage));
    }


    private BufferedImage scaleImage(BufferedImage bufferedImage){

        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        int type = bufferedImage.getType();

        // Come up with a better way to scale the image you filthy animal

        if (width > imageConfig.getMaxWidth() && height > imageConfig.getMaxHeight()) {
            if (width > height) {
                width = imageConfig.getMaxWidth();
                height = Math.round(height * (((float)width)/(bufferedImage.getWidth()))) - 1;
            } else {
                height = imageConfig.getMaxHeight();
                width = Math.round(width * (((float)height)/(bufferedImage.getHeight()))) - 1;
            }
        } else if (width > imageConfig.getMaxWidth()) {
            width = imageConfig.getMaxWidth();
            height = Math.round(height * (((float)width)/(bufferedImage.getWidth()))) - 1;
        } else if (height > imageConfig.getMaxHeight()) {
            height = imageConfig.getMaxHeight();
            width = Math.round(width * (((float)height)/(bufferedImage.getHeight()))) - 1;
        }

        BufferedImage resizedImage = new BufferedImage(width, height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(bufferedImage, 0, 0, width, height, null);
        g.dispose();

        return resizedImage;
    }
}
