/*

Given two 1d vectors, implement an iterator to return their elements alternately.
For example, given two 1d vectors:
v1 = [1, 2]
v2 = [3, 4, 5, 6]
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1, 3, 2, 4, 5, 6].

 */



package design.medium;

import java.util.Iterator;
import java.util.Queue;

/**
 * Created by poorvank on 17/09/16.
 */
public class ZigZagIterator {

    private Queue<Iterator<Integer>> queue;

    public ZigZagIterator(Iterator<Integer> it1,Iterator<Integer> it2) {

        if(it1.hasNext()) {
            queue.add(it1);
        }
        if(it2.hasNext()) {
            queue.add(it2);
        }

    }

    public int next()
    {
        Iterator<Integer> it = queue.remove();
        int result = it.next();
        if (it.hasNext())
        {
            queue.add(it);
        }

        return result;
    }

    public boolean hasNext()
    {
        return !queue.isEmpty();
    }

}
