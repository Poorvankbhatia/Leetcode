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

        if(head==null) {
            return null;
        }

        RandomListNode tmp = head;
        RandomListNode tmpCopy = new RandomListNode(head.label);
        RandomListNode copyHead = tmpCopy;
        tmpCopy.next = null;

        Map<RandomListNode,RandomListNode> map = new HashMap<>();

        while (tmp!=null) {
            map.put(tmp,tmp.next);
            tmp = tmp.next;
        }

        tmp = head;

        while (tmp.next!=null) {
            tmp = tmp.next;
            tmpCopy.next = new RandomListNode(tmp.label);
            tmpCopy = tmpCopy.next;
        }

        tmp = head;tmpCopy=copyHead;

        while (tmpCopy!=null) {
            tmpCopy.random = tmp;
            tmp.next = tmpCopy;
            tmpCopy = tmpCopy.next;
        }

        tmpCopy = copyHead;

        while (tmpCopy!=null) {
            if(tmpCopy.random.random!=null) {
                tmpCopy.random = tmpCopy.random.random.next;
            }
            tmpCopy = tmpCopy.next;
        }

        tmp = head;

        while (tmp!=null) {
            tmp.next = map.get(tmp);
            tmp = tmp.next;
        }

        return copyHead;

    }

    public static void main(String[] args) {
        RandomListNode head = new RandomListNode(-1);
        System.out.println(new RandomNodeList().copyRandomList(head));
    }

}
