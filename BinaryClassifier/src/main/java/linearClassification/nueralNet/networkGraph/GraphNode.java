package linearClassification.nueralNet.networkGraph;

import linearClassification.nueralNet.Operand;

import java.util.Collection;

/**
 * @author Dylan McGuire
 */
public interface GraphNode {

    Collection<String> getInputNames();

    String getOutputName();

    Operand evaluateForward(Operand... operands);

    void propagateBackward();
}
