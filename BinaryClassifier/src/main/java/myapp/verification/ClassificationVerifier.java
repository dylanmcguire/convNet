package myapp.verification;

import myapp.classifier.Classifier;
import myapp.io.LabeledClassifiableProvider;
import myapp.nueralNet.Operand;
import myapp.nueralNet.networkGraph.NetworkGraph;

import java.io.IOException;
import java.util.Collection;

/**
 * @author Dylan McGuire
 */
public interface ClassificationVerifier {

    VerificationResult verifyClassificationAccuracy(
            LabeledClassifiableProvider labeledClassifiableProvider,
            Classifier classifier,
            NetworkGraph networkGraph,
            Collection<Operand> operands,
            String positiveLabel
    ) throws IOException;

}
