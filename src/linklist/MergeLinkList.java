package linklist;

/**
 * Created by poorvank.b on 01/12/16.
 */
public class MergeLinkList {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1==null && l2==null) {
            return null;
        } else if(l1==null) {
            return l2;
        } else  if(l2==null) {
            return l1;
        } else {
            ListNode head=null;
            ListNode current=null;

            while (l1!=null && l2!=null) {

                if(l1.val<=l2.val) {
                    if(head==null) {
                        head = l1;
                        current = head;
                    } else {
                        current.next = l1;
                        current = current.next;
                    }
                    l1 = l1.next;
                } else if(l1.val>l2.val) {
                    if(head==null) {
                        head = l2;
                        current = head;
                    } else {
                        current.next = l2;
                        current = current.next;
                    }
                    l2 = l2.next;
                }

            }

            while (l1!=null) {
                current.next = l1;
                current=current.next;
                l1 = l1.next;
            }

            while (l2!=null) {
                current.next = l2;
                current=current.next;
                l2 = l2.next;
            }

            return head;

        }
    }

}
