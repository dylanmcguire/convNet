package myapp.nueralNet;

import java.util.Collection;

/**
 * @author Dylan McGuire
 */
public interface GraphNode {

    Collection<String> getInputNames();

    String getOutputName();

    Operand evaluateForward(NetworkGraph networkGraph);

    void propogateBackward(NetworkGraph networkGraph);
}
