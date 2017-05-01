/*

Remove all elements from a linked list of integers that have value val.

Example
Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
Return: 1 --> 2 --> 3 --> 4 --> 5



 */
package linklist.easy;

/**
 * Created by poorvank.b on 30/04/17.
 */
public class RemoveElements {

    public ListNode removeElements(ListNode head, int val) {

        while (head!=null && head.val==val) {
            head = head.next;
        }

        ListNode tmp = head;

        while (tmp!=null && tmp.next!=null) {
            if(tmp.next.val==val) {
                tmp.next = tmp.next.next;
            } else {
                tmp = tmp.next;
            }
        }

        return head;

    }

    public static void main(String[] args) {
        ListNode node = new ListNode(6);
        node.next = new ListNode(1);
        node.next.next = new ListNode(6);
        System.out.println(new RemoveElements().removeElements(node,6));
    }

}
