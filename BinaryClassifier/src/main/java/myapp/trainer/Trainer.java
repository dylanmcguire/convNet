package myapp.trainer;

import myapp.nueralNet.NetworkGraph;
import myapp.nueralNet.Operand;

import java.util.Collection;

/**
 * @author Dylan McGuire
 */
public interface Trainer {

    void train(NetworkGraph networkGraph, Collection<Operand> operands);

}