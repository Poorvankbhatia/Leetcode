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
package heap.medium;

import java.util.PriorityQueue;

public class MinimumRoundingError {

    public String minimizeError(String[] prices, int target) {
        float res = 0;
        PriorityQueue<Double> diffHeap = new PriorityQueue<>();

        for (String s : prices) {
            float f = Float.valueOf(s);
            double low = Math.floor(f);
            double high = Math.ceil(f);

            if (low != high)
                diffHeap.offer((high-f)-(f-low));

            res += f-low;
            target -= low;
        }

        if (target < 0 || target > diffHeap.size())
            return "-1";

        while (target-- > 0)
            res += diffHeap.poll();

        return String.format("%.3f", res);
    }

}

/*

In order to meet a target by rounding, we can first floor every number and check out if sum is within range [target-length, target].
If so, we can switch the numbers from floored to ceiled one by one until we reach the target.

In order to minimize the rounding error, we can determine the optimal order to switch the elements. Ideally, the closer the value to ceiling,
the sooner it should be switched.

Note integer representation's floor and ceil are the same, so don't have a choice to switch.

DP SOl:

 public String minimizeError(String[] prices, int target) {
	 	DecimalFormat df = new DecimalFormat("#.000");
	 	HashMap<Integer, Double>[] memo = new HashMap[prices.length + 1];
	 	double ans = minimizeError(memo, 0, target, prices);
	 	if(ans == 0)
            return "0.000";
        return ans <= 1000000 ? df.format(ans) : "-1";
    }

    public double minimizeError(HashMap<Integer, Double>[] memo, int index, int target, String[] prices){
    	if(target < 0) {
        	return 2000000;
        }
    	if(memo[index] != null && memo[index].get(target) != null) {
        	return memo[index].get(target);
        }
    	if(index == prices.length){
    		if(target == 0) {
    			return 0;
    		}
            return 2000000;
        }
        double current = Double.parseDouble(prices[index]);
        int floor =  (int) Math.floor(current);
        int ceil = (int) Math.ceil(current);
        double currError = Math.min(current - floor + minimizeError(memo, index + 1, target - floor, prices),
        		                    ceil - current + minimizeError(memo, index + 1, target - ceil, prices));
        if(memo[index] == null) {
        	memo[index] = new HashMap<Integer, Double>();
        }
        memo[index].put(target, currError);
        return currError;
    }

 */