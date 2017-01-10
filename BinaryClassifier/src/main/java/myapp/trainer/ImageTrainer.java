package myapp.trainer;

import myapp.nueralNet.NetworkGraph;
import myapp.nueralNet.Operand;
import org.apache.commons.math3.analysis.function.Sigmoid;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

/**
 * @author Dylan McGuire
 */
public class ImageTrainer implements Trainer{

    private int reps;
    private LabledImageProvider labledImageProvider;
    private String desiredLabel;
    private ImageRunner imageRunner;


    public ImageTrainer(int reps, LabledImageProvider labledImageProvider, String desiredLabel, ImageRunner imageRunner) {
        this.reps = reps;
        this.labledImageProvider = labledImageProvider;
        this.desiredLabel = desiredLabel;
        this.imageRunner = imageRunner;
    }


    public void train(NetworkGraph networkGraph, Collection<Operand> operands){

        final ArrayList<String> labledImageNames = new ArrayList<String>(labledImageProvider.getAvailableImageNames());

        for (int pass = 0; pass < 100; pass++) {
            for (String imageName : labledImageNames){
                try {
                    System.out.println("Loading Image: " + imageName);
                    final LabledImage labledImage = labledImageProvider.getLabledImageByName(imageName);
                    for (int n = 0; n < reps; n++) {

                        if (labledImage.getImage() != null) {

                            final Operand operand = imageRunner.run(labledImage.getImage(), networkGraph, operands);

                            System.out.println("Bounded Value: " + operand.getValue());

                            if (labledImage.getLabel().equals(desiredLabel) && operand.getValue() < 0.75) {
                                System.out.println("pulling up");
                                operand.setGradient(1);
                            } else if (!labledImage.getLabel().equals(desiredLabel) && operand.getValue() > 0.25){
                                System.out.println("pulling down");
                                operand.setGradient(-1);
                            } else {
                                break;
                            }

                            networkGraph.backprop();

                            System.out.println("testing effect");
                            imageRunner.run(labledImage.getImage(), networkGraph, operands);

                            System.out.println("----------------------------------------------");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            Collections.shuffle(labledImageNames);
        }

    }



}
