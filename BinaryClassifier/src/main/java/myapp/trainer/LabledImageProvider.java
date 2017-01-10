package myapp.trainer;

import java.io.IOException;
import java.util.Collection;

/**
 * Created by dylan on 1/9/17.
 */
public interface LabledImageProvider {

    Collection<String> getAvailableImageNames();

    LabledImage getLabledImageByName(String name) throws IOException;

}
