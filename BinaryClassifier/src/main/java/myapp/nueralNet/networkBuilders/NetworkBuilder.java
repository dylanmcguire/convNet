package myapp.nueralNet.networkBuilders;

import myapp.nueralNet.NetworkGraph;
import myapp.nueralNet.Operand;

import java.util.Collection;

/**
 * @author Dylan McGuire
 */
public interface NetworkBuilder {

     Collection<Operand> build(NetworkGraph networkGraph);

}