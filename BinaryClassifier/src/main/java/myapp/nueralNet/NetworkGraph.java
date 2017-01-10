package myapp.nueralNet;;

/**
 * @author Dylan McGuire
 */
public interface NetworkGraph {

    void addNode(GraphNode node);

    Operand evaluate();

    void backprop();

    GraphNode getNodeByName(String name);

    void replaceNodeByName(String name, GraphNode node);
}
