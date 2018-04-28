package linklist.easy;

import linklist.ListNode;

/**
 * Created by poorvank.b on 19/11/16.
 */
public class DeleteNode {

    public void deleteNode(ListNode node) {
        if(node!=null && node.next!=null) {
            node.val = node.next.val;
            node.next = node.next.next;
        }
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(0);
        listNode.next = new ListNode(1);

    }

}
