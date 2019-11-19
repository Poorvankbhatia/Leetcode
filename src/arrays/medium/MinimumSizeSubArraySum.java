/*

Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous
subarray of which the sum ≥ s. If there isn't one, return 0 instead.

For example, given the array [2,3,1,2,4,3] and s = 7,
the subarray [4,3] has the minimal length under the problem constraint.

 */

package arrays.medium;


/**
 * Created by poorvank on 20/01/17.
 */
public class MinimumSizeSubArraySum {

    public int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;
        int start=0;
        int sum=0;
        int len=Integer.MAX_VALUE;
        for(int end=0;end<n;end++) {
            sum+=nums[end];
            while(sum>=s) {
                len = Math.min(end-start+1,len);
                sum-=nums[start];
                start++;
            }
        }
        return len==Integer.MAX_VALUE?0:len;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2,5,1,2,1,3};
        int sum = 7;
        System.out.println(new MinimumSizeSubArraySum().minSubArrayLen(sum,arr));
    }

}


/*


As to NLogN solution, logN immediately reminds you of binary search. In this case, you cannot sort as the current order actually matters.
How does one get an ordered array then?
Since all elements are positive, the cumulative sum must be strictly increasing. Then, a subarray sum can expressed as the difference
between two cumulative sum. Hence, given a start index for the cumulative sum array, the other end index can be searched using binary search.

private int solveNLogN(int s, int[] nums) {
        int[] sums = new int[nums.length + 1];
        for (int i = 1; i < sums.length; i++) sums[i] = sums[i - 1] + nums[i - 1];
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < sums.length; i++) {
            int end = binarySearch(i + 1, sums.length - 1, sums[i] + s, sums);
            if (end == sums.length) break;
            if (end - i < minLen) minLen = end - i;
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    private int binarySearch(int lo, int hi, int key, int[] sums) {
        while (lo <= hi) {
           int mid = (lo + hi) / 2;
           if (sums[mid] >= key){
               hi = mid - 1;
           } else {
               lo = mid + 1;
           }
        }
        return lo;
    }

 */

