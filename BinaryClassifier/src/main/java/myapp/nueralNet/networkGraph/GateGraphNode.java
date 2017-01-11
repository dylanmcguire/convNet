package myapp.nueralNet.networkGraph;

import myapp.nueralNet.Operand;
import myapp.nueralNet.Gate;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Dylan McGuire
 */
public class GateGraphNode implements GraphNode {

    private final Gate gate;
    private final Collection<String> inputNames;
    private final String outputName;


    public GateGraphNode(Gate gate, Collection<String> inputNames, String outputName) {
        this.gate = gate;
        this.inputNames = inputNames;
        this.outputName = outputName;
    }


    @Override
    public Collection<String> getInputNames() {
        return inputNames;
    }


    @Override
    public String getOutputName() {
        return outputName;
    }


    @Override
    public Operand evaluateForward(Operand... operands) {
        return gate.forward(operands);
    }


    @Override
    public void propagateBackward() {
        gate.backward();
    }
}
