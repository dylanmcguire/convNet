package linearClassification.classificationTests;

import linearClassification.classifier.Classifier;
import linearClassification.classifier.ClassifierImpl;
import linearClassification.io.LabeledClassifiableProvider;
import linearClassification.io.bioactivity.LabeledCompoundProvider;
import linearClassification.nueralNet.Operand;
import linearClassification.nueralNet.networkBuilders.LinearClassifyingNetworkBuilder;
import linearClassification.nueralNet.networkBuilders.NetworkBuilder;
import linearClassification.nueralNet.networkGraph.NetworkGraph;
import linearClassification.nueralNet.networkGraph.NetworkGraphImpl;
import linearClassification.trainer.Trainer;
import linearClassification.trainer.TrainerImpl;
import linearClassification.verification.*;

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
