/*

Given a list of daily temperatures, produce a list that, for each day in the input, tells you how many days you would have to wait
until a warmer temperature. If there is no future day for which this is possible, put 0 instead.

For example, given the list temperatures = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].

Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].

 */
package stack.medium;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by poorvank.b on 03/12/17.
 */
public class DailyTemperatures {

    public int[] dailyTemperatures(int[] T) {
        if(T==null || T.length==0) {
            return new int[0];
        }
        int n = T.length;
        int[] ans = new int[n];
        ans[n-1]=0;
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<n;i++) {
            while(!stack.isEmpty() && T[i]>T[stack.peek()]) {
                int val = stack.pop();
                ans[val]=i-val;
            }
            stack.push(i);
        }
        while(!stack.isEmpty()) {
            T[stack.pop()]=0;
        }
        return ans;

    }
    public static void main(String[] args) {
        int[] temp = new int[]{89,62,70,58,47,47,46,76,100,70};
        System.out.println(Arrays.toString(new DailyTemperatures().dailyTemperatures(temp)));
    }

}
