package myapp.verification;

import myapp.classifier.Classifier;
import myapp.classifier.LabeledClassifiable;
import myapp.io.LabeledClassifiableProvider;
import myapp.nueralNet.Operand;
import myapp.nueralNet.networkGraph.NetworkGraph;

import java.io.IOException;
import java.util.Collection;

/**
 * @author Dylan McGuire
 */
public class ClassificationVerifierImpl implements ClassificationVerifier{

    @Override
    public VerificationResult verifyClassificationAccuracy(
            LabeledClassifiableProvider labeledClassifiableProvider,
            Classifier classifier,
            NetworkGraph networkGraph,
            Collection<Operand> operands,
            String positiveLabel
    ) throws IOException {

        int totalPositivesReportedAsPositive = 0;
        int totalNegativeReportedAsPositive = 0;
        int totalPositives = 0;
        int totalNegatives = 0;

        for (String  labeledClassifiableIdentifier : labeledClassifiableProvider.getLabeledClassifiableIdentifiers()) {
            final LabeledClassifiable labeledClassifiable = labeledClassifiableProvider.getLabeledClassifiableByIdentifier(labeledClassifiableIdentifier);
            final Operand result = classifier.classify(labeledClassifiable.getClassifiable(), networkGraph, operands);

            if (labeledClassifiable.getLabel().equals(positiveLabel)){
                totalPositives++;
            } else {
                totalNegatives++;
            }

            if (result.getValue() >= .5 && labeledClassifiable.getLabel().equals(positiveLabel)){
                totalPositivesReportedAsPositive++;
            } else if (result.getValue() >= .5 && !labeledClassifiable.getLabel().equals(positiveLabel)) {
                totalNegativeReportedAsPositive++;
            }
        }

        return new VerificationResult(
                totalPositives, totalPositivesReportedAsPositive, totalNegatives, totalNegativeReportedAsPositive
        );
    }

}
