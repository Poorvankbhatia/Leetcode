/*

Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.


OJ's undirected graph serialization:
Nodes are labeled uniquely.

We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
Visually, the graph looks like the following:

       1
      / \
     /   \
    0 --- 2
         / \
         \_/

 */
package graph;

import java.util.HashMap;

/**
 * Created by poorvank on 19/09/16.
 */
public class CloneGraph {

    private HashMap<UndirectedGraphNode,UndirectedGraphNode> map;

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {

        if(node==null) {
            return null;
        }

        map = new HashMap<>();
        fillMap(node);

        for ( UndirectedGraphNode n : map.keySet()) {
            cloneNodeUtil(n);
        }

        return map.get(node);

    }


    private void fillMap(UndirectedGraphNode node) {

        if(!map.containsKey(node)) {

            UndirectedGraphNode cloneNode = new UndirectedGraphNode(node.label);
            map.put(node,cloneNode);

            for (UndirectedGraphNode neighbour : node.neighbors) {
                if(neighbour.label!=node.label) {
                    fillMap(neighbour);
                }
            }

        }

    }

    private void cloneNodeUtil(UndirectedGraphNode node) {

        UndirectedGraphNode cloneNode = map.get(node);

        for (UndirectedGraphNode neighbour : node.neighbors) {
            cloneNode.neighbors.add(map.get(neighbour));
        }

    }

    public static void main(String[] args) {
        UndirectedGraphNode node = new UndirectedGraphNode(0);
        node.neighbors.add(new UndirectedGraphNode(0));
        node.neighbors.add(new UndirectedGraphNode(0));

        System.out.println(new CloneGraph().cloneGraph(node).neighbors.size());
    }


}
