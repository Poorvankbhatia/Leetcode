/*

Return the result of evaluating a given boolean expression, represented as a string.

An expression can either be:

"t", evaluating to True;
"f", evaluating to False;
"!(expr)", evaluating to the logical NOT of the inner expression expr;
"&(expr1,expr2,...)", evaluating to the logical AND of 2 or more inner expressions expr1, expr2, ...;
"|(expr1,expr2,...)", evaluating to the logical OR of 2 or more inner expressions expr1, expr2, ...


Example 1:

Input: expression = "!(f)"
Output: true
Example 2:

Input: expression = "|(f,t)"
Output: true
Example 3:

Input: expression = "&(t,f)"
Output: false
Example 4:

Input: expression = "|(&(t,f,t),!(t))"
Output: false


Constraints:

1 <= expression.length <= 20000
expression[i] consists of characters in {'(', ')', '&', '|', '!', 't', 'f', ','}.
expression is a valid expression representing a boolean, as given in the description.

 */
package stack.hard;

import java.util.Stack;

public class ParseBooleanExpression {

    public boolean parseBoolExpr(String expression) {

        Stack<Character> operator = new Stack<>();

        for (int i=expression.length()-1;i>=0;i--) {
            char c = expression.charAt(i);
            if(c==')' || c=='f' || c=='t') {
                operator.push(c);
            }else if(c=='(') {
                StringBuilder sb = new StringBuilder();
                if(i!=0) {
                    char op = expression.charAt(i-1);
                    while (!operator.empty() && operator.peek()!=')') {
                        char a = operator.pop();
                        if(Character.isAlphabetic(a)) {
                            sb.append(a);
                        }
                    }
                    operator.pop();
                    operator.push(performOperation(sb.toString(), op));
                }
            }
        }

        return operator.pop() == 't';
    }

    private char performOperation(String s,char op) {
        if(op=='!') {
            return s.equals("t")?'f':'t';
        } else if(op=='|') {
            boolean flag = s.charAt(0) != 'f';
            for (int i=1;i<s.length();i++) {
                flag = flag | (s.charAt(i) != 'f');
            }
            return !flag ?'f':'t';
        } else {
            boolean flag = s.charAt(0) != 'f';
            for (int i=1;i<s.length();i++) {
                flag = flag & (s.charAt(i) != 'f');
            }
            return !flag ?'f':'t';
        }
    }

    public static void main(String[] args) {
        String expression = "|(&(t,f,t),!(t))";
        System.out.println(new ParseBooleanExpression().parseBoolExpr(expression));
    }

}

/*

Another Sol:

public boolean parseBoolExpr(String expression) {
        Deque<Character> stk = new ArrayDeque<>();
        for (int i = 0; i < expression.length(); ++i) {
            char c = expression.charAt(i);
            if (c == ')') {
                Set<Character> seen = new HashSet<>();
                while (stk.peek() != '(')
                    seen.add(stk.pop());
                stk.pop();// pop out '('.
                char operator = stk.pop(); // get operator for current expression.
                if (operator == '&') {
                    stk.push(seen.contains('f') ? 'f' : 't'); // if there is any 'f', & expression results to 'f'
                }else if (operator == '|') {
                    stk.push(seen.contains('t') ? 't' : 'f'); // if there is any 't', | expression results to 't'
                }else { // ! expression.
                    stk.push(seen.contains('t') ? 'f' : 't'); // Logical NOT flips the expression.
                }
            }else if (c != ',') {
                stk.push(c);
            }
        }
        return stk.pop() == 't';
    }

 */
