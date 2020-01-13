/*

Follow up for problem "Populating Next Right Pointers in Each Node".

What if the given tree could be any binary tree? Would your previous solution still work?

Note:

You may only use constant extra space.
For example,
Given the following binary tree,
         1
       /  \
      2    3
     / \    \
    4   5    7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \    \
    4-> 5 -> 7 -> NULL

 */

package tree.hard;

import tree.TreeLinkNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by poorvank on 27/09/16.
 */
public class PopulatingNextRight {

    public void connect(TreeLinkNode root) {
        if(root!=null) {
            connectUtil(root);
        }
    }
    private void connectUtil(TreeLinkNode root) {
        Queue<TreeLinkNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i=0;i<size;i++) {
                TreeLinkNode node = queue.remove();
                if(i==size-1) {
                    node.next = null;
                } else {
                    node.next = queue.peek();
                }
                if(node.left!=null) {
                    queue.add(node.left);
                }
                if(node.right!=null) {
                    queue.add(node.right);
                }
            }
        }
    }

    public static void main(String[] args) {
        TreeLinkNode root = new TreeLinkNode(1);
        root.left = new TreeLinkNode(2);
        root.left.left = new TreeLinkNode(3);

        new PopulatingNextRight().connect(root);

        System.out.println(root.left.next!=null?root.left.next.val:"#");

    }

}

/*

CONSTANT SPACE:
public void connect(TreeLinkNode root) {
    TreeLinkNode dummyhead = new TreeLinkNode(0);
    TreeLinkNode current = null;
    while (root != null) {
        current = dummyhead;
	    while (root != null) {
		    if (root.left != null) { current.next = root.left; current = current.next; }
		    if (root.right != null) { current.next = root.right; current = current.next; }
		    root = root.next;
	    }
        root = dummyhead.next;
        dummyhead.next = null;
    }
}
 */