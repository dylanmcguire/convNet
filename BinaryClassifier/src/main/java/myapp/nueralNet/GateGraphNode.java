package myapp.nueralNet;

import myapp.nueralNet.gates.Gate;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Dylan McGuire
 */
public class GateGraphNode implements GraphNode{

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
    public Operand evaluateForward(NetworkGraph networkGraph) {
        final ArrayList<Operand> operands = new ArrayList<>();

        for (String inputName : inputNames) {
            operands.add(networkGraph.getNodeByName(inputName).evaluateForward(networkGraph));
        }

        return gate.forward(operands.toArray(new Operand[operands.size()]));
    }


    @Override
    public void propogateBackward(NetworkGraph networkGraph) {
        gate.backward();

        for (String inputName : inputNames) {
            networkGraph.getNodeByName(inputName).propogateBackward(networkGraph);
        }
    }
}
