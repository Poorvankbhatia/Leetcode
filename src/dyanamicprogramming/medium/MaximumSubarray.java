package dyanamicprogramming.medium;

/**
 * Created by poorvank on 11/09/16.
 */
public class MaximumSubarray {

    public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int cumulativeSum = 0;
        for (int num : nums) {
            cumulativeSum = Math.max(cumulativeSum+num,num);
            maxSum = Math.max(maxSum,cumulativeSum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(new MaximumSubarray().maxSubArray(nums));
    }

}

/*

Divide And Conquer :

class Solution {
    public int maxSubArray(int[] nums) {
        return util(nums,0,nums.length-1);
    }

    private int util(int[] A,int start,int end) {
        if(start>end) {
            return Integer.MIN_VALUE;
        }
        int mid = (start)+(end-start)/2;
        int lMax = util(A,start,mid-1);
        int rMax = util(A,mid+1,end);
        int ml=0,mr=0;
        for(int i=mid-1,sum=0;i>=0;i--) {
            sum+=A[i];
            ml=Math.max(sum,ml);
        }
        for (int i=mid+1,sum=0;i<=end;i++) {
            sum+=A[i];
            mr=Math.max(sum, mr);
        }
        return Math.max(Math.max(lMax,rMax),ml+mr+A[mid]);
    }
}

 */