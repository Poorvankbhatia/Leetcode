/*

Return the root node of a binary search tree that matches the given preorder traversal.



1 <= preorder.length <= 100
The values of preorder are distinct.

 */
package tree.medium;

import tree.TreeNode;

import java.util.Stack;

public class ConstructBSTFromPreorder {

    public TreeNode bstFromPreorder(int[] preorder) {
        if(preorder==null || preorder.length==0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int i=1;
        while(i<preorder.length) {
            TreeNode current = null;
            while(!stack.isEmpty() && preorder[i]>stack.peek().val) {
                current = stack.pop();
            }
            TreeNode val = new TreeNode(preorder[i]);
            if(current!=null) {
                current.right = val;
            } else {
                stack.peek().left = val;
            }
            stack.push(val);
            i++;
        }
        return root;
    }

}

/*

stack based iterative solution that works in O(n) time.

1. Create an empty stack.

2. Make the first value as root. Push it to the stack.



3. Keep on popping while the stack is not empty and the next value is greater than stack’s top value. Make this value as the right child of the last popped node. Push the new node to the stack.

4. If the next value is less than the stack’s top value, make this value as the left child of the stack’s top node. Push the new node to the stack.

5. Repeat steps 2 and 3 until there are items remaining in pre[].

 */