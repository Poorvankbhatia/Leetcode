/*

Given a binary tree where every node has a unique value, and a target key k, find the value of the closest leaf node to target k in the tree.

Here, closest to a leaf means the least number of edges travelled on the binary tree to reach any leaf of the tree. Also, a node is called a leaf if it has no children.

In the following examples, the input tree is represented in flattened form row by row. The actual root tree given will be a TreeNode object.

Example 1:

Input:
root = [1, 3, 2], k = 1
Diagram of binary tree:
          1
         / \
        3   2

Output: 2 (or 3)

Explanation: Either 2 or 3 is the closest leaf node to the target of 1.
Example 2:

Input:
root = [1], k = 1
Output: 1

Explanation: The closest leaf node is the root node itself.
Example 3:

Input:
root = [1,2,3,4,null,null,null,5,null,6], k = 2
Diagram of binary tree:
             1
            / \
           2   3
          /
         4
        /
       5
      /
     6

Output: 3
Explanation: The leaf node with value 3 (and not the leaf node with value 6) is closest to the node with value 2.
Note:
root represents a binary tree with at least 1 node and at most 1000 nodes.
Every node has a unique node.val in range [1, 1000].
There exists some node in the given binary tree for which node.val == k.

 */
package tree.medium;

import tree.TreeNode;

import java.util.*;

/**
 * Created by poorvank.b on 10/12/17.
 */
public class ClosestLeaf {


    public int findClosestLeaf(TreeNode root, int k) {

        Map<TreeNode,TreeNode> parentMap = new HashMap<>();
        TreeNode kNode = findK(root,parentMap,k);


        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(kNode);
        Set<Integer> visited = new TreeSet<>(); // So that we dont traverse child of parents again from parent map

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if(current.left==null && current.right==null) {
                return current.val;
            }
            if(current.left!=null && visited.add(current.left.val)) {
                queue.add(current.left);
            }
            if(current.right!=null&& visited.add(current.right.val)) {
                queue.add(current.right);
            }
            if(parentMap.containsKey(current) && visited.add(parentMap.get(current).val)) {
                queue.add(parentMap.get(current));
            }
        }

        return -1;

    }

    private TreeNode findK(TreeNode root,Map<TreeNode,TreeNode> map,int k) {

        if(root.val==k) {
            return root;
        }

        if(root.left!=null) {
            map.put(root.left,root);
            TreeNode node = findK(root.left,map,k);
            if(node!=null) {
                return node;
            }
        }

        if(root.right!=null) {
            map.put(root.right,root);
            TreeNode node = findK(root.right,map,k);
            if(node!=null) {
                return node;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right=new TreeNode(3);
        System.out.println(new ClosestLeaf().findClosestLeaf(root,3));

    }

}

/*

First, preform DFS on root in order to find the node whose val = k,
at the meantime use HashMap to keep record of all back edges from child to parent;
Then perform BFS on this node to find the closest leaf node.

 */