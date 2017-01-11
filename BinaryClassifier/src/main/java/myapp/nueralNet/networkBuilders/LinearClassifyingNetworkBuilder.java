package myapp.nueralNet.networkBuilders;

import myapp.nueralNet.Operand;
import myapp.nueralNet.gates.AddGate;
import myapp.nueralNet.gates.MultiplyGate;
import myapp.nueralNet.networkGraph.*;
import org.apache.commons.math3.analysis.function.Sigmoid;

import java.util.*;

/**
 * @author Dylan McGuire
 */
public class LinearClassifyingNetworkBuilder implements NetworkBuilder {

    private final Random random = new Random();
    private final int numberOfParameters;


    public LinearClassifyingNetworkBuilder(int numberOfParameters) {
        this.numberOfParameters = numberOfParameters;
    }


    @Override
    public Collection<Operand> build(NetworkGraph networkGraph) {

        final ArrayList<Operand> inputOperands = new ArrayList<>();

        String lastGateName = "";

        for (int n = 0; n < numberOfParameters; n++){
            final Operand inputOperand = new Operand(1);
            final Operand scalerOperand = new Operand(generateRandom());

            final String inputName = UUID.randomUUID().toString();
            final String scalerName = UUID.randomUUID().toString();
            final String multGateName = UUID.randomUUID().toString();

            final GraphNode inputGraphNode = new OperandGraphNode(inputName, inputOperand);
            final GraphNode scalerGraphNode = new OperandGraphNode(scalerName, scalerOperand);
            final GraphNode multGraphNode = new GateGraphNode(new MultiplyGate(), Arrays.asList(inputName, scalerName), multGateName);

            networkGraph.addNode(inputGraphNode);
            networkGraph.addNode(scalerGraphNode);
            networkGraph.addNode(multGraphNode);

            if (!lastGateName.isEmpty()) {
                String addGateName = UUID.randomUUID().toString();
                networkGraph.addNode(new GateGraphNode(new AddGate(), Arrays.asList(lastGateName, multGateName), addGateName));
                lastGateName = addGateName;
            } else {
                lastGateName = multGateName;
            }

            inputOperands.add(inputOperand);
        }

        //need one more variable to tug on.
        networkGraph.addNode(new OperandGraphNode("c", new Operand(generateRandom())));
        networkGraph.addNode(new GateGraphNode(new AddGate(), Arrays.asList(lastGateName, "c"), networkGraph.getFinalOutputName()));

        return inputOperands;
    }


    private float generateRandom(){
        int rand = random.nextInt(1);
        float shifted = rand - 0.5f;
        return (float) new Sigmoid(-1, 1).value((double) shifted);
    }
}
