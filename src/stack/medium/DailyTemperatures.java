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

    public int[] dailyTemperatures(int[] temperatures) {

        if(temperatures==null || temperatures.length==0) {
            return new int[0];
        }

        Stack<Integer> stack = new Stack<>();

        int n = temperatures.length;
        int[] result = new int[n];

        stack.push(0);

        for (int i=1;i<n;i++) {

            int currentIndex = stack.pop();

            while (temperatures[currentIndex]<temperatures[i]) {
                result[currentIndex]=i-currentIndex;
                if(stack.isEmpty()) {
                    break;
                }
                currentIndex = stack.pop();
            }

            if(temperatures[currentIndex]>=temperatures[i]) {
                stack.push(currentIndex);
            }

            stack.push(i);

        }

        while (!stack.isEmpty()) {
            int element = stack.pop();
            result[element]=0;
        }

        return result;

    }

    public static void main(String[] args) {
        int[] temp = new int[]{89,62,70,58,47,47,46,76,100,70};
        System.out.println(Arrays.toString(new DailyTemperatures().dailyTemperatures(temp)));
    }

}
