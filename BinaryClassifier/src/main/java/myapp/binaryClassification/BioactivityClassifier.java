package myapp.binaryClassification;

import myapp.classifier.Classifier;
import myapp.classifier.ClassifierImpl;
import myapp.io.bioactivity.LabeledCompound;
import myapp.io.bioactivity.LabeledCompoundProvider;
import myapp.io.bioactivity.LabeledCompoundProviderImpl;
import myapp.nueralNet.Operand;
import myapp.nueralNet.networkBuilders.LinearClassifyingNetworkBuilder;
import myapp.nueralNet.networkBuilders.NetworkBuilder;
import myapp.nueralNet.networkGraph.NetworkGraph;
import myapp.nueralNet.networkGraph.NetworkGraphImpl;
import myapp.trainer.CompoundTrainer;
import myapp.trainer.Trainer;

import java.io.File;
import java.io.IOException;
import java.util.Collection;


/**
 * @author Dylan McGuire
 */
public class BioactivityClassifier {

    private static int THROMBIN_DATA_SIZE = 139351;


    public static void main(String[] args) {

        final NetworkBuilder networkBuilder = new LinearClassifyingNetworkBuilder(THROMBIN_DATA_SIZE);
        final NetworkGraph networkGraph = new NetworkGraphImpl();
        final Classifier classifier = new ClassifierImpl();
        final Collection<Operand> operands = networkBuilder.build(networkGraph);
        final LabeledCompoundProvider labeledCompoundProvider = new LabeledCompoundProviderImpl(new File("/Users/dylan/downloads/Thrombin/thrombin.data"));

        final Trainer trainer = new CompoundTrainer(
                100,
                labeledCompoundProvider,
                "A",
                classifier
        );

        trainer.train(networkGraph, operands);

        try {
            int numberOfCorrectlyIdentifiedCompounds = 0;
            int numberOfFalsePositives = 0;
            int totalActive = 0;
            int totalInactive = 0;
            for (LabeledCompound labeledCompound : labeledCompoundProvider.getLabeledCompounds()) {
                final Operand result = classifier.classify(labeledCompound.getCompound(), networkGraph, operands);

                if (labeledCompound.getLabel().equals("A")){
                    totalActive++;
                } else {
                    totalInactive++;
                }

                if (result.getValue() >= .5 && labeledCompound.getLabel().equals("A")){
                    numberOfCorrectlyIdentifiedCompounds++;
                } else if (result.getValue() >= .5 && !labeledCompound.getLabel().equals("A")) {
                    numberOfFalsePositives++;
                }
            }

            System.out.println((((float)numberOfCorrectlyIdentifiedCompounds)/totalActive) * 100 + "% of the active compounds were recognized." );
            System.out.println((((float) numberOfFalsePositives) / totalInactive) * 100 + "% of the inactive components were identified as active.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
