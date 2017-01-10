package myapp.binaryClassification;


import myapp.classifier.ImageClassifier;
import myapp.classifier.ImageClassifierImpl;
import myapp.nueralNet.networkGraph.NetworkGraph;
import myapp.nueralNet.Operand;
import myapp.nueralNet.networkBuilders.ImageClassifyingNetworkBuilder;
import myapp.nueralNet.networkBuilders.NetworkBuilder;
import myapp.nueralNet.networkGraph.NetworkGraphImpl;
import myapp.trainer.ImageTrainer;
import myapp.io.LabeledImageProviderImpl;
import myapp.trainer.Trainer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
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

//        final NetworkGraph networkGraph = new NetworkGraphImpl();
//
//        networkGraph.addNode(new OperandGraphNode("x", new Operand(3)));
//        networkGraph.addNode(new OperandGraphNode("y", new Operand(-2)));
//        networkGraph.addNode(new GateGraphNode(new MultiplyGate(), Arrays.asList("x", "y"), NetworkGraphImpl.FINAL_OUTPUT_NAME));
//
//        final Operand topNode = networkGraph.evaluate();
//        System.out.println(topNode.getValue());
//
//        topNode.setGradient(1);
//        networkGraph.backprop();
//
//        System.out.println(networkGraph.evaluate().getValue());

        final NetworkGraph networkGraph = new NetworkGraphImpl();
        final NetworkBuilder networkBuilder = new ImageClassifyingNetworkBuilder(32, 32, 3);
        final Collection<Operand> inputs = networkBuilder.build(networkGraph);
        final ImageClassifier imageClassifier = new ImageClassifierImpl();
        final Trainer trainer = new ImageTrainer(500, new LabeledImageProviderImpl(new File("/Users/dylan/101_ObjectCategories")),"butterfly", imageClassifier);
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
            final BufferedImage image;
            try {
                image = ImageIO.read(file);
                final Operand operand = imageClassifier.classifyImage(image, networkGraph, inputs);

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
            final BufferedImage image;
            try {
                image = ImageIO.read(file);
                final Operand operand = imageClassifier.classifyImage(image, networkGraph, inputs);

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
                final BufferedImage image = ImageIO.read(new File(filePath));

                Operand operand = imageClassifier.classifyImage(image, networkGraph, inputs);

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
