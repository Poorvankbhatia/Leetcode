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
package graph.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by poorvank on 19/09/16.
 */
public class CloneGraph {

    private class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {}

        public Node(int _val,List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    };

    Map<Node,Node> map;

    public Node cloneGraph(Node node) {
        map = new HashMap<>();
        copyNodes(node);
        for(Node n : map.keySet()) {
            copyStructure(n);
        }
        return map.get(node);
    }

    private void copyNodes(Node node) {
        if(node!=null && !map.containsKey(node)) {
            int val = node.val;
            Node nodeCopy = new Node(val,new ArrayList<>());
            map.put(node,nodeCopy);
            if(node.neighbors!=null) {
                for(Node next : node.neighbors) {
                    copyNodes(next);
                }
            }
        }
    }

    private void copyStructure(Node node) {
        if (node != null) {
            if (node.neighbors != null) {
                for (Node next : node.neighbors) {
                    map.get(node).neighbors.add(map.get(next));
                }
            }
        }
    }
}

/*

One Function call:

private HashMap<Integer, UndirectedGraphNode> map = new HashMap<>();
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        return clone(node);
    }

    private UndirectedGraphNode clone(UndirectedGraphNode node) {
        if (node == null) return null;

        if (map.containsKey(node.label)) {
            return map.get(node.label);
        }
        UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
        map.put(clone.label, clone);
        for (UndirectedGraphNode neighbor : node.neighbors) {
            clone.neighbors.add(clone(neighbor));
        }
        return clone;
    }

 */