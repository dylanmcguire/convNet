package myapp.nueralNet.networkBuilders;

import myapp.io.image.ImageConfig;
import myapp.nueralNet.networkGraph.NetworkGraph;
import myapp.nueralNet.Operand;

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
