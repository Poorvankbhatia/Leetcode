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



O(NlogN) implementation using Priority Queue.:

class FreqStack {

    private class Val {
        private Integer count;
        private Integer time;
        private int val;
        Val(int count,int time,int val) {
            this.count = count;
            this.time = time;
            this.val = val;
        }
    }

    private int time;
    PriorityQueue<Val> pq;
    Map<Integer,Integer> map;

    public FreqStack() {
        time=0;
        map = new TreeMap<>();
        pq = new PriorityQueue<>((o1, o2) -> {
           int c = o2.count.compareTo(o1.count);
           if(c!=0) {
               return (c);
           } else {
               return o2.time.compareTo(o1.time);
           }
        });
    }


    public void push(int x) {
        map.put(x,map.getOrDefault(x,0)+1);
        pq.add(new Val(map.get(x),++time,x));
    }

    public int pop() {
        Val v =  pq.remove();
       map.put(v.val,v.count-1);
       return v.val;
    }
}

 */