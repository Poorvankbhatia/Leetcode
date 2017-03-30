/*

Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

 */

package linklist;

import tree.TreeNode;

/**
 * Created by poorvank.b on 17/03/17.
 */
public class SortedListToBST {

    public TreeNode sortedListToBST(ListNode head) {

        if(head==null) {
            return null;
        }

        return util(head,null);


    }

    private TreeNode util(ListNode start,ListNode end) {

        if(start==end || start==null) {
            return null;
        }

        ListNode slow = start;
        ListNode fast = start;

        while (fast!=end && fast.next!=end) {
            slow = slow.next;
            fast = fast.next.next;
        }

        TreeNode root = new TreeNode(slow.val);

        root.left = util(start,slow);
        root.right= util(slow.next,end);

        return root;

    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(3);
        System.out.print(new SortedListToBST().sortedListToBST(node).val);
    }

}
