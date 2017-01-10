package myapp.nueralNet.networkGraph;

import myapp.nueralNet.Operand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Dylan McGuire
 */
public class SOResilientNetworkGraph implements NetworkGraph{

    public static final String FINAL_OUTPUT_NAME = "confidenceInterval";

    private final Map<String, GraphNode> nameToNodeMap = new HashMap();

    private final Map<String, Operand> outputStore = new HashMap<>();

    @Override
    public void addNode(GraphNode node) {
        nameToNodeMap.put(node.getOutputName(), node);
    }


    @Override
    public Operand evaluate() {

        Collection<GraphNode> nodesOnLevel = getBottomNodes();

        while(!nodesOnLevel.isEmpty()) {
            for (GraphNode graphNode : nodesOnLevel) {
                final Operand result = graphNode.evaluateForward(this);
                outputStore.put(graphNode.getOutputName(), result);
            }

            

        }


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


    private Collection<GraphNode> getBottomNodes(){

        final ArrayList<GraphNode> bottomNodes = new ArrayList<>();

        for (GraphNode node : nameToNodeMap.values()) {
            if (node.getInputNames().isEmpty()) {
                bottomNodes.add(node);
            }
        }

        return bottomNodes;
    }

}
