package myapp.trainer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Dylan McGuire
 *
 *
 * This is shitty. Need to fix this up at some point.
 *
 */
public class LabledImageProviderImpl implements LabledImageProvider{

    final Map<String, File> nameToFile = new HashMap<>();


    public LabledImageProviderImpl(File dir) {

        for (File file : dir.listFiles()) {
            if (file.isDirectory()){
                String dirName = file.getName();
                for (File imageFile : file.listFiles()) {
                    nameToFile.put(dirName + "_" + imageFile.getName(), imageFile);
                }
            }
        }

    }


    @Override
    public Collection<String> getAvailableImageNames() {
        return nameToFile.keySet();
    }

    @Override
    public LabledImage getLabledImageByName(String name) throws IOException {
        final File file = nameToFile.get(name);
        final BufferedImage bufferedImage = ImageIO.read(file);

        return new LabledImage(name.split("_")[0], bufferedImage);
    }
}
