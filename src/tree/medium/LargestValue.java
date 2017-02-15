/*

You need to find the largest value in each row of a binary tree.

Example:
Input:

          1
         / \
        3   2
       / \   \
      5   3   9

Output: [1, 3, 9]

 */

package tree.medium;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by poorvank.b on 14/02/17.
 */
public class LargestValue {

    public List<Integer> largestValues(TreeNode root) {

        List<Integer> result = new ArrayList<>();

        if(null==root) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);
        TreeNode current;

        while (!queue.isEmpty()) {

            int size = queue.size();
            int max = Integer.MIN_VALUE;
            /*
            A simple BFS technique
             */
            for (int i=0;i<size;i++) {
                current = queue.remove();
                max = Math.max(max,current.val);
                if(current.left!=null) {
                    queue.add(current.left);
                }
                if(current.right!=null) {
                    queue.add(current.right);
                }
            }
            result.add(max);

        }

        return result;

    }

}
