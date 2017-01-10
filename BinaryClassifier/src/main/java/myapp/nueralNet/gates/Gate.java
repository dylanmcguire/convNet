package myapp.nueralNet.gates;

import myapp.nueralNet.Operand;

/**
 * @author  Dylan McGuire
 */
public interface Gate {

    int getNumberOfInputs();

    Operand forward(Operand... operands);

    void backward();
}
