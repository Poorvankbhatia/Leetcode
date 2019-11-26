/*

Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target.
Return the sum of the three integers. You may assume that each input would have exactly one solution.

    For example, given array S = {-1 2 1 -4}, and target = 1.

    The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

 */
package arrays.medium;

import java.util.Arrays;

/**
 * Created by poorvank on 20/01/17.
 */
public class ThreeSumClosest {

    public int threeSumClosest(int[] A, int target) {
        Arrays.sort(A);
        int diff=Integer.MAX_VALUE;
        int ans=0;
        int n= A.length;
        for(int i=0;i<n;i++) {
            int j=i+1;
            int k=n-1;
            while(j<k) {
                int sum = A[i]+A[j]+A[k];
                if(sum==target) {
                    return target;
                } else if(sum>target) {
                    k--;
                } else {
                    j++;
                }
                if(diff>Math.abs(sum-target)) {
                    diff = Math.abs(sum-target);
                    ans =sum;
                }
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{-1,2,1,-4};
        int  target = 1;
        System.out.println(new ThreeSumClosest().threeSumClosest(arr,target));
    }
}
