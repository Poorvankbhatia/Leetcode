package linklist.medium;/*

Given a linked children, remove the nth node from the end of children and return its head.

For example,

   Given linked children: 1->2->3->4->5, and n = 2.

   After removing the second node from the end, the linked children becomes 1->2->3->5.
Note:
Given n will always be valid.
Try to do this in one pass.

 */

import linklist.easy.ListNode;

/**
 * Created by poorvank.b on 25/11/16.
 */
public class DeleteNthNode {

    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode slow = head;
        if(n==0) {
            return head;
        } else  if(n==1) {
            if(head.next==null) {
                head = null;
            } else {
                while (slow.next!=null && slow.next.next!=null) {
                    slow = slow.next;
                }
                slow.next = null;
            }
            return head;
        }

        ListNode fast = head;

        while (n>1) {
            fast = fast.next;
            n--;
        }

        while (fast.next!=null) {
            fast = fast.next;
            slow = slow.next;
        }

        if(slow.next!=null) {
            slow.val = slow.next.val;
            slow.next = slow.next.next;
        }

        return head;

    }

}
