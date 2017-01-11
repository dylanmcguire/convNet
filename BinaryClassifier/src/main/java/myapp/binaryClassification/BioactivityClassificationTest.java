package myapp.binaryClassification;

import myapp.classifier.Classifier;
import myapp.classifier.ClassifierImpl;
import myapp.io.LabeledClassifiableProvider;
import myapp.io.bioactivity.LabeledCompoundProvider;
import myapp.nueralNet.Operand;
import myapp.nueralNet.networkBuilders.LinearClassifyingNetworkBuilder;
import myapp.nueralNet.networkBuilders.NetworkBuilder;
import myapp.nueralNet.networkGraph.NetworkGraph;
import myapp.nueralNet.networkGraph.NetworkGraphImpl;
import myapp.trainer.Trainer;
import myapp.trainer.TrainerImpl;
import myapp.verification.*;

import java.io.File;
import java.io.IOException;
import java.util.Collection;


/**
 * @author Dylan McGuire
 */
public class BioactivityClassificationTest {

    private static int THROMBIN_DATA_SIZE = 139351;


    public static void main(String[] args) {

        final NetworkBuilder networkBuilder = new LinearClassifyingNetworkBuilder(THROMBIN_DATA_SIZE);
        final NetworkGraph networkGraph = new NetworkGraphImpl();
        final Classifier classifier = new ClassifierImpl();
        final Collection<Operand> operands = networkBuilder.build(networkGraph);
        final LabeledClassifiableProvider labeledClassifiableProvider = new LabeledCompoundProvider(new File("/Users/dylan/downloads/Thrombin/thrombin.data"));
        final ClassificationVerifier verifier = new ClassificationVerifierImpl();
        final VerificationResultViewer verificationResultViewer = new VerificationResultConsoleViewer("compounds");
        final String positiveLabel = "A";

        final Trainer trainer = new TrainerImpl(
                100,
                labeledClassifiableProvider,
                positiveLabel,
                classifier
        );

        trainer.train(networkGraph, operands);

        try {

            final VerificationResult result = verifier.verifyClassificationAccuracy(
                    labeledClassifiableProvider, classifier, networkGraph, operands, positiveLabel
            );

            verificationResultViewer.display(result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
