package myapp.io.bioactivity;

import myapp.classifier.Classifiable;

/**
 * @author Dylan McGuire
 */
public class Compound implements Classifiable{

    private final byte[] data;

    public Compound(byte[] data) {
        this.data = data;
    }


    @Override
    public String getDescriptor(){
        return "compound";
    }


    @Override
    public float[] getClassifiableData() {

        final float[] floats = new float[data.length];

        for (int i = 0; i < floats.length; i++) {
            floats[i] = data[i];
        }

        return floats;
    }
}
