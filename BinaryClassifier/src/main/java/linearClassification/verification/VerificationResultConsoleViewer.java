package linearClassification.verification;

/**
 * @author Dylan McGuire
 */
public class VerificationResultConsoleViewer implements VerificationResultViewer {

    private final  String dataDescription;


    public VerificationResultConsoleViewer(String dataDescription) {
        this.dataDescription = dataDescription;
    }


    @Override
    public void display(VerificationResult verificationResult) {
        System.out.println(
                verificationResult.getPercentageOfPositivesMarkedAsPositive() + "% of the positive " + dataDescription + " were identified as positive."
        );

        System.out.println(
                verificationResult.getPercentageOfNegativesMarkedAsPositive() + "% of the negative " + dataDescription + " were identified as positive."
        );
    }
}
