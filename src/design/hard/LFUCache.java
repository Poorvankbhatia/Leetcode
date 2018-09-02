/*

Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and set.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
set(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should
invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie
(i.e., two or more keys that have the same frequency), the least recently used key would be evicted.

 */
package design.hard;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Created by poorvank on 22/11/16.
 */
public class LFUCache {

    private int capacity;
    private Node head;
    private Map<Integer,Integer> valueMap;
    private Map<Integer,Node> nodeMap;

    private class Node {
        private Node prev;
        private Node next;
        private int frequency;
        private Queue<Integer> queue;

        public Node(int frequency) {
            this.frequency = frequency;
            queue=new LinkedList<>();
            this.prev=null;
            this.next=null;
        }
    }

    public LFUCache(int capacity) {
        this.capacity = capacity;
        valueMap = new HashMap<>();
        nodeMap = new HashMap<>();
        head = new Node(1);
    }

    public int get(int key) {
        if(!valueMap.containsKey(key)) {
            return -1;
        }
        Node node = nodeMap.get(key);
        changeFrequency(node,key);
        return valueMap.get(key);
    }

    private void changeFrequency(Node node,int key) {
        int frequency = node.frequency;
        if(node.next!=null && node.next.frequency==frequency+1) {
            node.next.queue.add(key);
            nodeMap.put(key,node.next);
        } else {
            Node newNode = new Node(frequency+1);
            newNode.queue.add(key);
            nodeMap.put(key,newNode);
            Node next = node.next;
            node.next=newNode;
            newNode.prev=node;
            newNode.next=next;
            if(next!=null) {
                next.prev=newNode;
            }
        }
        node.queue.remove(key);
        if(node.queue.isEmpty()) {
            removeNode(node);
        }
    }

    private void removeNode(Node node) {
        if(node.next!=null && node.prev!=null) {
            node.prev.next=node.next;
            node.next.prev=node.prev;
        }
        if(head==node) {
            head=head.next;
        }
        if(head==null) {
            head = new Node(1);
        }
    }

    public void put(int key, int value) {
        if(capacity==0) {
            return;
        }
        if(!nodeMap.containsKey(key) && valueMap.size()==capacity) {
            int toRemove = head.queue.poll();
            nodeMap.remove(toRemove);
            valueMap.remove(toRemove);
            if(head.queue.isEmpty()) {
                removeNode(head);
            }
        }
        if(valueMap.containsKey(key)) {
            changeFrequency(nodeMap.get(key),key);
        } else {
            if(head.frequency==1) {
                head.queue.add(key);
            } else {
                Node node = new Node(1);
                node.queue.add(key);
                node.next=head;
                head.prev=node;
                head=node;
            }
            nodeMap.put(key,head);
        }
        valueMap.put(key,value);
    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache( 2 /* capacity */ );

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // returns 1
        cache.put(3, 3);    // evicts key 2
        System.out.println(cache.get(2));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3.
        cache.put(4, 4);    // evicts key 1.
        System.out.println(cache.get(1));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3
        System.out.println(cache.get(4));       // returns 4
    }

}


/*

Frequency is the time a key is accessed. Queue is a list of keys with current frequency.
The reason to use a queue is because we want to keep track the order, i.e., we can poll out the head of the
queue because the least recently used key will be removed in case there are multiple keys with the same frequency. To visualize look at the diagram.
The overall complexity is O(1).


O(logn) get and set:

//Remember recentness
    private static int time=0;

    private class HeapNode implements Comparable<HeapNode>  {

        private int key;
        private int value;
        private int accessTime;
        private int frequency;

        public HeapNode(int key,int value) {
            this.key = key;
            this.value = value;
            this.frequency = 1;
            this.accessTime = ++time;
        }

        public int compareTo(HeapNode that) {
            if(this.frequency>that.frequency) {
                return 1;
            } else if(that.frequency>this.frequency) {
                return -1;
            } else {
                if(that.accessTime>this.accessTime) {
                    return -1;
                } else {
                    return 1;
                }
            }
        }
    }

    MinPriorityQueue<HeapNode> minPriorityQueue = new MinPriorityQueue<>();
    HashMap<Integer,HeapNode> map = new HashMap<>();
    private int capacity;

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if(map.containsKey(key)) {
            HeapNode node = map.get(key);
            node.accessTime = ++time;
            node.frequency++;
            minPriorityQueue.reArrangeItems();
            return node.value;
        }
        return -1;
    }

    public void set(int key, int value) {
        if(capacity==0) {
            return;
        }
        if(map.containsKey(key)) {
            HeapNode node = map.get(key);
            node.frequency++;
            node.accessTime = ++time;
            node.value = value;
            minPriorityQueue.reArrangeItems();
        } else {
            HeapNode node = new HeapNode(key,value);
            if(map.size()>=capacity) {
                HeapNode heapNode = minPriorityQueue.deleteMin();
                map.remove(heapNode.key);
            }
            map.put(key,node);
            minPriorityQueue.insert(node);
        }
    }

get() and set() in average O(logn) time, or we will get TLE.

hashmap to remember key-value pair.
We need remember (frequency, recentness) for each key; and sort them to get the smallest one.
So, we need to use Collection such as TreeSet or PriorityQueue.

 */