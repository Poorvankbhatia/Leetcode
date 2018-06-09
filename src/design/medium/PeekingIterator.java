/*

Given an Iterator class interface with methods: next() and hasNext(), design and implement a PeekingIterator that
support the peek() operation -- it essentially peek() at the element that will be returned by the next call to next().

Here is an example. Assume that the iterator is initialized to the beginning of the list: [1, 2, 3].

Call next() gets you 1, the first element in the list.

Now you call peek() and it returns 2, the next element. Calling next() after that still return 2.

You call next() the final time and it returns 3, the last element. Calling hasNext() after that should return false.

 */
package design.medium;

import java.util.Iterator;

/**
 * Created by poorvank on 17/09/16.
 */
class PeekingIterator implements Iterator<Integer> {

    private Integer peekingElement;
    private boolean hasPeeked;
    private Iterator<Integer> peekIterator;

    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        peekIterator = iterator;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if(!hasPeeked) {
            peekingElement = peekIterator.next();
            hasPeeked = true;
        }
        return peekingElement;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        if(hasPeeked) {
            Integer next = peekingElement;
            peekingElement = null;
            hasPeeked = false;
            return next;
        }
        return peekIterator.next();
    }

    @Override
    public boolean hasNext() {
        return hasPeeked || peekIterator.hasNext();
    }
}

/*
 G I
 */