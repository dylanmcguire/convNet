package myapp.trainer;

import myapp.classifier.Classifier;
import myapp.io.image.LabeledImage;
import myapp.io.image.LabeledImageProvider;
import myapp.nueralNet.networkGraph.NetworkGraph;
import myapp.nueralNet.Operand;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


/**
 * @author Dylan McGuire
 */
public class ImageTrainer implements Trainer{

    private int reps;
    private LabeledImageProvider labeledImageProvider;
    private String desiredLabel;
    private Classifier imageClassifier;


    public ImageTrainer(int reps, LabeledImageProvider labeledImageProvider, String desiredLabel, Classifier imageClassifier) {
        this.reps = reps;
        this.labeledImageProvider = labeledImageProvider;
        this.desiredLabel = desiredLabel;
        this.imageClassifier = imageClassifier;
    }


    public void train(NetworkGraph networkGraph, Collection<Operand> operands){

        final ArrayList<String> labeledImageNames = new ArrayList<>(labeledImageProvider.getAvailableImageNames());

        for (int pass = 0; pass < 5; pass++) {
            for (String imageName : labeledImageNames){
                try {
                    System.out.println("=====================================================");
                    System.out.println("Training on image: " + imageName);
                    System.out.println("=====================================================");
                    final LabeledImage labeledImage = labeledImageProvider.loadLabeledImageByName(imageName);
                    for (int n = 0; n < reps; n++) {

                        if (labeledImage.getImage() != null) {

                            final Operand operand = imageClassifier.classify(labeledImage.getImage(), networkGraph, operands);

                            System.out.println("Value: " + operand.getValue());

                            if (labeledImage.getLabel().equals(desiredLabel) && operand.getValue() < 0.75) {
                                System.out.println("Pulling up");
                                operand.setGradient(1);
                            } else if (!labeledImage.getLabel().equals(desiredLabel) && operand.getValue() > 0.25){
                                System.out.println("Pulling down");
                                operand.setGradient(-1);
                            } else {
                                System.out.println("No need to pull");
                                System.out.println("----------------------------------------------");
                                break;
                            }

                            networkGraph.backprop();

                            System.out.println("testing effect");
                            final Operand secondPassOperand = imageClassifier.classify(labeledImage.getImage(), networkGraph, operands);

                            System.out.println("Value: " + secondPassOperand.getValue());

                            System.out.println("----------------------------------------------");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            Collections.shuffle(labeledImageNames);
        }

    }



}
