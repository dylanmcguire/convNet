package myapp.classifier;

import myapp.nueralNet.Operand;
import myapp.nueralNet.networkGraph.NetworkGraph;
import org.apache.commons.math3.analysis.function.Sigmoid;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Collection;

/**
 * @author Dylan McGuire
 */
public class ImageClassifierImpl implements ImageClassifier{


    @Override
    public Operand classifyImage(BufferedImage image, NetworkGraph networkGraph, Collection<Operand> operands) {
        float[] flattenedImage = flattenImage(image);

        int i = 0;
        for (Operand operand : operands) {
            operand.setValue(flattenedImage[i]);
            i++;
        }

        final Operand operand = networkGraph.evaluate();

        final Sigmoid sigmoid = new Sigmoid();
        operand.setValue( (float) sigmoid.value((double)operand.getValue()));

        return operand;
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
