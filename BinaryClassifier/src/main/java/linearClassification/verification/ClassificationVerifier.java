package linearClassification.verification;

import linearClassification.classifier.Classifier;
import linearClassification.io.LabeledClassifiableProvider;
import linearClassification.nueralNet.Operand;
import linearClassification.nueralNet.networkGraph.NetworkGraph;

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
