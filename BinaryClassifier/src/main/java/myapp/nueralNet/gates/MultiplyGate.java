package myapp.nueralNet.gates;

import myapp.nueralNet.Gate;
import myapp.nueralNet.Operand;

/**
 * @author Dylan McGuire
 */
public class MultiplyGate implements Gate {

    private static final int NUMBER_OF_INPUTS = 2;

    private Operand output;
    private Operand operand0;
    private Operand operand1;


    @Override
    public int getNumberOfInputs() {
        return NUMBER_OF_INPUTS;
    }


    @Override
    public Operand forward(Operand... operands) {

        if (operands.length != NUMBER_OF_INPUTS) {
            throw new IllegalArgumentException("The multiply gate takes exactly " + NUMBER_OF_INPUTS + " operands");
        }

        operand0 = operands[0];
        operand1 = operands[1];

        output = new Operand(operand0.getValue() * operand1.getValue(), 0);

        return output;
    }


    @Override
    public void backward() {
        /*
          Setting the gradient of each of the operands by multiplying downstream gradient by the
          derivative of this function wrt the operand (chain rule).

          Ex: f(op0, op1) = op0 * op1

          d(f(op0, op1))
          -------------- =  op1
          d(op0)

          d(f(op0, op1))
          -------------- =  op0
          d(op1)

         */

        operand0.incrementGradient(operand1.getValue() * output.getGradient());

        operand1.incrementGradient(operand0.getValue() * output.getGradient());
    }
}
