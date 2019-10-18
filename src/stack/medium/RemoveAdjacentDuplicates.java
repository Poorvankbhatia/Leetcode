/*

Given a string s, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them
causing the left and the right side of the deleted substring to concatenate together.

We repeatedly make k duplicate removals on s until we no longer can.

Return the final string after all such duplicate removals have been made.

It is guaranteed that the answer is unique.



Example 1:

Input: s = "abcd", k = 2
Output: "abcd"
Explanation: There's nothing to delete.
Example 2:

Input: s = "deeedbbcccbdaa", k = 3
Output: "aa"
Explanation:
First delete "eee" and "ccc", get "ddbbbdaa"
Then delete "bbb", get "dddaa"
Finally delete "ddd", get "aa"
Example 3:

Input: s = "pbbcggttciiippooaais", k = 2
Output: "ps"


Constraints:

1 <= s.length <= 10^5
2 <= k <= 10^4
s only contains lower case English letters.


 */
package stack.medium;

import java.util.Stack;

public class RemoveAdjacentDuplicates {

    private class Element {
        int count;
        char c;

        Element(char c,int count) {
            this.c = c;
            this.count = count;
        }

    }

    public String removeDuplicates(String s, int k) {
        Stack<Element> stack = new Stack<>();
        for(char c: s.toCharArray()) {
            if(!stack.isEmpty() && c==stack.peek().c) {
                if(stack.peek().count==k-1) {
                    stack.pop();
                } else {
                    Element x = stack.pop();
                    stack.push(new Element(c,x.count+1));
                }
            } else {
                stack.push(new Element(c,1));
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            Element e = stack.pop();
            for(int i=0;i<e.count;i++) {
                sb.append(e.c);
            }
        }
        return sb.reverse().toString();
    }

}

/*

public String removeDuplicates(String s, int k) {
        // LinkedList will be more efficient than Stack because Stack has to reallocate when size over capacity
        LinkedList<Adjacent> stack = new LinkedList<>();

        for (char x : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek().ch == x) {
                stack.peek().freq++;
            } else {
                stack.push(new Adjacent(x, 1));
            }
            if (stack.peek().freq == k) {
                stack.pop();
            }
        }

        // Convert linked list stack to string
        StringBuilder builder = new StringBuilder();
        while (stack.size() > 0) {
            Adjacent peek = stack.removeLast();
            for (int i = 0; i < peek.freq; i++) {
                builder.append(peek.ch);
            }
        }
        return builder.toString();
    }

    class Adjacent {
        char ch;
        int freq;

        public Adjacent(char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
        }
    }

 */