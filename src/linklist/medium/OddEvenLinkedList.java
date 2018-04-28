/*

Given a singly linked list, group all odd nodes together followed by the even nodes.
Please note here we are talking about the node number and not the value in the nodes.

You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.

Example:
Given 1->2->3->4->5->NULL,
return 1->3->5->2->4->NULL.

Note:
The relative order inside both the even and odd groups should remain as it was in the input.
The first node is considered odd, the second node even and so on ...



 */
package linklist.medium;

import linklist.ListNode;

/**
 * Created by poorvank on 06/05/17.
 */
public class OddEvenLinkedList {

    public ListNode oddEvenList(ListNode head) {

        if(head==null) {
            return null;
        }

        ListNode oddDummy = new ListNode(1); ListNode evenDummy = new ListNode(2);
        ListNode curr1 = oddDummy, curr2 = evenDummy;
        int count = 1;
        while (head!=null) {

            if(count%2==0) {
                curr2.next = head;
                curr2 = head;
            } else {
                curr1.next = head;
                curr1 = head;
            }
            count++;
            head = head.next;
        }

        curr2.next = null;
        curr1.next = evenDummy.next;
        return oddDummy.next;

    }

}
