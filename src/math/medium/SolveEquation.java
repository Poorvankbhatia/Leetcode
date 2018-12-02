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
