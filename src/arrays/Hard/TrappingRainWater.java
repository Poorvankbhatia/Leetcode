/*

Given n non-negative integers representing an elevation map where the width of each bar is 1,
compute how much water it is able to trap after raining.

For example,
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.

 */

package arrays.Hard;

/**
 * Created by poorvank on 26/11/16.
 */
public class TrappingRainWater {

    public int trap(int[] height) {

        int n = height.length;
        int[] waterFromLeft = new int[n];
        int[] waterFromRight = new int[n];

        waterFromLeft[0] = height[0];
        for (int i=1;i<n;i++) {
            waterFromLeft[i] = Math.max(waterFromLeft[i-1],height[i]);
        }

        waterFromRight[n-1] = height[n-1];
        for (int i=n-2;i>=0;i--) {
            waterFromRight[i] = Math.max(waterFromRight[i+1],height[i]);
        }

        int maxWater = 0;

        for (int i=0;i<n;i++) {
            maxWater += Math.min(waterFromLeft[i],waterFromRight[i])-height[i];
        }

        return maxWater;

    }


}
