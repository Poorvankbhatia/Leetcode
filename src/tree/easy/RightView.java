/*

Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

Example:

Input: [1,2,3,null,5,null,4]
Output: [1, 3, 4]
Explanation:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
 */

package tree.easy;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by poorvank on 15/09/16.
 */
public class RightView {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root==null) {
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0;i<size;i++) {
                TreeNode poll = queue.poll();
                if(i==size-1) {
                    list.add(poll.val);
                }
                if(poll.left!=null) {
                    queue.add(poll.left);
                }
                if(poll.right!=null) {
                    queue.add(poll.right);
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);

        System.out.println(new RightView().rightSideView(root));
    }

}


/*

Another method:

private static int max;
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        max = 0;
        return viewUtil(root,list,1);
    }

    private List<Integer> viewUtil(TreeNode root, List<Integer> list, int level) {

        if(root==null) {
            return list;
        }

        if(max<level) {
            max = level;
            list.add(root.val);
        }

        list = viewUtil(root.right,list,level+1);
        return viewUtil(root.left,list,level+1);

    }

 */