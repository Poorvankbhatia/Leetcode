/*
Given a binary tree, write a function to get the maximum width of the given tree. The width of a tree is the maximum width
among all levels. The binary tree has the same structure as a full binary tree, but some nodes are null.

The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level,
where the null nodes between the end-nodes are also counted into the length calculation.

Example 1:
Input:

           1
         /   \
        3     2
       / \     \
      5   3     9

Output: 4
Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
Example 2:
Input:

          1
         /
        3
       / \
      5   3

Output: 2
Explanation: The maximum width existing in the third level with the length 2 (5,3).
Example 3:
Input:

          1
         / \
        3   2
       /
      5

Output: 2
Explanation: The maximum width existing in the second level with the length 2 (3,2).
Example 4:
Input:

          1
         / \
        3   2
       /     \
      5       9
     /         \
    6           7
Output: 8
Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).


Note: Answer will in the range of 32-bit signed integer.
 */
package tree.medium;

import tree.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Created by poorvank on 20/08/17.
 */
public class MaximumWidth {

    public int widthOfBinaryTree(TreeNode root) {

        if(root == null) return 0;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        Map<TreeNode, Integer> m = new HashMap<>();
        q.offer(root);
        m.put(root, 1);
        int current = 0;
        int max = 0;
        while(!q.isEmpty()){
            int size = q.size();
            int start = 0;
            int end = 0;
            /**
             * Each time a node is traversed, the position of the node(as it is in a full binary tree) is stored in the HashMap.
             * If the position of the parent node is 'n', then the left child is '2 * n' and the right child is '2 * n + 1'.
             * The width of each level is the last node's position in this level subtracts the first node's position in this level plus 1.
             */
            for(int i = 0; i < size; i++){
                TreeNode node = q.poll();
                if(i == 0) start = m.get(node);
                if(i == size - 1) end = m.get(node);
                if(node.left != null){
                    m.put(node.left, m.get(node) * 2);
                    q.offer(node.left);
                }
                if(node.right != null){
                    m.put(node.right, m.get(node) * 2 + 1);
                    q.offer(node.right);
                }
            }
            current = end - start + 1;
            max = Math.max(current, max);
        }
        return max;

    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(4);
        System.out.println(new MaximumWidth().widthOfBinaryTree(node));
    }

}

/*
The idea is to use heap indexing:

      1
   2     3
 4   5  6   7
you can tell that regardless whether these nodes exist:

the id of left child is parent_id * 2;
the id of right child is parent_id * 2 + 1;
So we can just:

Record the id of left most node at each level of the tree(you can tell be check the size of the container);
At each node, calculate the distance from it the left most node, and compare with the max width;

public int widthOfBinaryTree(TreeNode root) {
        if(root==null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> widthQueue = new LinkedList<>();
        widthQueue.add(0);
        queue.add(root);
        int max=1;
        int left=0,right=0;
        while(!queue.isEmpty()) {
            int size=queue.size();
            for(int i=0;i<size;i++) {
                TreeNode current = queue.poll();
                int val = widthQueue.poll();
                if(i==0) {
                    left=val;
                }
                if(i==size-1) {
                    right=val;
                }
                if(current.left!=null) {
                    queue.add(current.left);
                    widthQueue.add(2*val+1);
                }
                if(current.right!=null) {
                    queue.add(current.right);
                    widthQueue.add(2*val+2);
                }
            }
            max = Math.max(max,right-left+1);
        }

        return max;
    }

 */