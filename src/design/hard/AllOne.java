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

/**
 * Created by poorvank on 18/11/16.
 */
public class AllOne {

    private class DLLNode {
        String key;
        int val;
        DLLNode next;
        DLLNode prev;

        public DLLNode(String key,int val) {
            this(key,val,null,null);
        }

        public DLLNode(String key,int val,DLLNode next,DLLNode prev) {
            this.key = key;
            this.val = val;
            this.next = next;
            this.prev = prev;
        }
    }

    DLLNode head,tail;
    private HashMap<String,DLLNode> map;

    /** Initialize your data structure here. */
    public AllOne() {
        map = new HashMap<>();
        head = null;
        tail = null;
    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if(map.containsKey(key)) {
            DLLNode node = map.get(key);
            node.val++;
            if(node!=head && node.val>=head.val) {
                delete(node);
                makeHead(node);
            }
        } else {
            DLLNode node = new DLLNode(key,1);
            map.put(key,node);
            add(node);
        }
    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if(map.containsKey(key)) {
            DLLNode node = map.get(key);
            node.val--;
            if(node.val==0) {
                delete(node);
                map.remove(key);
            } else if(node!=tail && tail.val>=node.val) {
                delete(node);
                makeTail(node);
            }
        }
    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        if(head!=null){
            return head.key;
        }
        return "";
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if(tail!=null){
            return tail.key;
        }
        return "";
    }

    private void delete(DLLNode node) {

        if(head==tail) {
            head =tail=null;
        } else if(node==tail) {
            tail = node.prev;
            tail.next = null;
        } else if(node==head) {
            head = head.next;
            head.prev=null;
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

    }

    private void add(DLLNode node) {

        if(head==null){
            head = tail = node;
        } else if(head == tail) {
            head.next=node;
            node.prev = head;
            tail = node;
        } else if(node.val<=tail.val) { // In case the newly added node is smaller than tail
            tail.next = node;
            node.prev = tail;
            tail = node;
        } else {
            DLLNode next = head.next;
            node.next = next;
            next.prev = node;
            node.prev = head;
            head.next = node;
        }

    }

    private void makeHead(DLLNode node) {

        node.next = head;
        head.prev = node;
        head = node;

    }

    private void makeTail(DLLNode node) {

        tail.next = node;
        node.prev = tail;
        tail = node;

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