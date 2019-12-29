/*

Given a binary tree, return the sum of values of its deepest leaves.

Input: root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
Output: 15


Constraints:

The number of nodes in the tree is between 1 and 10^4.
The value of nodes is between 1 and 100.

 */
package tree.medium;

import tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class DeepestLeavesSum {

    public int deepestLeavesSum(TreeNode root) {
        if(root==null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int max = Integer.MIN_VALUE;
        int level=0;
        int ans = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0;i<size;i++) {
                TreeNode poll = queue.poll();
                if(poll.left==null && poll.right==null) {
                    if(level>max) {
                        ans=poll.val;
                        max=level;
                    } else if(level==max) {
                        ans+=poll.val;
                    }
                }
                if(poll.left!=null) {
                    queue.add(poll.left);
                }
                if(poll.right!=null) {
                    queue.add(poll.right);
                }
            }
            level++;
        }
        return ans;
    }

}
