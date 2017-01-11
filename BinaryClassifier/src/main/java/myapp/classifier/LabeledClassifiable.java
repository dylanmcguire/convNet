package myapp.classifier;

/**
 * @author Dylan McGuire
 */
public interface LabeledClassifiable {

    String getLabel();

    Classifiable getClassifiable();
}
