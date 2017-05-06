/*

Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of
k then left-out nodes in the end should remain as it is.

You may not alter the values in the nodes, only nodes itself may be changed.

Only constant memory is allowed.

For example,
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5

 */
package linklist.hard;

import linklist.easy.ListNode;

/**
 * Created by poorvank.b on 06/05/17.
 */
public class ReverseNodesKGroups {

    public ListNode reverseKGroup(ListNode head, int k) {

        if(k==0) {
            return head;
        }

        ListNode current = head;
        ListNode prev = null;
        ListNode next = null;
        int count = 0;

        while(current!=null) {
            count++;
            current = current.next;
        }

        if(count<k) {
            return head;
        } else {
            count = 0;
            current = head;
        }

        while (current!=null && count<k) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            count++;
        }

        if(next!=null) {
            head.next = reverseKGroup(next,k);
        }

        return prev;
    }
}