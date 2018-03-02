/*

You are given two non-empty linked lists representing two non-negative integers. The most significant digit
comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Follow up:
What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

Example:

Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7

 */
package linklist.medium;

import linklist.easy.ListNode;

/**
 * Created by poorvank on 12/06/17.
 */
public class AddTwoNos {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        if(l1==null) {
            return l2;
        }
        if(l2==null) {
            return l1;
        }

        int size1 = 0,size2=0;

        ListNode head1 = l1,head2=l2;

        while (head1!=null) {
            head1 = head1.next;
            size1++;
        }

        while (head2!=null) {
            head2 = head2.next;
            size2++;
        }

        if(size1==size2) {
            if(calculateSumSameSize(l1,l2)==0) {
                return l1;
            } else {
                ListNode current = new ListNode(1);
                current.next = l1;
                return current;
            }
        } else {
            int diff = Math.abs(size1-size2);

            //l1 will always be greater than l2

            if (size1 < size2) {
                ListNode temp = l1;
                l1 = l2;
                l2 = temp;
            }

            ListNode current = l1;
            while (diff>0) {
                current = current.next;
                diff--;
            }

            int carry = calculateSumSameSize(current,l2);
            int lastCarry = addCarry(l1,carry,current);

            if(lastCarry!=0) {
                ListNode x = new ListNode(1);
                x.next = l1;
                return x;
            } else {
                return l1;
            }

        }

    }

    private int addCarry(ListNode l1,int carry,ListNode curr) {

        if (l1 != curr) {
            carry = addCarry(l1.next, carry, curr);

            l1.val+= + carry;
            carry = l1.val / 10;
            l1.val = l1.val%10;

        }

        return carry;
    }

    private int calculateSumSameSize(ListNode h1,ListNode h2) {

        if(h1==null || h2==null) {
            return 0;
        }

        int carry = calculateSumSameSize(h1.next,h2.next);
        h1.val += h2.val+carry;
        carry = (h1.val)/10;
        h1.val %= 10;
        return carry;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(9);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(8);
        l2.next.next = new ListNode(9);

        ListNode result = new AddTwoNos().addTwoNumbers(l1,l2);
        while (result!=null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }

}

/*

Following are the steps.
1) Calculate sizes of given two linked lists.
2) If sizes are same, then calculate sum using recursion. Hold all nodes in recursion call stack till the rightmost node,
calculate sum of rightmost nodes and forward carry to left side.
3) If size is not same, then follow below steps:
….a) Calculate difference of sizes of two linked lists. Let the difference be diff
….b) Move diff nodes ahead in the bigger linked children. Now use step 2 to calculate sum of smaller children and right sub-children (of same size)
of larger children. Also, store the carry of this sum.
….c) Calculate sum of the carry (calculated in previous step) with the remaining left sub-children of larger children. Nodes of this sum are added
at the beginning of sum children obtained previous step.



 */