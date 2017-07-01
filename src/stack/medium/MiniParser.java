package stack.medium;

import stack.NestedInteger;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by poorvank on 04/10/16.
 */
public class MiniParser {

    public NestedInteger deserialize(String s) {
        NestedInteger nestedInteger = new NestedInteger();
        int val = 0;
        Stack<Integer> stack = new Stack<>();
        Stack<Character> charStack = new Stack<>();

        // "[123,[456,[789]]]"

        for (int i=0;i<s.length();i++) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) {
                val *= 10;
                val += (c - '0');
            } else if(c==',') {
                stack.push(val);
                charStack.push(c);
                val = 0;
            } else if(c=='[') {
                charStack.push(c);
            } else if(c==']') {
                if(charStack.peek()=='[') {
                    NestedInteger n = new NestedInteger();
                    System.out.println(n);
                    if(!stack.isEmpty()) {
                        n.setInteger(stack.pop());
                        nestedInteger.add(n);
                    }
                    charStack.pop();
                } else {
                    while (charStack.peek()!='[') {
                        if(!stack.isEmpty()) {
                            Integer n = stack.pop();
                            nestedInteger.setInteger(n);
                        }
                        charStack.pop();
                    }
                    charStack.pop();
                }

            }

        }

        if(charStack.isEmpty()){
            if(!stack.isEmpty()) {
                nestedInteger.setInteger(stack.pop());
            }
            else if(val!=0) {
                nestedInteger.setInteger(val);
            }
        }

        return nestedInteger;

    }

}
