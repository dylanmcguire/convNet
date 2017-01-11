package linearClassification.io.image;

/**
 * @author Dylan McGuire
 */
public class ImageConfig {

    private final int maxWidth;
    private final int maxHeight;
    private final int numberOfColorChannels;


    public ImageConfig(int maxWidth, int maxHeight, int numberOfColorChannels) {
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        this.numberOfColorChannels = numberOfColorChannels;
    }


    public int getMaxHeight() {
        return maxHeight;
    }


    public int getMaxWidth() {
        return maxWidth;
    }


    public int getNumberOfColorChannels() {
        return numberOfColorChannels;
    }
}
