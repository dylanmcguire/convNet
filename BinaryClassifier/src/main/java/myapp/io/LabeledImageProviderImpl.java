package myapp.io;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Dylan McGuire
 */
public class LabeledImageProviderImpl implements LabeledImageProvider {

    private final Map<String, File> nameToFile = new HashMap<>();
    private final ImageProvider imageProvider;


    public LabeledImageProviderImpl(File dir, ImageProvider imageProvider) {
        this.imageProvider = imageProvider;

        //This is shitty. Expecting such a specific folder structure makes this very fragile.
        for (File file : dir.listFiles()) {
            if (file.isDirectory()){
                String dirName = file.getName();
                for (File imageFile : file.listFiles(new ImageFileFilter())) {
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
        final BufferedImage bufferedImage = imageProvider.loadImage(file);
        return new LabeledImage(name.split("_")[0], bufferedImage);
    }


    private class ImageFileFilter implements FilenameFilter {

        @Override
        public boolean accept(File dir, String name) {
            return name.endsWith(".jpg");
        }
    }
}
