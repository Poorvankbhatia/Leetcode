/*

Implement a data structure supporting the following operations:

Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1. Key is guaranteed to be a non-empty string.

Dec(Key) - If Key's value is 1, remove it from the data structure. Otherwise decrements an existing key by 1.
If the key does not exist, this function does nothing. Key is guaranteed to be a non-empty string.

GetMaxKey() - Returns one of the keys with maximal value. If no element exists, return an empty string "".

GetMinKey() - Returns one of the keys with minimal value. If no element exists, return an empty string "".
Challenge: Perform all these in O(1) time complexity.



 */

package design.hard;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by poorvank on 18/11/16.
 */
class DLLNode {
    int freq;
    DLLNode prev;
    DLLNode next;
    Set<String> keys;

    public DLLNode(int freq) {
        this.freq = freq;
        keys = new HashSet<>();
    }
}


public class AllOne {

    private DLLNode head;
    private DLLNode tail;
    Map<String, DLLNode> map;

    /** Initialize your data structure here. */
    public AllOne() {
        head = null;
        tail = null;
        map = new HashMap<>();
    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {

        if (map.containsKey(key)) {
            DLLNode DLLNode = map.get(key);
            int freq = DLLNode.freq;
            DLLNode.keys.remove(key);
            if (DLLNode.next == null) {
                DLLNode newDLLNode = new DLLNode(freq + 1);
                DLLNode.next = newDLLNode;
                newDLLNode.prev = DLLNode;
                newDLLNode.keys.add(key);
                map.put(key, newDLLNode);
                tail = newDLLNode;
            } else {
                DLLNode next = DLLNode.next;
                if (next.freq - freq > 1) {
                    DLLNode newDLLNode = new DLLNode(freq + 1);
                    newDLLNode.keys.add(key);
                    DLLNode.next = newDLLNode;
                    newDLLNode.prev = DLLNode;
                    newDLLNode.next = next;
                    next.prev = newDLLNode;
                    map.put(key, newDLLNode);
                } else {
                    next.keys.add(key);
                    map.put(key, next);
                }
            }

            if (DLLNode.keys.size() == 0) {
                removeNode(DLLNode);
            }

        } else { // map does not contain the key
            if (head == null) {
                head = new DLLNode(1);
                head.keys.add(key);
                tail = head;
            } else {
                if (head.freq == 1) {
                    head.keys.add(key);
                } else {
                    DLLNode newDLLNode = new DLLNode(1);
                    newDLLNode.keys.add(key);
                    newDLLNode.next = head;
                    head.prev = newDLLNode;
                    head = newDLLNode;
                }
            }
            map.put(key, head);
        }
    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if (!map.containsKey(key)) {
            return;
        }
        DLLNode DLLNode = map.get(key);
        DLLNode.keys.remove(key);
        int freq = DLLNode.freq;
        if (freq == 1) {
            map.remove(key);

        } else if (DLLNode == head) {
            DLLNode newDLLNode = new DLLNode(freq - 1);
            newDLLNode.keys.add(key);
            newDLLNode.next = head;
            head.prev = newDLLNode;
            head = newDLLNode;
            map.put(key, head);
        } else {
            DLLNode prev = DLLNode.prev;
            if (freq - prev.freq == 1) {
                prev.keys.add(key);
                map.put(key, prev);
            } else {
                DLLNode newDLLNode = new DLLNode(freq - 1);
                prev.next = newDLLNode;
                newDLLNode.prev = prev;
                newDLLNode.next = DLLNode;
                DLLNode.prev = newDLLNode;
                newDLLNode.keys.add(key);
                map.put(key, newDLLNode);
            }
        }

        if (DLLNode.keys.size() == 0) {
            removeNode(DLLNode);
        }
    }


    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        if (head == null) {
            return "";
        }
        return tail.keys.iterator().next();
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if (head == null) {
            return "";
        }
        return head.keys.iterator().next();
    }


    private void removeNode(DLLNode DLLNode) {
        if (DLLNode == head) {
            head = head.next;
            DLLNode.next = null;
            if (head != null) {
                head.prev = null;
            }
        } else if (DLLNode == tail) {
            tail = tail.prev;
            DLLNode.prev = null;
            if (tail != null) {
                tail.next = null;
            }
        } else {
            DLLNode.prev.next = DLLNode.next;
            DLLNode.next.prev = DLLNode.prev;
            DLLNode.prev = null;
            DLLNode.next = null;
        }
    }

    public static void main(String[] args) {

        AllOne a = new AllOne();
        a.inc("hello");
        a.inc("hello");
        a.inc("world");
        a.inc("world");
        a.inc("hello");
        a.dec("world");
        System.out.println(a.getMaxKey());
        System.out.println(a.getMinKey());
        a.inc("world");
        a.inc("world");
        a.inc("leet");
        System.out.println(a.getMaxKey());
        System.out.println(a.getMinKey());
        a.inc("leet");
        a.inc("leet");
        System.out.println(a.getMinKey());
    }


}

/*

Using a hashmap of retrival and insert.
DLL with head as maximum and tail as minimum.

 */