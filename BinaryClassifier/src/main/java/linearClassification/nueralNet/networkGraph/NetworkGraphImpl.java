package linearClassification.nueralNet.networkGraph;

import linearClassification.nueralNet.Operand;

import java.util.*;

/**
 * @author Dylan McGuire
 */
public class NetworkGraphImpl implements NetworkGraph {

    private static final String FINAL_OUTPUT_NAME = "confidenceInterval";

    private final Map<String, GraphNode> nameToNodeMap = new HashMap();

    private final Map<String, GraphNode> inputNameToNodeMap = new HashMap<>();

    private final Map<String, Operand> outputStore = new HashMap<>();

    @Override
    public void addNode(GraphNode node) {
        nameToNodeMap.put(node.getOutputName(), node);
        for (String inName : node.getInputNames()) {
            inputNameToNodeMap.put(inName, node);
        }
    }


    @Override
    public Operand evaluate() {

        Collection<GraphNode> nodesOnLevel = getBottomNodes();
        Set<GraphNode> nextLevel = new LinkedHashSet<>();

        while(!nodesOnLevel.isEmpty()) {
            for (GraphNode graphNode : nodesOnLevel) {
                //This null check is a little nasty. Why in the hell am I ending up with nulls in the list?
                if (graphNode != null) {
                    final Operand[] operands = new Operand[graphNode.getInputNames().size()];
                    int i = 0;
                    boolean readyToEvaluate = true;
                    for (String name : graphNode.getInputNames()) {
                        if (!outputStore.containsKey(name)) {
                            readyToEvaluate = false;
                            break;
                        }
                        operands[i] = outputStore.get(name);
                        i++;
                    }
                    if (readyToEvaluate) {
                        final Operand result = graphNode.evaluateForward(operands);
                        outputStore.put(graphNode.getOutputName(), result);
                        nextLevel.add(inputNameToNodeMap.get(graphNode.getOutputName()));
                    }
                }
            }
            nodesOnLevel = nextLevel;
            nextLevel = new LinkedHashSet<>();
        }

        final Operand confidenceIntervalOperand = outputStore.get(FINAL_OUTPUT_NAME);
        outputStore.clear();

        return confidenceIntervalOperand;
    }


    @Override
    public void backprop() {

        Collection<GraphNode> nodesOnLevel = new ArrayList<>();
        nodesOnLevel.add(getTopNode());
        Set<GraphNode> nextLevelNodes = new HashSet<>();

        while (!nodesOnLevel.isEmpty()) {
            for (GraphNode node : nodesOnLevel) {
                node.propagateBackward();
                for (String inputName : node.getInputNames()) {
                    nextLevelNodes.add(getNodeByName(inputName));
                }
            }
            nodesOnLevel = nextLevelNodes;
            nextLevelNodes = new HashSet<>();
        }

    }


    @Override
    public GraphNode getNodeByName(String name) {
        return nameToNodeMap.get(name);
    }

    @Override
    public String getFinalOutputName() {
        return FINAL_OUTPUT_NAME;
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
