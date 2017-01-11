package myapp.io.bioactivity;


/**
 * @author Dylan McGuire
 */
public class LabeledCompound {

    private final String label;
    private final Compound compound;


    public LabeledCompound(String label, Compound compound) {
        this.label = label;
        this.compound =  compound;
    }


    public Compound getCompound() {
        return compound;
    }


    public String getLabel() {
        return label;
    }
}
