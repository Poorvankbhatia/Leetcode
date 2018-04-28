/*

Reverse a linked list from position m to n. Do it in-place and in one-pass.

For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,

return 1->4->3->2->5->NULL.

Note:
Given m, n satisfy the following condition:
1 ≤ m ≤ n ≤ length of list.

 */
package linklist.medium;

import linklist.ListNode;

/**
 * Created by poorvank on 22/05/17.
 */
public class ReverseLinkList2 {

    public ListNode reverseBetween(ListNode head, int m, int n) {

        if(head==null || m>=n) {
            return head;
        }

        int i=1;
        ListNode first=null,current=head,next;

        while (i<m) {
            first = current;
            current = current.next;
            i++;
        }

        ListNode prev = null,start=current;
        // Standard reversal procedure.
        while (i<=n && current!=null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            i++;
        }

        if(first!=null) {
            first.next = prev;
        } else {
            // In case we start from the first node.
            head = prev;
        }

        if(start!=null) {
            start.next = current;
        }

        current = head;

        while (current!=null) {
            System.out.print(current.val + " ");
            current = current.next;
        }

        return head;

    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode current = head;
        while (current!=null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();

        new ReverseLinkList2().reverseBetween(head,1,5);
    }

}
