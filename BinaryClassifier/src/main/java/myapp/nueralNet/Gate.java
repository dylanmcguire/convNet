package myapp.nueralNet;


/**
 * @author  Dylan McGuire
 */
public interface Gate {

    int getNumberOfInputs();

    Operand forward(Operand... operands);

    void backward();
}
