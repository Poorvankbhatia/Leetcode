/*

Given two 1d vectors, implement an iterator to return their elements alternately.
For example, given two 1d vectors:
v1 = [1, 2]
v2 = [3, 4, 5, 6]
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1, 3, 2, 4, 5, 6].

 */



package design.medium;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by poorvank on 17/09/16.
 */
public class ZigzagIterator {

    private Queue<List<Integer>> queue;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        queue = new LinkedList<>();
        if( v1!=null &&v1.size()!=0) {
            queue.add(v1);
        }
        if(v2!=null && v2.size()!=0) {
            queue.add(v2);
        }
    }

    public int next() {
        List<Integer> it = queue.remove();
        int result = it.remove(0);
        if(it.size()!=0) {
            queue.add(it);
        }
        return result;
    }

    public boolean hasNext() {
        return !queue.isEmpty();
    }

}
