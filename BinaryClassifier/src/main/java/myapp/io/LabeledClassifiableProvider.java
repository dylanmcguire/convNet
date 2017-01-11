package myapp.io;

import myapp.classifier.LabeledClassifiable;

import java.io.IOException;
import java.util.Collection;

/**
 * @author Dylan McGuire
 */
public interface LabeledClassifiableProvider {

    LabeledClassifiable getLabeledClassifiableByIdentifier(String identifier) throws IOException;

    Collection<String> getLabeledClassifiableIdentifiers() throws IOException;

}
