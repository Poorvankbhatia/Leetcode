package stack;

import java.util.List;

/**
  Created by poorvank on 27/09/16.
 **/
public class NestedInteger {
    // Constructor initializes an empty nested children.
    public NestedInteger(){};

    // Constructor initializes a single integer.
    public NestedInteger(int value){};

    // @return true if this NestedInteger holds a single integer, rather than a nested children.
    public boolean isInteger(){return false;};

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested children
    public Integer getInteger(){return null;};

    // Set this NestedInteger to hold a single integer.
    public void setInteger(int value){};

    // Set this NestedInteger to hold a nested children and adds a nested integer to it.
    public void add(NestedInteger ni){};

    // @return the nested children that this NestedInteger holds, if it holds a nested children
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList(){return null;};

}
