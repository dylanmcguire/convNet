package myapp.io.bioactivity;


import myapp.classifier.Classifiable;
import myapp.classifier.LabeledClassifiable;

/**
 * @author Dylan McGuire
 */
public class LabeledCompound implements LabeledClassifiable {

    private final String label;
    private final Compound compound;


    public LabeledCompound(String label, Compound compound) {
        this.label = label;
        this.compound =  compound;
    }


    public Compound getCompound() {
        return compound;
    }


    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public Classifiable getClassifiable() {
        return getCompound();
    }
}
