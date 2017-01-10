package myapp.nueralNet.networkGraph;

import myapp.nueralNet.GraphNode;
import myapp.nueralNet.NetworkGraph;
import myapp.nueralNet.Operand;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dylan McGuire
 */
public class NetworkGraphImpl implements NetworkGraph {

    public static final String FINAL_OUTPUT_NAME = "confidenceInterval";

    private final Map<String, GraphNode> nameToNodeMap = new HashMap();


    @Override
    public void addNode(GraphNode node) {
        nameToNodeMap.put(node.getOutputName(), node);
    }


    @Override
    public Operand evaluate() {

        final GraphNode node = getTopNode();
        Operand operand = node.evaluateForward(this);

        return operand;
    }


    @Override
    public void backprop() {
        getTopNode().propogateBackward(this);
    }


    @Override
    public GraphNode getNodeByName(String name) {
        return nameToNodeMap.get(name);
    }


    @Override
    public void replaceNodeByName(String name, GraphNode node) {
        nameToNodeMap.put(name, node);
    }


    private GraphNode getTopNode(){
        return getNodeByName(FINAL_OUTPUT_NAME);
    }

}
