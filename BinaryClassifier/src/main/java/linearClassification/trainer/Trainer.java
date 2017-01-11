package linearClassification.trainer;

import linearClassification.nueralNet.networkGraph.NetworkGraph;
import linearClassification.nueralNet.Operand;

import java.util.Collection;

/**
 * @author Dylan McGuire
 */
public interface Trainer {

    void train(NetworkGraph networkGraph, Collection<Operand> operands);

}
