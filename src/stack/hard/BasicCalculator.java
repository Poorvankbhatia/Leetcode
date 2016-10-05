/*

Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

You may assume that the given expression is always valid.

Some examples:
"1 + 1" = 2
" 2-1 + 2 " = 3
"(1+(4+5+2)-3)+(6+8)" = 23

 */
package stack.hard;

import java.util.Stack;

/**
 * Created by poorvank on 05/10/16.
 */
public class BasicCalculator {

    public int calculate(String s) {

        char[] arr = s.toCharArray();
        Stack<Integer> valStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();

        for (int i=0;i<arr.length;i++) {

            if(arr[i]==' ') {
                continue;
            }

            if(arr[i]=='(') {
                operatorStack.push(arr[i]);
            } else if(Character.isDigit(arr[i])) {
                StringBuilder sb = new StringBuilder();
                while (i<arr.length && Character.isDigit(arr[i])) {
                    sb.append(arr[i]);
                    i++;
                }
                i--;
                valStack.push(Integer.parseInt(sb.toString()));
            } else if(arr[i]==')') {
                while (operatorStack.peek()!='(') {
                    int b = valStack.pop();
                    int a = valStack.pop();
                    valStack.push(applyOperator(operatorStack.pop(), a, b));
                }
                operatorStack.pop();
            } else if(arr[i]=='+' || arr[i]=='-' || arr[i]=='/' || arr[i]=='*') {
                while (!operatorStack.isEmpty() && hasPrecedence(arr[i],operatorStack.peek())) {
                    int b = valStack.pop();
                    int a = valStack.pop();
                    valStack.push(applyOperator(operatorStack.pop(), a, b));
                }

                operatorStack.push(arr[i]);
            }

        }

        while (!operatorStack.empty()){
            int b = valStack.pop();
            int a = valStack.pop();
            valStack.push(applyOperator(operatorStack.pop(), a, b));
        }

        // Top of 'values' contains result, return it
        return valStack.pop();
    }

    private boolean hasPrecedence(char op1, char op2) {
        return !(op2 == '(' || op2 == ')') && !((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'));
    }


    private int applyOperator(char op,int a,int b) {

        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0)
                    throw new
                            UnsupportedOperationException("Cannot divide by zero");
                return a / b;
        }
        return 0;

    }

    public static void main(String[] args) {

        System.out.println(new BasicCalculator().calculate("(1+(4+5+2)-3)+(6+8)"));

    }

}


/*

1. While there are still tokens to be read in,
   1.1 Get the next token.
   1.2 If the token is:
       1.2.1 A number: push it onto the value stack.
       1.2.2 A variable: get its value, and push onto the value stack.
       1.2.3 A left parenthesis: push it onto the operator stack.
       1.2.4 A right parenthesis:
         1 While the thing on top of the operator stack is not a
           left parenthesis,
             1 Pop the operator from the operator stack.
             2 Pop the value stack twice, getting two operands.
             3 Apply the operator to the operands, in the correct order.
             4 Push the result onto the value stack.
         2 Pop the left parenthesis from the operator stack, and discard it.
       1.2.5 An operator (call it thisOp):
         1 While the operator stack is not empty, and the top thing on the
           operator stack has the same or greater precedence as thisOp,
           1 Pop the operator from the operator stack.
           2 Pop the value stack twice, getting two operands.
           3 Apply the operator to the operands, in the correct order.
           4 Push the result onto the value stack.
         2 Push thisOp onto the operator stack.
2. While the operator stack is not empty,
    1 Pop the operator from the operator stack.
    2 Pop the value stack twice, getting two operands.
    3 Apply the operator to the operands, in the correct order.
    4 Push the result onto the value stack.
3. At this point the operator stack should be empty, and the value
   stack should have only one value in it, which is the final result.

 */