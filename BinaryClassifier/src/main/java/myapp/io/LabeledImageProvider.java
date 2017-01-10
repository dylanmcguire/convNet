package myapp.io;

import java.io.IOException;
import java.util.Collection;

/**
 * @author Dylan McGuire
 */
public interface LabeledImageProvider {

    Collection<String> getAvailableImageNames();

    LabeledImage loadLabeledImageByName(String name) throws IOException;

}
