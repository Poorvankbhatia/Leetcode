/*

Given a nested list of integers, implement an iterator to flatten it.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:
Given the list [[1,1],2,[1,1]],

By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].

Example 2:
Given the list [1,[4,[6]]],

By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].

 */
package stack.medium;

import stack.NestedInteger;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Created by poorvank on 27/09/16.
 */
public class NestedIterator implements Iterator<Integer> {

    Stack<Integer> stack;

    public NestedIterator(List<NestedInteger> nestedList) {

        stack = new Stack<>();
        fillStack(nestedList,stack);

    }

    private void fillStack(List<NestedInteger> nestedIntegers,Stack<Integer> stack) {

        for(int i=nestedIntegers.size()-1;i>=0;i--) {
            if(null!=nestedIntegers.get(i)) {
                if(nestedIntegers.get(i).isInteger()) {
                    stack.push(nestedIntegers.get(i).getInteger());
                } else {
                    fillStack(nestedIntegers.get(i).getList(),stack);
                }
            }
        }

    }

    @Override
    public Integer next() {
        return stack.pop();
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

}
