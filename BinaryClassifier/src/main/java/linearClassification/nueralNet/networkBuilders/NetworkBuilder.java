package linearClassification.nueralNet.networkBuilders;

import linearClassification.nueralNet.networkGraph.NetworkGraph;
import linearClassification.nueralNet.Operand;

import java.util.Collection;

/**
 * @author Dylan McGuire
 */
public interface NetworkBuilder {

     Collection<Operand> build(NetworkGraph networkGraph);

}
