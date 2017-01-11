package linearClassification.nueralNet.networkBuilders;

import linearClassification.io.image.ImageConfig;
import linearClassification.nueralNet.networkGraph.NetworkGraph;
import linearClassification.nueralNet.Operand;

import java.util.*;

/**
 * @author Dylan McGuire
 */
public class ImageClassifyingNetworkBuilder implements NetworkBuilder{

    private final NetworkBuilder linearClassifyingNetworkBuilder;

    public ImageClassifyingNetworkBuilder(ImageConfig imageConfig) {
        linearClassifyingNetworkBuilder = new LinearClassifyingNetworkBuilder(
                imageConfig.getMaxHeight() * imageConfig.getMaxWidth() * imageConfig.getNumberOfColorChannels()
        );
    }


    @Override
    public Collection<Operand> build(NetworkGraph networkGraph) {
        return linearClassifyingNetworkBuilder.build(networkGraph);
    }

}
