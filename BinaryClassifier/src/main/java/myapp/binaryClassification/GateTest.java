package myapp.binaryClassification;


import myapp.nueralNet.Operand;
import myapp.nueralNet.Gate;
import myapp.nueralNet.gates.MultiplyGate;

/**
 * @author Dylan McGuire
 */
public class GateTest {


    public static void main(String[] args) {

        final Operand x = new Operand(3, 0);
        final Operand y = new Operand(-2, 0);

        final Gate mulGate = new MultiplyGate();

        final Operand output = mulGate.forward(x, y);

        System.out.println("Result: " + output.getValue());

        output.setGradient(1);
        mulGate.backward();

        float stepSize = 0.01f;

        x.incrementValue(x.getGradient() * stepSize);
        y.incrementValue(y.getGradient() * stepSize);

        final Operand output2  = mulGate.forward(x, y);

        System.out.println("Next Result: " + output2.getValue());
    }


}
