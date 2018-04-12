/*

Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Examples:
"()())()" -> ["()()()", "(())()"]
"(a)())()" -> ["(a)()()", "(a())()"]
")(" -> [""]

 */
package bfsdfs.hard;

import java.util.*;

/**
 * Created by poorvank.b on 08/02/18.
 */
public class RemoveInvalidParentheses {

    public List<String> removeInvalidParentheses(String s) {

        List<String> result = new ArrayList<>();

        if(s==null) {
            return result;
        }

        Set<String> visited = new HashSet<>();


        Queue<String> queue = new LinkedList<>();
        boolean reachedFirstLevel = false;

        queue.add(s);
        visited.add(s);

        while (!queue.isEmpty()) {
            String current = queue.poll();

            if(isValidString(current)) {
                result.add(current);
                reachedFirstLevel = true;
            }

            if(reachedFirstLevel) {
                continue;
            }

            for (int i=0;i<current.length();i++) {


                if(!isParenthesis(current.charAt(i))) {
                    continue;
                }

                String next = current.substring(0,i)+current.substring(i+1);
                if(!visited.contains(next)) {
                    queue.add(next);
                    visited.add(next);
                }

            }

        }

        return result;

    }

    private boolean isParenthesis(char c) {
        return c==')' || c=='(';
    }

    private boolean isValidString(String s) {

        Stack<Character> stack = new Stack<>();

        //System.out.println(s);

        for (char c : s.toCharArray()) {
            if(c=='(') {
                stack.push(c);
            } else if(c==')') {
                if(stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }

        return stack.isEmpty();

    }

    public static void main(String[] args) {
        String s = "()())()";
        System.out.println(new RemoveInvalidParentheses().removeInvalidParentheses(s));
    }

}
/*

As we need to generate all possible output we will backtrack among all states by removing one opening or closing bracket and check if
they are valid if invalid then add the removed bracket back and go for next state. We will use BFS for moving through states,
use of BFS will assure removal of minimal number of brackets because we traverse into states level by level and each level corresponds
to one extra bracket removal. Other than this BFS involve no recursion so overhead of passing parameters is also saved.

 */