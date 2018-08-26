/*

Implement FreqStack, a class which simulates the operation of a stack-like data structure.

FreqStack has two functions:

push(int x), which pushes an integer x onto the stack.
pop(), which removes and returns the most frequent element in the stack.
If there is a tie for most frequent element, the element closest to the top of the stack is removed and returned.


Example 1:

Input:
["FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"],
[[],[5],[7],[5],[7],[4],[5],[],[],[],[]]
Output: [null,null,null,null,null,null,null,5,7,5,4]
Explanation:
After making six .push operations, the stack is [5,7,5,7,4,5] from bottom to top.  Then:

pop() -> returns 5, as 5 is the most frequent.
The stack becomes [5,7,5,7,4].

pop() -> returns 7, as 5 and 7 is the most frequent, but 7 is closest to the top.
The stack becomes [5,7,5,4].

pop() -> returns 5.
The stack becomes [5,7,4].

pop() -> returns 4.
The stack becomes [5,7].


Note:

Calls to FreqStack.push(int x) will be such that 0 <= x <= 10^9.
It is guaranteed that FreqStack.pop() won't be called if the stack has zero elements.
The total number of FreqStack.push calls will not exceed 10000 in a single test case.
The total number of FreqStack.pop calls will not exceed 10000 in a single test case.
The total number of FreqStack.push and FreqStack.pop calls will not exceed 150000 across all test cases.

 */
package stack.hard;

import java.util.*;

/**
 * Created by poorvank.b on 27/08/18.
 */
public class FreqStack {

    private Map<Integer,Integer> freq;
    private Map<Integer,Stack<Integer>> stackMap;
    private int maxFreq = Integer.MIN_VALUE;

    public FreqStack() {
        freq = new HashMap<>();
        stackMap = new HashMap<>();
    }

    //O(1)
    public void push(int x) {
        freq.put(x,freq.getOrDefault(x,0)+1);
        maxFreq = Math.max(maxFreq,freq.get(x));
        if(!stackMap.containsKey(freq.get(x))) {
            stackMap.put(freq.get(x),new Stack<>());
        }
        stackMap.get(freq.get(x)).push(x);
    }

    //O(1)
    public int pop() {
        int val = stackMap.get(maxFreq).pop();
        freq.put(val,freq.get(val)-1);
        if(stackMap.get(maxFreq).size()==0) {
            maxFreq -=1;
        }
        return val;
    }

    public static void main(String[] args) {
        FreqStack freqStack = new FreqStack();
        freqStack.push(5);
        freqStack.push(7);
        freqStack.push(5);
        freqStack.push(7);
        freqStack.push(4);
        System.out.println(freqStack.pop());
    }

}


/*



O(NlogN) implementation using TreeMap:

class FreqStack {

    private class Node implements Comparable<Node> {
        Integer value;
        Integer frequency;
        Integer insertionTime;

        public Node(int value, int frequency, int insertionTime) {
            this.value = value;
            this.frequency = frequency;
            this.insertionTime = insertionTime;
        }

        @Override
        public int compareTo(Node o) {
            if(!o.frequency.equals(this.frequency)) {
                return o.frequency.compareTo(this.frequency);
            }
            return o.insertionTime.compareTo(this.insertionTime);
        }
    }

    private int insertionTime=0;
    private Map<Integer,Integer> freq;
    private TreeSet<Node> set;

    public FreqStack() {
        freq = new HashMap<>();
        set = new TreeSet<>();
    }

    public void push(int x) {
        freq.put(x,freq.getOrDefault(x,0)+1);
        Node node =new Node(x,freq.get(x), insertionTime++);
        set.add(node);
    }

    public int pop() {
        Node node = set.pollFirst();
        int res = node.value;
        freq.put(res,freq.get(res)-1);
        node.frequency-=1;
        return res;
    }

}

 */