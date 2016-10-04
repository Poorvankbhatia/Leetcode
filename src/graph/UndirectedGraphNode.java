package graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 19/09/16.
 */
public class UndirectedGraphNode {
    public int label;
    public List<UndirectedGraphNode> neighbors;

    public UndirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<>();
    }
};
