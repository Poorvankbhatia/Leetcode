/*

Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai).
n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines,
which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container.

 */

package arrays.medium;

/**
 * Created by poorvank on 26/11/16.
 */
public class ContainerWithMostWater {

    public int maxArea(int[] height) {
        int a= 0;
        int b =height.length-1;
        int leftMax=0;
        int rightMax = 0;
        int ans=0;
        while(a<=b) {
            leftMax = Math.max(leftMax,height[a]);
            rightMax = Math.max(rightMax,height[b]);
            ans = Math.max(Math.min(leftMax,rightMax)*(b-a),ans);
            if(leftMax<rightMax) {
                a++;
            } else {
                b--;
            }

        }
        return ans;
    }


    public static void main(String[] args) {
        int[] height = new int[]{4,17,13,12};
        System.out.println(new ContainerWithMostWater().maxArea(height));
    }

}

/*

The intuition behind this approach is that the area formed between the lines will always be limited by the height of the shorter line.
Further, the farther the lines, the more will be the area obtained.

We take two pointers, one at the beginning and one at the end of the array constituting the length of the lines. Futher, we maintain a variable
maxarea to store the maximum area obtained till now. At every step, we find out the area formed between them, update maxarea
and move the pointer pointing to the shorter line towards the other end by one step.

How this approach works?

Initially we consider the area constituting the exterior most lines. Now, to maximize the area, we need to consider the area between the lines
of larger lengths.
If we try to move the pointer at the longer line inwards, we won't gain any increase in area, since it is limited by the shorter line.
But moving the shorter line's pointer could turn out to be beneficial, as per the same argument, despite the reduction in the width.
This is done since a relatively longer line obtained by moving the shorter line's pointer might overcome the reduction in area caused by
the width reduction.

We starts with the widest container, l = 0 and r = n - 1. Let's say the left one is shorter: h[l] < h[r].
Then, this is already the largest container the left one can possibly form. There's no need to consider it again.
Therefore, we just throw it away and start again with l = 1 and r = n -1.





*/