/*

Given a binary tree, return the vertical order traversal of its nodes values.

For each node at position (X, Y), its left and right children respectively will be at positions (X-1, Y-1) and (X+1, Y-1).

Running a vertical line from X = -infinity to X = +infinity, whenever the vertical line touches some nodes, we report the values
of the nodes in order from top to bottom (decreasing Y coordinates).

If two nodes have the same position, then the value of the node that is reported first is the value that is smaller.

Return an list of non-empty reports in order of X coordinate.  Every report will have a list of values of nodes.


Input: [1,2,3,4,5,6,7]
Output: [[4],[2],[1,5,6],[3],[7]]
Explanation:
The node with value 5 and the node with value 6 have the same position according to the given scheme.
However, in the report "[1,5,6]", the node value of 5 comes first since 5 is smaller than 6.

 */
package tree.medium;

import tree.TreeNode;

import java.util.*;

public class VerticalTraversal {

    private class Node implements Comparable<Node> {
        TreeNode node;
        Integer x;
        Integer y;

        Node(TreeNode node,Integer x,Integer y) {
            this.node=node;
            this.x=x;
            this.y=y;
        }

        @Override
        public int compareTo(Node that) {
            if(Objects.equals(this.x, that.x)) {
                return that.y.equals(this.y)?Integer.compare(this.node.val, that.node.val):that.y.compareTo(this.y);
            }
            return 0;
        }

    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(root,0,0));
        TreeMap<Integer,TreeSet<Node>> map = new TreeMap<>();
        TreeSet<Node> set = new TreeSet<>();
        set.add(new Node(root,0,0));
        map.put(0,set);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0;i<size;i++) {
                Node current = queue.poll();
                if(current.node.left!=null) {
                    Node newNode = new Node(current.node.left,current.x-1,current.y-1);
                    queue.add(newNode);
                    if(map.containsKey(newNode.x)) {
                        map.get(newNode.x).add(newNode);
                    } else {
                        TreeSet<Node> treeSet = new TreeSet<>();
                        treeSet.add(newNode);
                        map.put(newNode.x,treeSet);
                    }
                }
                if(current.node.right!=null) {
                    Node newNode = new Node(current.node.right,current.x+1,current.y-1);
                    queue.add(newNode);
                    if(map.containsKey(newNode.x)) {
                        map.get(newNode.x).add(newNode);
                    } else {
                        TreeSet<Node> treeSet = new TreeSet<>();
                        treeSet.add(newNode);
                        map.put(newNode.x,treeSet);
                    }
                }
            }
        }
        for(Map.Entry<Integer,TreeSet<Node>> entry : map.entrySet()) {
            List<Integer> list = new ArrayList<>();
            for (Node node : entry.getValue()) {
                list.add(node.node.val);
            }
            result.add(list);
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left=new TreeNode(9);
        root.right=new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(new VerticalTraversal().verticalTraversal(root));

    }

}
