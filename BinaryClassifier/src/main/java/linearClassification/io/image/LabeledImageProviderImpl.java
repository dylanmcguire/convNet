package linearClassification.io.image;

import linearClassification.classifier.LabeledClassifiable;
import linearClassification.io.LabeledClassifiableProvider;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Dylan McGuire
 */
public class LabeledImageProviderImpl implements LabeledClassifiableProvider {

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
    public LabeledClassifiable getLabeledClassifiableByIdentifier(String identifier) throws IOException {
        final File file = nameToFile.get(identifier);
        final ClassifiableImage bufferedImage = imageProvider.loadImage(file);
        return new LabeledImage(identifier.split("_")[0], bufferedImage);
    }


    @Override
    public Collection<String> getLabeledClassifiableIdentifiers() throws IOException {
        return nameToFile.keySet();
    }


    private class ImageFileFilter implements FilenameFilter {

        @Override
        public boolean accept(File dir, String name) {
            return name.endsWith(".jpg");
        }
    }
}
