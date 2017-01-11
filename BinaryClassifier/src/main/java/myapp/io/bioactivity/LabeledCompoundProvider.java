package myapp.io.bioactivity;

import java.io.IOException;
import java.util.Collection;

/**
 * @author Dylan McGuire
 */
public interface LabeledCompoundProvider {

    Collection<LabeledCompound> getLabeledCompounds() throws IOException;

}
