package linearClassification.nueralNet.networkGraph;;import linearClassification.nueralNet.Operand;

/**
 * @author Dylan McGuire
 */
public interface NetworkGraph {

    void addNode(GraphNode node);

    Operand evaluate();

    void backprop();

    GraphNode getNodeByName(String name);

    String getFinalOutputName();
}
