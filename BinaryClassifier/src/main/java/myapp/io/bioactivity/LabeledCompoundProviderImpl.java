package myapp.io.bioactivity;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Dylan McGuire
 */
public class LabeledCompoundProviderImpl implements LabeledCompoundProvider {

    private final File thrombinDataFile;

    public LabeledCompoundProviderImpl(File thrombinDataFile) {
        this.thrombinDataFile = thrombinDataFile;
    }





    @Override
    public Collection<LabeledCompound> getLabeledCompounds() throws IOException {

        final List<LabeledCompound> compoundList = new ArrayList<>();


        FileInputStream inputStream = FileUtils.openInputStream(thrombinDataFile);

        final BufferedReader reader = new BufferedReader(new FileReader(thrombinDataFile));

        for (Object line : FileUtils.readLines(thrombinDataFile)) {
            final String[] parts = line.toString().split(",");
            final byte[] data = new byte[parts.length - 1];
            for (int i = 1; i < parts.length; i++) {
                data[i - 1] = Byte.parseByte(parts[i]);
            }
            final LabeledCompound compound = new LabeledCompound(parts[0], new Compound(data));

            compoundList.add(compound);
        }
        return compoundList;
    }
}
