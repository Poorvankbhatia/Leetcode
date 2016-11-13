/*

Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity,
it should invalidate the least recently used item before inserting a new item.

 */

package design.hard;

import java.util.HashMap;

/**
 * Created by poorvank on 12/11/16.
 */
public class LRUCache {

    private int capacity;
    private DLLNode head;
    private DLLNode tail;
    private HashMap<Integer,DLLNode> map = new HashMap<>();

    private class DLLNode {
        int key;
        int value;
        DLLNode next;
        DLLNode prev;
        private DLLNode(int key,int value,DLLNode next,DLLNode prev) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }

    public LRUCache(int capacity) {

        this.capacity = capacity;
        head = null;
        tail = null;

    }

    private void removeFromList(DLLNode node) {

        if(node.prev!=null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }

        if(node.next!=null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }

    }

    public int get(int key) {

        if(map.containsKey(key)) {
            DLLNode node = map.get(key);
            removeFromList(node);
            setHead(node);
            return node.value;
        }

        return -1;

    }


    private void setHead(DLLNode node) {

        node.next = head;
        node.prev = null;

        if(head!=null) {
            head.prev = node;
        }

        head = node;

        if(tail==null) {
            tail = head;
        }
    }

    public void set(int key, int value) {

        if(map.containsKey(key)) {
            DLLNode node = map.get(key);
            node.value = value;
            removeFromList(node);
            setHead(node);
        } else {
            DLLNode node = new DLLNode(key,value,null,null);
            if(map.size()>=capacity) {
                map.remove(tail.key);
                removeFromList(tail);
                setHead(node);
            } else {
                setHead(node);
            }
            map.put(key,node);
        }

    }

}
