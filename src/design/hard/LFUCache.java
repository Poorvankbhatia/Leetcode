/*

Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and set.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
set(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should
invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie
(i.e., two or more keys that have the same frequency), the least recently used key would be evicted.

 */
package design.hard;

import heap.MinPriorityQueue;

import java.util.HashMap;

/**
 * Created by poorvank on 22/11/16.
 */
public class LFUCache {

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


    public static void main(String[] args) {
        LFUCache cache = new LFUCache( 0 /* capacity */ );

        cache.set(0, 0);
        cache.set(2, 2);
        System.out.println(cache.get(1));       // returns 1
        cache.set(3, 3);    // evicts key 2
        System.out.println(cache.get(2));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3.
        cache.set(4, 4);    // evicts key 1.
        System.out.println(cache.get(1));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3
        System.out.println(cache.get(4));       // returns 4
    }

}


/*

get() and set() in average O(logn) time, or we will get TLE.

hashmap to remember key-value pair.
We need remember (frequency, recentness) for each key; and sort them to get the smallest one.
So, we need to use Collection such as TreeSet or PriorityQueue.


Better O(1) solution :

Two HashMaps are used, one to store <key, value> pair, another store the <key, node>.
A double linked list to keep the frequeny of each key.
In each double linked list node,
keys with the same count are saved using java built in LinkedHashSet. This can keep the order.

class Node {
        public int count = 0;
        public LinkedHashSet<Integer> keys = null;
        public Node prev = null, next = null;

        public Node(int count) {
            this.count = count;
            keys = new LinkedHashSet<Integer>();
            prev = next = null;
        }
    }

Every time, one key is referenced, first find the current node corresponding to the key,
If the following node exist and the frequent is larger by one, add key to the keys of the following node,
else create a new node and add it following the current node.
All operations are guaranteed to be O(1).


storing all keys with same counts in one node, if one of the keys in that node got hit once more,
it will moved into a new node with (count+1) if the node exits or it will be wrapped into a newly created node with (count+1).
All your operations are guaranteed O(1) no doubt.

View Discussion : https://discuss.leetcode.com/topic/69137/java-o-1-accept-solution-using-hashmap-doublelinkedlist-and-linkedhashset/2

 */