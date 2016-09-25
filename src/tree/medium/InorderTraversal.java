package tree.medium;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by poorvank on 24/09/16.
 */
public class InorderTraversal {

    public List<Integer> inorderTraversal(TreeNode root) {

        List<Integer> result = new ArrayList<>();

        if(root==null) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<>();

        boolean flag = true;

        while (flag) {

            while (root.left!=null) {
                stack.push(root);
                root = root.left;
            }

            while (root.right==null) {
                result.add(root.val);
                if(stack.isEmpty()) {
                    flag = false;
                    break;
                }
                root = stack.pop();
            }

            if(flag) {
                result.add(root.val);
                root = root.right;
            }

        }

        return result;

    }

}


