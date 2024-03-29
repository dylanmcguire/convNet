package linearClassification.io.image;

import linearClassification.classifier.Classifiable;
import linearClassification.classifier.LabeledClassifiable;

/**
 * @author Dylan McGuire
 */
public class LabeledImage implements LabeledClassifiable{

    private String label;
    private ClassifiableImage image;


    public LabeledImage(String label, ClassifiableImage image) {
        this.label = label;
        this.image = image;
    }


    @Override
    public String getLabel() {
        return label;
    }


    @Override
    public Classifiable getClassifiable() {
        return image;
    }
}
