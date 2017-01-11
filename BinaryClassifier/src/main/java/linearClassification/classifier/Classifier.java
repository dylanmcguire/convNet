package linearClassification.classifier;

import linearClassification.nueralNet.Operand;
import linearClassification.nueralNet.networkGraph.NetworkGraph;

import java.util.Collection;

/**
 * @author Dylan McGuire
 */
public interface Classifier {

    Operand classify(Classifiable classifiable, NetworkGraph networkGraph, Collection<Operand> operands);

}
