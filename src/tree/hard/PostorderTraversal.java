package tree.hard;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by poorvank on 24/09/16.
 */
public class PostorderTraversal {

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode q = null;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        boolean flag = true;
        while (flag) {
            while (root.left!=null) {
                stack.push(root);
                root=root.left;
            }
            while (root.right==null || q==root.right) {
                result.add(root.val);
                q = root;
                if(stack.isEmpty()) {
                    flag = false;
                    break;
                }
                root = stack.pop();
            }
            if(flag) {
                stack.push(root);
                root = root.right;
            }
        }
        return result;
    }
}

/*

Another method :

List<Integer> result = new LinkedList<>();
        if(root==null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(0,node.val);
            if(node.left!=null) {
                stack.add(node.left);
            }
            if(node.right!=null) {
                stack.add(node.right);
            }

        }
        return result;

 */
