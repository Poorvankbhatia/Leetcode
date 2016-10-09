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

       root.next = null;

       TreeLinkNode tempRoot = root;

       while (tempRoot!=null) {

           TreeLinkNode temp = tempRoot;

           while (temp!=null) {

               if(temp.left!=null) {

                   if(temp.right!=null) {
                       temp.left.next = temp.right;
                   } else {
                       temp.left.next = getNextNode(temp.next);
                   }

               }

               if(temp.right!=null) {
                   temp.right.next = getNextNode(temp.next);
               }

               temp = temp.next;

           }


           if(tempRoot.left!=null) {
               tempRoot = tempRoot.left;
           } else if(tempRoot.right!=null) {
               tempRoot = tempRoot.right;
           } else {
               tempRoot = tempRoot.next;
           }

       }

    }

    private TreeLinkNode  getNextNode(TreeLinkNode root) {

       if(root!=null) {

           if(root.left!=null) {
               return root.left;
           } else if(root.right!=null) {
               return root.right;
           } else {
               return getNextNode(root.next);
           }

       }

       return null;
    }

    public static void main(String[] args) {
        TreeLinkNode root = new TreeLinkNode(1);
        root.left = new TreeLinkNode(2);
        root.left.left = new TreeLinkNode(3);

        new PopulatingNextRight().connect(root);

        System.out.println(root.left.next!=null?root.left.next.val:"#");

    }

}
