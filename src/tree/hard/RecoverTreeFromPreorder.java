/*
We run a preorder depth first search on the root of a binary tree.

At each node in this traversal, we output D dashes (where D is the depth of this node), then we output the value of this node.
(If the depth of a node is D, the depth of its immediate child is D+1.  The depth of the root node is 0.)

If a node has only one child, that child is guaranteed to be the left child.

Given the output S of this traversal, recover the tree and return its root.


Input: "1-2--3--4-5--6--7"
Output: [1,2,5,3,4,6,7]

Input: "1-2--3---4-5--6---7"
Output: [1,2,5,3,null,6,null,4,null,7]

Input: "1-401--349---90--88"
Output: [1,401,null,349,88,90]

The number of nodes in the original tree is between 1 and 1000.
Each node will have a value between 1 and 10^9.


 */
package tree.hard;

import tree.TreeNode;

import java.util.Stack;

public class RecoverTreeFromPreorder {
    public TreeNode recoverFromPreorder(String S) {
        int level, val;
        Stack<TreeNode> stack = new Stack<>();
        for (int i = 0; i < S.length();) {
            for (level = 0; S.charAt(i) == '-'; i++) {
                level++;
            }
            for (val = 0; i < S.length() && S.charAt(i) != '-'; i++) {
                val = val * 10 + (S.charAt(i) - '0');
            }
            while (stack.size() > level) {
                stack.pop();
            }
            TreeNode node = new TreeNode(val);
            if (!stack.isEmpty()) {
                if (stack.peek().left == null) {
                    stack.peek().left = node;
                } else {
                    stack.peek().right = node;
                }
            }
            stack.add(node);
        }
        while (stack.size() > 1) {
            stack.pop();
        }
        return stack.pop();
    }
}
