package myapp.binaryClassification;


import myapp.classifier.Classifier;
import myapp.classifier.ClassifierImpl;
import myapp.io.LabeledClassifiableProvider;
import myapp.io.image.*;
import myapp.nueralNet.networkGraph.NetworkGraph;
import myapp.nueralNet.Operand;
import myapp.nueralNet.networkBuilders.ImageClassifyingNetworkBuilder;
import myapp.nueralNet.networkBuilders.NetworkBuilder;
import myapp.nueralNet.networkGraph.NetworkGraphImpl;
import myapp.trainer.Trainer;
import myapp.trainer.TrainerImpl;
import myapp.verification.*;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;

/**
 * @author Dylan McGuire
 */
public class ImageClassificationTest {


    public static void main(String[] args) {

        final ImageConfig imageConfig = new ImageConfig(110, 110, 3);
        final NetworkGraph networkGraph = new NetworkGraphImpl();
        final NetworkBuilder networkBuilder = new ImageClassifyingNetworkBuilder(imageConfig);
        final Collection<Operand> inputs = networkBuilder.build(networkGraph);
        final Classifier imageClassifier = new ClassifierImpl();
        final ImageProvider imageProvider = new ImageProviderImpl(imageConfig);
        final LabeledClassifiableProvider labeledClassifiableProvider = new LabeledImageProviderImpl(
                new File("/Users/dylan/101_ObjectCategories"),
                imageProvider
        );
        final String positiveLabel = "butterfly";
        final Trainer trainer = new TrainerImpl(
                500,
                labeledClassifiableProvider,
                positiveLabel,
                imageClassifier
        );

        trainer.train(networkGraph, inputs);

        System.out.println("Training Completed!!");


        final ClassificationVerifier verifier = new ClassificationVerifierImpl();
        final VerificationResultViewer viewer = new VerificationResultConsoleViewer("images");

        try {
            final VerificationResult result = verifier.verifyClassificationAccuracy(
                    labeledClassifiableProvider, imageClassifier, networkGraph, inputs, positiveLabel
            );

            viewer.display(result);

        } catch (IOException e) {
            e.printStackTrace();
        }


        final Scanner scanner = new Scanner(System.in);


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
