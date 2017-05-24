/*

Given a string of numbers and operators, return all possible results from computing all the different possible ways
to group numbers and operators. The valid operators are +, - and *.


Example 1
Input: "2-1-1".

((2-1)-1) = 0
(2-(1-1)) = 2
Output: [0, 2]


Example 2
Input: "2*3-4*5"

(2*(3-(4*5))) = -34
((2*3)-(4*5)) = -14
((2*(3-4))*5) = -10
(2*((3-4)*5)) = -10
(((2*3)-4)*5) = 10
Output: [-34, -14, -10, -10, 10]

 */

package dyanamicprogramming.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by poorvank on 23/05/17.
 */
public class DifferentWaysToAddParentheses {

    private Map<String,List<Integer>> cacheMap = new HashMap<>();

    public List<Integer> diffWaysToCompute(String input) {

        if(cacheMap.containsKey(input)) {
            return cacheMap.get(input);
        }

        List<Integer> list = new ArrayList<>();

        for (int i=0;i<input.length();i++) {
            if(isChar(input.charAt(i))) {
                List<Integer> left = diffWaysToCompute(input.substring(0,i));
                List<Integer> right = diffWaysToCompute(input.substring(i+1));

                for (Integer l : left) {
                    for (Integer r : right) {
                        if(input.charAt(i)=='-') {
                            list.add(l-r);
                        } else if(input.charAt(i)=='+') {
                            list.add(l+r);
                        } else {
                            list.add(l*r);
                        }
                    }
                }
                cacheMap.put(input,list);
            }
        }

        if(list.size()==0) {
            list.add(Integer.parseInt(input));
        }

        return list;

    }

    private boolean isChar(char c) {
        return (c=='-' || c=='+' || c=='*');
    }

    public static void main(String[] args) {
        String input = "2-1-1";
        System.out.println(new DifferentWaysToAddParentheses().diffWaysToCompute(input));
    }

}
