/*

We are given a linked list with head as the first node.  Let's number the nodes in the list: node_1, node_2, node_3, ... etc.

Each node may have a next larger value: for node_i, next_larger(node_i) is the node_j.val such that j > i, node_j.val > node_i.val, and j
is the smallest possible choice.  If such a j does not exist, the next larger value is 0.

Return an array of integers answer, where answer[i] = next_larger(node_{i+1}).

Note that in the example inputs (not outputs) below, arrays such as [2,1,5] represent the serialization of a linked list with a head node value of
2, second node value of 1, and third node value of 5.



Example 1:

Input: [2,1,5]
Output: [5,5,0]
Example 2:

Input: [2,7,4,3,5]
Output: [7,0,5,5,0]
Example 3:

Input: [1,7,5,1,9,2,5,1]
Output: [7,9,9,9,0,5,0,0]


Note:

1 <= node.val <= 10^9 for each node in the linked list.
The given list has length in the range [0, 10000].

 */
package linklist.medium;

import linklist.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class NextGreaterNodeLinkList {
    public int[] nextLargerNodes(ListNode head) {
        Stack<int[]> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        int size=0;
        while(head!=null) {
            list.add(0); // Just adding so that list.set function can work.
            // If we don't get any bigger value we won't require to update.
            while(!stack.isEmpty() && stack.peek()[0]<head.val)  {
                list.set(stack.pop()[1],head.val);
            }
            stack.push(new int[]{head.val,size});
            size++;
            head = head.next;
        }
        int[] result = new int[size];
        int i=0;
        for(int x : list) {
            result[i]=x;
            i++;
        }
        return result;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(2);
        listNode.next = new ListNode(7);
        listNode.next.next = new ListNode(4);
        System.out.println(Arrays.toString(new NextGreaterNodeLinkList().nextLargerNodes(listNode)));

    }
}
