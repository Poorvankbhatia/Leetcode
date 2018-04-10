/*

Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.
Note:
Given target value is a floating point.
You may assume k is always valid, that is: k ≤ total nodes.
You are guaranteed to have only one unique set of k values in the BST that are closest to the target.


 */
package tree.hard;

import heap.MinPriorityQueue;
import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by poorvank.b on 20/11/17.
 */
public class ClosestValueBST {

    public List<Integer> closestKValues(TreeNode root,int k,double target) {


        List<Integer> result = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();

        TreeNode node = root;
        int count=0;

        MinPriorityQueue<Integer> minPriorityQueue = new MinPriorityQueue<>();

        while (!stack.isEmpty() || node!=null) {

            while (node!=null) {
                stack.push(node);
                node = node.left;
            }

            node = stack.pop();

            if(count<k) {
                minPriorityQueue.insert(node.val);
            } else {
                if(Math.abs(minPriorityQueue.getMinimumElement()-target)>Math.abs(node.val-target)) {
                    minPriorityQueue.replaceRoot(node.val);
                } else {
                    break;
                }
            }

            count++;
            node = node.right;
        }

        while (!minPriorityQueue.isEmpty()) {
            result.add(minPriorityQueue.deleteMin());
        }

        return result;

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(40);
        root.left = new TreeNode(28);
        root.right = new TreeNode(54);
        root.left.left = new TreeNode(19);
        root.left.right = new TreeNode(36);
        root.right.left = new TreeNode(49);
        root.right.right = new TreeNode(60);
        System.out.println(new ClosestValueBST().closestKValues(root,2,55));
    }

}

/*

The straight-forward solution would be to use a heap. We just treat the BST just as a usual array and do a in-order traverse.
 Then we compare the current element with the minimum element in the heap, the same as top k problem.

The time complexity would be O(k + (n - k) logk).
Space complexity is O(k).

http://buttercola.blogspot.in/2015/09/leetcode-closest-binary-search-tree_8.html
 */