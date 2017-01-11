package myapp.verification;

/**
 * @author Dylan McGuire
 */
public class VerificationResult {

    private final int totalPositives;
    private final int totalPositivesReportedAsPositive;
    private final int totalNegatives;
    private final int totalNegativeReportedAsPositive;


    public VerificationResult(int totalPositives, int totalPositivesReportedAsPositive, int totalNegatives, int totalNegativeReportedAsPositive) {
        this.totalPositives = totalPositives;
        this.totalPositivesReportedAsPositive = totalPositivesReportedAsPositive;
        this.totalNegatives = totalNegatives;
        this.totalNegativeReportedAsPositive = totalNegativeReportedAsPositive;
    }


    public int getTotalPositives() {
        return totalPositives;
    }


    public int getTotalPositivesReportedAsPositive() {
        return totalPositivesReportedAsPositive;
    }


    public int getTotalNegatives() {
        return totalNegatives;
    }


    public int getTotalNegativeReportedAsPositive() {
        return totalNegativeReportedAsPositive;
    }


    public float getPercentageOfPositivesMarkedAsPositive() {
        return (((float) totalPositivesReportedAsPositive)/totalPositives) * 100;
    }


    public float getPercentageOfNegativesMarkedAsPositive() {
        return (((float) totalNegativeReportedAsPositive)/totalNegatives) * 100;
    }
}
