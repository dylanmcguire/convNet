package linearClassification.classifier;

import linearClassification.nueralNet.Operand;
import linearClassification.nueralNet.networkGraph.NetworkGraph;
import org.apache.commons.math3.analysis.function.Sigmoid;

import java.util.Collection;

/**
 * @author Dylan McGuire
 */
public class ClassifierImpl implements Classifier{

    @Override
    public Operand classify(Classifiable classifiable, NetworkGraph networkGraph, Collection<Operand> operands) {
        final float[] data = classifiable.getClassifiableData();

        int i = 0;
        for (Operand operand : operands) {
            operand.setValue(i < data.length ? data[i] : 0);
            i++;
        }

        final Operand operand = networkGraph.evaluate();

        final Sigmoid sigmoid = new Sigmoid();
        operand.setValue( (float) sigmoid.value((double)operand.getValue()));

        return operand;
    }
}
