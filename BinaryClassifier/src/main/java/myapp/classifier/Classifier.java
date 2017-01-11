package myapp.classifier;

import myapp.nueralNet.Operand;
import myapp.nueralNet.networkGraph.NetworkGraph;

import java.util.Collection;

/**
 * @author Dylan McGuire
 */
public interface Classifier {

    Operand classify(Classifiable classifiable, NetworkGraph networkGraph, Collection<Operand> operands);

}
