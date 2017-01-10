package myapp.nueralNet;


/**
 * @author Dylan McGuire
 */
public class Operand {

    private float value;
    private float gradient;

    public Operand(float value) {
        this(value, 0);
    }


    public Operand(float value, float gradient) {
        this.value = value;
        this.gradient = gradient;
    }


    public float getValue() {
        return value;
    }


    public void setValue(float value) {
        this.value = value;
    }


    public void incrementValue(float value) {
        this.value += value;
    }


    public float getGradient() {
        return gradient;
    }


    public void incrementGradient(float gradient) {
        this.gradient = gradient;
    }


    public void setGradient(float gradient) {
        this.gradient = gradient;
    }

}
