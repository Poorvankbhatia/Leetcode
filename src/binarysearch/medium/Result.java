package binarysearch.medium;

import java.util.*;

public class Result {

    public static class BSTNode {
        public int value;
        public BSTNode left;
        public BSTNode right;

        BSTNode(int value) {
            this.value = value;
            left = null;
            right = null;
        }
    }

    public static boolean isABinarySearchTree(BSTNode startNode, Set<Integer> set) {
        if(null == startNode) {
            return true;
        }
        if(!set.remove(startNode.value)) {
            return false;
        }
        if(startNode.left != null && startNode.right != null) {
            return (startNode.value > startNode.left.value && (isABinarySearchTree(startNode.left, set)) &&
                    (startNode.value < startNode.right.value && isABinarySearchTree(startNode.right, set)));
        }
        if(startNode.right != null) {
            return (startNode.value < startNode.right.value && isABinarySearchTree(startNode.right, set));
        }
        if(startNode.left != null) {
            return (startNode.value > startNode.left.value && isABinarySearchTree(startNode.left, set));
        }
        return true;
    }

    public static int findRoot(List<List<Integer>> nodes) {
        Map<Integer, BSTNode> nodeMap = new HashMap<>();
        Map<Integer, Integer> parentMap = new HashMap<>();

        if(nodes.size()==1) {
            return nodes.get(0).get(0);
        }

        for(List<Integer> node : nodes) {
            nodeMap.put(node.get(0), new BSTNode(node.get(0)));
            if(node.get(1) != -1) {
                if(parentMap.containsKey(node.get(1))) return -1;
                parentMap.put(node.get(1), node.get(0));
            }
            if(node.get(2) != -1) {
                if(parentMap.containsKey(node.get(2))) return -1;
                parentMap.put(node.get(2), node.get(0));
            }
        }

        for(List<Integer> node: nodes) {
            BSTNode n = nodeMap.get(node.get(0));
            n.left = nodeMap.get(node.get(1));
            n.right = nodeMap.get(node.get(2));
        }

        if(parentMap.size() >= nodeMap.size())
            return -1;

        int result = -1;
        for(Integer n: nodeMap.keySet()) {
            if(parentMap.containsKey(n)){
                result = parentMap.get(n);
            }
        }
        Set<Integer> nodeSet = nodeMap.keySet();
        if(!isABinarySearchTree(nodeMap.get(result), nodeSet) || nodeSet.size() > 0)
            return -1;
        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>();
        System.out.println(Result.findRoot(list));
    }


}
