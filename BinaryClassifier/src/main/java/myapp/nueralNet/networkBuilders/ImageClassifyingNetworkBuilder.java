package myapp.nueralNet.networkBuilders;

import myapp.io.image.ImageConfig;
import myapp.nueralNet.networkGraph.GateGraphNode;
import myapp.nueralNet.networkGraph.GraphNode;
import myapp.nueralNet.networkGraph.NetworkGraph;
import myapp.nueralNet.Operand;
import myapp.nueralNet.gates.AddGate;
import myapp.nueralNet.gates.MultiplyGate;
import myapp.nueralNet.networkGraph.NetworkGraphImpl;
import myapp.nueralNet.networkGraph.OperandGraphNode;
import org.apache.commons.math3.analysis.function.Sigmoid;

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
