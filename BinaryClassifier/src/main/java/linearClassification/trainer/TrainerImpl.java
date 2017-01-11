package linearClassification.trainer;

import linearClassification.classifier.Classifier;
import linearClassification.classifier.LabeledClassifiable;
import linearClassification.io.LabeledClassifiableProvider;
import linearClassification.nueralNet.Operand;
import linearClassification.nueralNet.networkGraph.NetworkGraph;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


/**
 * @author Dylan McGuire
 */
public class TrainerImpl implements Trainer{

    private final int reps;
    private final LabeledClassifiableProvider labeledClassifiableProvider;
    private final String positiveLabel;
    private final Classifier classifier;


    public TrainerImpl(int reps, LabeledClassifiableProvider labeledClassifiableProvider, String positiveLabel, Classifier classifier) {
        this.reps = reps;
        this.labeledClassifiableProvider = labeledClassifiableProvider;
        this.positiveLabel = positiveLabel;
        this.classifier = classifier;
    }


    @Override
    public void train(NetworkGraph networkGraph, Collection<Operand> operands) {
        try {
            final ArrayList<String> labeledClassifiableIdentifiers = new ArrayList<>(labeledClassifiableProvider.getLabeledClassifiableIdentifiers());

            for (int pass = 0; pass < 25; pass++) {
                for (String labeledCompoundIdentifier : labeledClassifiableIdentifiers){
                    final LabeledClassifiable labeledCompound  = labeledClassifiableProvider.getLabeledClassifiableByIdentifier(labeledCompoundIdentifier);
                    System.out.println("=====================================================");
                    System.out.println("Training on: " + labeledCompound.getClassifiable().getDescriptor());
                    System.out.println("=====================================================");
                    for (int n = 0; n < reps; n++) {
                        final Operand operand = classifier.classify(labeledCompound.getClassifiable(), networkGraph, operands);

                        System.out.println("Value: " + operand.getValue());

                        if (labeledCompound.getLabel().equals(positiveLabel) && operand.getValue() < 0.75) {
                            System.out.println("Pulling up");
                            operand.setGradient(1);
                        } else if (!labeledCompound.getLabel().equals(positiveLabel) && operand.getValue() > 0.25){
                            System.out.println("Pulling down");
                            operand.setGradient(-1);
                        } else {
                            System.out.println("No need to pull");
                            System.out.println("----------------------------------------------");
                            break;
                        }

                        networkGraph.backprop();

                        System.out.println("testing effect");
                        final Operand secondPassOperand = classifier.classify(labeledCompound.getClassifiable(), networkGraph, operands);

                        System.out.println("Value: " + secondPassOperand.getValue());

                        System.out.println("----------------------------------------------");
                    }

                }

                Collections.shuffle(labeledClassifiableIdentifiers);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
