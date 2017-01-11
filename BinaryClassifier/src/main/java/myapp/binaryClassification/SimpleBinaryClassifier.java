package myapp.binaryClassification;


import myapp.classifier.Classifier;
import myapp.classifier.ClassifierImpl;
import myapp.io.image.*;
import myapp.nueralNet.networkGraph.NetworkGraph;
import myapp.nueralNet.Operand;
import myapp.nueralNet.networkBuilders.ImageClassifyingNetworkBuilder;
import myapp.nueralNet.networkBuilders.NetworkBuilder;
import myapp.nueralNet.networkGraph.NetworkGraphImpl;
import myapp.trainer.ImageTrainer;
import myapp.trainer.Trainer;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;

/**
 * @author Dylan McGuire
 */
public class SimpleBinaryClassifier {


    public static void main(String[] args) {

        final ImageConfig imageConfig = new ImageConfig(110, 110, 3);
        final NetworkGraph networkGraph = new NetworkGraphImpl();
        final NetworkBuilder networkBuilder = new ImageClassifyingNetworkBuilder(imageConfig);
        final Collection<Operand> inputs = networkBuilder.build(networkGraph);
        final Classifier imageClassifier = new ClassifierImpl();
        final ImageProvider imageProvider = new ImageProviderImpl(imageConfig);
        final Trainer trainer = new ImageTrainer(
                500,
                new LabeledImageProviderImpl(
                        new File("/Users/dylan/101_ObjectCategories"),
                        imageProvider
                ),
                "butterfly",
                imageClassifier
        );

        trainer.train(networkGraph, inputs);

        System.out.println("Training Completed!!");


        final Scanner scanner = new Scanner(System.in);

        int numCorrectIdentifications = 0;
        final File[] butterflyFiles = new File("/Users/dylan/101_ObjectCategories/butterfly").listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".jpg");
            }
        });
        for (File file : butterflyFiles) {
            final ClassifiableImage image;
            try {
                image = imageProvider.loadImage(file);
                final Operand operand = imageClassifier.classify(image, networkGraph, inputs);

                if (operand.getValue() >= .5) {
                    numCorrectIdentifications++;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println((((float)numCorrectIdentifications)/butterflyFiles.length) * 100  + "% of the butterfly images were properly identified.");


        int numMissIdentifications = 0;
        final File[] buddhaFiles = new File("/Users/dylan/101_ObjectCategories/wrench").listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".jpg");
            }
        });
        for (File file : buddhaFiles) {
            final ClassifiableImage image;
            try {
                image = imageProvider.loadImage(file);
                final Operand operand = imageClassifier.classify(image, networkGraph, inputs);

                if (operand.getValue() >= .5) {
                    numMissIdentifications++;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println((((float)numMissIdentifications)/buddhaFiles.length) * 100 + "% of the wrench images were mistaken for butterflies.");


        while (true) {

            System.out.println("Enter the path to an image you would like to classify");
            String filePath = scanner.next();
            try {
                final ClassifiableImage image = imageProvider.loadImage(new File(filePath));

                Operand operand = imageClassifier.classify(image, networkGraph, inputs);

                System.out.println(operand.getValue());

                if (operand.getValue() < .5) {
                    System.out.println("This is probably not a butterfly");
                } else {
                    System.out.println("There's a good chance that this is a butterfly");
                }


            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }


}
