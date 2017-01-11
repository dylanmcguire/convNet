package myapp.trainer;

import myapp.classifier.Classifier;
import myapp.io.bioactivity.LabeledCompound;
import myapp.io.bioactivity.LabeledCompoundProvider;
import myapp.nueralNet.Operand;
import myapp.nueralNet.networkGraph.NetworkGraph;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * @author Dylan McGuire
 */
public class CompoundTrainer implements Trainer{


    private int reps;
    private LabeledCompoundProvider labeledCompoundProvider;
    private String desiredLabel;
    private Classifier imageClassifier;


    public CompoundTrainer(int reps, LabeledCompoundProvider labeledCompoundProvider, String desiredLabel, Classifier imageClassifier) {
        this.reps = reps;
        this.labeledCompoundProvider = labeledCompoundProvider;
        this.desiredLabel = desiredLabel;
        this.imageClassifier = imageClassifier;
    }


    public void train(NetworkGraph networkGraph, Collection<Operand> operands){

        try {
            final ArrayList<LabeledCompound> labeledCompounds = new ArrayList<>(labeledCompoundProvider.getLabeledCompounds());

            for (int pass = 0; pass < 5; pass++) {
                for (LabeledCompound labeledCompound : labeledCompounds){
                    System.out.println("=====================================================");
                    System.out.println("Training on compound");
                    System.out.println("=====================================================");
                    for (int n = 0; n < reps; n++) {
                        final Operand operand = imageClassifier.classify(labeledCompound.getCompound(), networkGraph, operands);

                        System.out.println("Value: " + operand.getValue());

                        if (labeledCompound.getLabel().equals(desiredLabel) && operand.getValue() < 0.75) {
                            System.out.println("Pulling up");
                            operand.setGradient(1);
                        } else if (!labeledCompound.getLabel().equals(desiredLabel) && operand.getValue() > 0.25){
                            System.out.println("Pulling down");
                            operand.setGradient(-1);
                        } else {
                            System.out.println("No need to pull");
                            System.out.println("----------------------------------------------");
                            break;
                        }

                        networkGraph.backprop();

                        System.out.println("testing effect");
                        final Operand secondPassOperand = imageClassifier.classify(labeledCompound.getCompound(), networkGraph, operands);

                        System.out.println("Value: " + secondPassOperand.getValue());

                        System.out.println("----------------------------------------------");
                    }

                }

                Collections.shuffle(labeledCompounds);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
