package myapp.binaryClassification;

import myapp.classifier.Classifier;
import myapp.classifier.ClassifierImpl;
import myapp.io.bioactivity.LabeledCompoundProviderImpl;
import myapp.nueralNet.Operand;
import myapp.nueralNet.networkBuilders.LinearClassifyingNetworkBuilder;
import myapp.nueralNet.networkBuilders.NetworkBuilder;
import myapp.nueralNet.networkGraph.NetworkGraph;
import myapp.nueralNet.networkGraph.NetworkGraphImpl;
import myapp.trainer.CompoundTrainer;
import myapp.trainer.Trainer;

import java.io.File;
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
        final Trainer trainer = new CompoundTrainer(
                100,
                new LabeledCompoundProviderImpl(new File("/Users/dylan/downloads/Thrombin/thrombin.data")),
                "A",
                classifier
        );

        trainer.train(networkGraph, operands);
//        final LabeledCompoundProvider thrombinDataProvider = new LabeledCompoundProviderImpl(new File("/Users/dylan/downloads/Thrombin/thrombin.data"));
//        try {
//            thrombinDataProvider.getLabeledCompounds();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}
