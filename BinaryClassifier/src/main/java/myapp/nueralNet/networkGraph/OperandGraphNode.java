package myapp.nueralNet.networkGraph;

import myapp.nueralNet.Operand;

import java.util.Collection;
import java.util.Collections;

/**
 * @author Dylan McGuire
 */
public class OperandGraphNode implements GraphNode {

    private static final float STEP_SIZE =  0.01f;

    private final String outputName;
    private final Operand operand;

    public OperandGraphNode(String outputName, Operand operand) {
        this.outputName = outputName;
        this.operand = operand;
    }


    @Override
    public Collection<String> getInputNames() {
        return Collections.emptyList();
    }

    @Override
    public String getOutputName() {
        return outputName;
    }

    @Override
    public Operand evaluateForward(NetworkGraph networkGraph) {
        return operand;
    }

    @Override
    public void propogateBackward(NetworkGraph networkGraph) {
        operand.incrementValue(operand.getGradient() * STEP_SIZE);
    }
}
