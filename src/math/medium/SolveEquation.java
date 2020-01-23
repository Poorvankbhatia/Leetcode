/*
Solve a given equation and return the value of x in the form of string "x=#value". The equation contains only '+', '-' operation, the variable x and its coefficient.

If there is no solution for the equation, return "No solution".

If there are infinite solutions for the equation, return "Infinite solutions".

If there is exactly one solution for the equation, we ensure that the value of x is an integer.

Example 1:
Input: "x+5-3+x=6+x-2"
Output: "x=2"
Example 2:
Input: "x=x"
Output: "Infinite solutions"
Example 3:
Input: "2x=x"
Output: "x=0"
Example 4:
Input: "2x+3x-6x=x+2"
Output: "x=-1"
Example 5:
Input: "x=x+2"
Output: "No solution"
 */
package math.medium;

/**
 * Created by poorvank.b on 15/07/17.
 */
public class SolveEquation {

    public String solveEquation(String equation) {
        String[] parts = equation.split("=");
        int[] leftPart = evaluate(parts[0]);
        int[] rightPart = evaluate(parts[1]);
        if(leftPart[0] == rightPart[0] && leftPart[1] == rightPart[1]) {
            return "Infinite solutions";
        }else if (leftPart[0] == rightPart[0]){
            return "No solution";
        }
        return "x="+ (rightPart[1]-leftPart[1])/(leftPart[0]-rightPart[0]);
    }

    private int[] evaluate(String str){
        String[] tokens = str.split("(?=[+-])");  // ()for match group; ?= for match and include in res; [+-] means + or -;
        int[] res = new int[2]; // coefficient for x;  coefficient for constant
        for(String token : tokens) {
            if (token.equals("+x") || token.equals("x")) res[0]++; // x means 1x
            else if (token.equals("-x")) res[0]--;// -x means -1x
            else if (token.contains("x")) {
                res[0] += Integer.parseInt(token.substring(0, token.length()-1));
            }else {
                res[1] += Integer.parseInt(token);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String x = "x+5-2x=6-3x";
        System.out.println(new SolveEquation().solveEquation(x));
    }
}

/*

Example:
x +5 -2x = 6 -3x;
leftPart : tokens= { x, +5, -2x}; coefficient for x = 1-2 =-1; constant = 5;
rightPart: tokens= {6, -3x}; coefficient for x = -3; constant = 6;

final result = (6-5)/ (-1 - (-3))

 */
