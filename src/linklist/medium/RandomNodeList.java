package linklist.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by poorvank on 27/04/17.
 */
public class RandomNodeList {

    private static class RandomListNode {
        int label;
        RandomListNode next, random;
        RandomListNode(int x) {
            this.label = x;
        }
    }

    public RandomListNode copyRandomList(RandomListNode head) {

        if (head == null) return null;

        Map<RandomListNode, RandomListNode> map = new HashMap<>();

        // loop 1. copy all the nodes
        RandomListNode node = head;
        while (node != null) {
            map.put(node, new RandomListNode(node.label));
            node = node.next;
        }

        // loop 2. assign next and random pointers
        node = head;
        while (node != null) {
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);
            node = node.next;
        }

        return map.get(head);
    }

    public static void main(String[] args) {
        RandomListNode head = new RandomListNode(-1);
        System.out.println(new RandomNodeList().copyRandomList(head));
    }

}
