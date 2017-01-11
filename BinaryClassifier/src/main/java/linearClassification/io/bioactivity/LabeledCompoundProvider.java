package linearClassification.io.bioactivity;

import linearClassification.classifier.LabeledClassifiable;
import linearClassification.io.LabeledClassifiableProvider;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.*;

/**
 * @author Dylan McGuire
 */
public class LabeledCompoundProvider implements LabeledClassifiableProvider {

    private final Map<String, LabeledCompound> labeledCompoundMap = new LinkedHashMap<>();
    private final File dataFile;


    public LabeledCompoundProvider(File dataFile) {
        this.dataFile = dataFile;
    }


    @Override
    public LabeledClassifiable getLabeledClassifiableByIdentifier(String identifier) throws IOException {
        return getMap().get(identifier);
    }


    @Override
    public Collection<String> getLabeledClassifiableIdentifiers() throws IOException {
        return getMap().keySet();
    }


    private Map<String, LabeledCompound> getMap() {
        if (labeledCompoundMap.isEmpty()) {
            try {
                loadMap();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return labeledCompoundMap;
    }


    private void loadMap() throws IOException {

        for (Object line : FileUtils.readLines(dataFile)) {
            final String[] parts = line.toString().split(",");
            final byte[] data = new byte[parts.length - 1];
            for (int i = 1; i < parts.length; i++) {
                data[i - 1] = Byte.parseByte(parts[i]);
            }
            final LabeledCompound labeledCompound = new LabeledCompound(parts[0], new Compound(data));

            labeledCompoundMap.put(UUID.randomUUID().toString(), labeledCompound);
        }
    }
}
