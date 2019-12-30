/*

Given two binary search trees root1 and root2.

Return a list containing all the integers from both trees sorted in ascending order.


Input: root1 = [2,1,4], root2 = [1,0,3]
Output: [0,1,1,2,3,4]
Example 2:

Input: root1 = [0,-10,10], root2 = [5,1,7,0,2]
Output: [-10,0,0,1,2,5,7,10]
Example 3:

Input: root1 = [], root2 = [5,1,7,0,2]
Output: [0,1,2,5,7]
Example 4:

Input: root1 = [0,-10,10], root2 = []
Output: [-10,0,10]

Input: root1 = [1,null,8], root2 = [8,1]
Output: [1,1,8,8]


Constraints:

Each tree has at most 5000 nodes.
Each node's value is between [-10^5, 10^5].

 */
package tree.medium;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class AllElementsInTwoBST {

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {

        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        pushLeft(stack1,root1);
        pushLeft(stack2,root2);

        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            Stack<TreeNode> tempStack = stack1.isEmpty()?stack2:(stack2.isEmpty()?stack1:(stack1.peek().val<stack2.peek().val?stack1:stack2));
            TreeNode node = tempStack.pop();
            result.add(node.val);
            pushLeft(tempStack,node.right);
        }
        return result;

    }

    private void pushLeft(Stack<TreeNode> stack,TreeNode root) {
        while (root!=null) {
            stack.push(root);
            root = root.left;
        }
    }

}
