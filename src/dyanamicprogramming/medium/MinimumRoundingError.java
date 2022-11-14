/*

Given an array of prices [p1,p2...,pn] and a target, round each price pi to Roundi(pi) so that the rounded array [Round1(p1),Round2(p2)...,Roundn(pn)]
sums to the given target. Each operation Roundi(pi) could be either Floor(pi) or Ceil(pi).

Return the string "-1" if the rounded array is impossible to sum to target. Otherwise, return the smallest rounding error, which is defined as Σ |Roundi(pi) - (pi)|
for i from 1 to n, as a string with three places after the decimal.



Example 1:

Input: prices = ["0.700","2.800","4.900"], target = 8
Output: "1.000"
Explanation:
Use Floor, Ceil and Ceil operations to get (0.7 - 0) + (3 - 2.8) + (5 - 4.9) = 0.7 + 0.2 + 0.1 = 1.0 .
Example 2:

Input: prices = ["1.500","2.500","3.500"], target = 10
Output: "-1"
Explanation:
It is impossible to meet the target.


Note:

1 <= prices.length <= 500.
Each string of prices prices[i] represents a real number which is between 0 and 1000 and has exactly 3 decimal places.
target is between 0 and 1000000.

 */
package dyanamicprogramming.medium;

import java.util.HashMap;
import java.util.Map;

public class MinimumRoundingError {

    public String minimizeError(String[] prices, int target) {
        double[] p = new double[prices.length];
        for (int i = 0; i < prices.length; i++) {
            p[i] = Double.parseDouble(prices[i]);
        }
        double res = dfs(p, target, 0, 0, new HashMap<>());
        if (res == -1) return String.format("%.0f", res);
        return String.format("%.3f", res);
    }

    public double dfs(double[] prices, int target, int index, int currentSum, Map<String, Double> map) {
        if (index == prices.length) {
            if (currentSum == target) {
                return 0;
            } else {
                return -1;
            }
        }

        String key = currentSum + "-" + index;

        if (map.containsKey(key)) {
            return map.get(key);
        }

        double result = Double.MAX_VALUE;
        //round down
        double roundDownError = dfs(prices, target, index + 1, currentSum + (int)Math.floor(prices[index]), map);
        if (roundDownError != -1) {
            roundDownError +=  prices[index] - Math.floor(prices[index]);
            result = Math.min(result, roundDownError);
        }
        //round up
        double roundUpError = dfs(prices, target, index + 1, currentSum + (int)Math.ceil(prices[index]), map);
        if (roundUpError != -1) {
            roundUpError +=  Math.ceil(prices[index]) - prices[index];
            result = Math.min(result, roundUpError);
        }

        result = result == Double.MAX_VALUE? -1: result;

        map.put(key, result);
        return  result;
    }

}

/*

DFS backtracking with memorization

 */