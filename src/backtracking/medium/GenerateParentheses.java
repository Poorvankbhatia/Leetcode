/*

Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]

 */

package backtracking.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 27/08/16.
 */
public class GenerateParentheses {

    public List<String> generateParenthesis(int n) {

        List<String> list = new ArrayList<>();
        generateParenthesisUtil(n,0,list,new StringBuilder());
        return list;
    }

    public void generateParenthesisUtil(int openBracket,int closeBracket,List<String> result,StringBuilder sb) {

        if(openBracket==0 && closeBracket==0) {
            result.add(sb.toString());
            return;
        }
        if(openBracket>0) {
            sb.append("(");
            generateParenthesisUtil(openBracket-1,closeBracket+1,result,sb);
            sb.deleteCharAt(sb.length()-1);
        }
        if(closeBracket>0) {
            sb.append(")");
            generateParenthesisUtil(openBracket,closeBracket-1,result,sb);
            sb.deleteCharAt(sb.length()-1);
        }

    }

    public static void main(String[] args) {

        GenerateParentheses gp = new GenerateParentheses();
        System.out.println(gp.generateParenthesis(3));

    }

}
