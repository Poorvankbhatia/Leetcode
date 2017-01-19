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

        int l = 0,r=height.length-1;
        int maxArea = (r-l)*Math.min(height[r],height[l]);

        while (l<r) {
            if(height[l]<height[r]) {
                l++;
            } else {
                r--;
            }

            maxArea = Math.max(maxArea,((r-l)*Math.min(height[r],height[l])));
        }

        return maxArea;

    }


    public static void main(String[] args) {
        int[] height = new int[]{4,17,13,12};
        System.out.println(new ContainerWithMostWater().maxArea(height));
    }

}
