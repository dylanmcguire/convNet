package myapp.io.image;

/**
 * @author Dylan McGuire
 */
public class LabeledImage {

    private String label;
    private ClassifiableImage image;


    public LabeledImage(String label, ClassifiableImage image) {
        this.label = label;
        this.image = image;
    }


    public ClassifiableImage getImage() {
        return image;
    }


    public String getLabel() {
        return label;
    }
}
