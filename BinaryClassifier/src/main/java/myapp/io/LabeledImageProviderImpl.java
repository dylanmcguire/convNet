package myapp.io;

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
public class LabeledImageProviderImpl implements LabeledImageProvider {

    final Map<String, File> nameToFile = new HashMap<>();


    public LabeledImageProviderImpl(File dir) {

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
    public LabeledImage loadLabeledImageByName(String name) throws IOException {
        final File file = nameToFile.get(name);
        final BufferedImage bufferedImage = ImageIO.read(file);

        return new LabeledImage(name.split("_")[0], bufferedImage);
    }
}
