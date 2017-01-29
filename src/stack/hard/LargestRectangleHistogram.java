/*

Given n non-negative integers representing the histogram's
bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.

 */
package stack.hard;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by poorvank on 29/01/17.
 */
public class LargestRectangleHistogram {

    public int largestRectangleArea(int[] heights) {

        if(heights.length==0) {
            return 0;
        }

        Stack<Integer> leftStack = new Stack<>();
        Stack<Integer> rightStack = new Stack<>();
        int n = heights.length;

        int[] width = new int[n];
        Arrays.fill(width,1);

        for (int i=0;i<n;i++) {

            while (!leftStack.isEmpty() && heights[i]<=heights[leftStack.peek()]) {
                leftStack.pop();
            }

            if(leftStack.isEmpty()) {
                width[i] += i;
            } else {
                width[i] += i-leftStack.peek()-1;
            }
            leftStack.push(i);
        }


        for (int i=n-1;i>=0;i--) {

            while (!rightStack.isEmpty() && heights[i]<=heights[rightStack.peek()]) {
                rightStack.pop();
            }

            if(rightStack.isEmpty()) {
                width[i] += n-i-1;
            } else {
                width[i] +=rightStack.peek()-i-1 ;
            }

            rightStack.push(i);

        }

        int result = 0;


        for (int i=0;i<n;i++) {
            result = Math.max(result,(width[i]*heights[i]));
        }

        return result;

    }

    public static void main(String[] args) {

        int[] heights = new int[]{2,1,5,6,2,3};
        System.out.println(new LargestRectangleHistogram().largestRectangleArea(heights));

    }

}
