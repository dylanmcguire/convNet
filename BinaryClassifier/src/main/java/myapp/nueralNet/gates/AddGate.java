package myapp.nueralNet.gates;

import myapp.nueralNet.Operand;

/**
 * @author Dylan McGuire
 */
public class AddGate implements Gate {

    private static final int NUMBER_OF_INPUTS = 2;

    private Operand operand0;
    private Operand operand1;
    private Operand output;


    @Override
    public int getNumberOfInputs() {
        return NUMBER_OF_INPUTS;
    }


    @Override
    public Operand forward(Operand... operands) {

        if (operands.length != 2) {
            throw new IllegalArgumentException("The add gate takes exactly " + NUMBER_OF_INPUTS + " operands");
        }

        operand0 = operands[0];
        operand1 = operands[1];

//        try {
//            Thread.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    //    System.out.println(operand0.getValue() + " + " + operand1.getValue() + " = " + operand0.getValue() + operand1.getValue());

        output = new Operand(operand0.getValue() + operand1.getValue(), 0);

        return output;
    }

    @Override
    public void backward() {
        // really easy derivative here. It's just 1 so all we need to do is pass the outputs gradient back up the chain.

        operand0.incrementGradient(output.getGradient());
        operand1.incrementGradient(output.getGradient());
    }
}
