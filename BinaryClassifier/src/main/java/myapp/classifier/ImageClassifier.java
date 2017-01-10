package myapp.classifier;

import myapp.nueralNet.Operand;
import myapp.nueralNet.networkGraph.NetworkGraph;

import java.awt.image.BufferedImage;
import java.util.Collection;

/**
 * @author Dylan McGuire
 */
public interface ImageClassifier {

    Operand classifyImage(BufferedImage image, NetworkGraph networkGraph, Collection<Operand> operands);

}
