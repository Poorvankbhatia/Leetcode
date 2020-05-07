/*

Given n non-negative integers representing an elevation map where the width of each bar is 1,
compute how much water it is able to trap after raining.

For example,
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.

 */

package arrays.hard;

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
/*

Better Sol:

public int trap(int[] A){
    int a=0;
    int b=A.length-1;
    int max=0;
    int leftmax=0;
    int rightmax=0;
    while(a<=b){
        leftmax=Math.max(leftmax,A[a]);
        rightmax=Math.max(rightmax,A[b]);
        if(leftmax<rightmax){
            max+=(leftmax-A[a]);       // leftmax is smaller than rightmax, so the (leftmax-A[a]) water can be stored
            a++;
        }
        else{
            max+=(rightmax-A[b]);
            b--;
        }
    }
    return max;
}


The idea is: Calculate the stored water at each index a and b in code. At the start of every loop,
update the current maximum height from left side (that is from A[0,1...a]) and the maximum height from right side(from A[b,b+1...n-1]).
if(leftmax<rightmax) then, at least (leftmax-A[a]) water can definitely be stored no matter what happens between [a,b]
since we know there is a barrier at the rightside(rightmax>leftmax). On the other side, we cannot store more water than (leftmax-A[a])
at index a since the barrier at left is of height leftmax. So, we know the water that can be stored at index a is exactly (leftmax-A[a]).
The same logic applies to the case when (leftmax>rightmax). At each loop we can make a and b one step closer. Thus we can finish it in linear time.

 */
