/*

Given a binary array, find the maximum number of consecutive 1s in this array if you can flip at most one 0.
Example 1:
Input: [1,0,1,1,0]
Output: 4
Explanation: Flip the first zero will get the the maximum number of consecutive 1s.
    After flipping, the maximum number of consecutive 1s is 4.
Note:
The input array will only contain 0 and 1.
The length of input array is a positive integer and will not exceed 10,000


Follow up:
What if the input numbers come in one by one as an infinite stream?
In other words, you can't store all numbers coming from the stream as it's too large to hold in memory. Could you solve it efficiently?

 */
package arrays.medium;

/**
 * Created by poorvank.b on 06/02/18.
 */
public class MaxConsecutiveOnes2 {

    public int findMaxConsecutiveOnes(int[] nums) {

        if(nums==null || nums.length==0) {
            return 0;
        }

        int k=1;
        int zero=0;
        int result = Integer.MIN_VALUE;

        for (int start=0,end=0;end<nums.length;end++) {

            if(nums[end]==0) {
                zero++;
            }

            while (zero>k) {
                if(nums[start]==0) {
                    zero--;
                }
                start++;
            }

            result = Math.max(result,end-start+1);

        }

        return result;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{1,1,0,1,1,1,0,0,0,0,1,0,1,1,1,1,1,1,1,1,1};
        System.out.println(new MaxConsecutiveOnes2().findMaxConsecutiveOnes(arr));
    }

}

/*

Now let's deal with follow-up, we need to store up to k indexes of zero within the window [l, h]
so that we know where to move lnext when the window contains more than k zero.
If the input stream is infinite, then the output could be extremely large because there could be super long consecutive ones.
In that case we can use BigInteger for all indexes. For simplicity, here we will use int
Time: O(n) Space: O(k)
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0, k = 1; // flip at most k zero
        Queue<Integer> zeroIndex = new LinkedList<>();
        for (int l = 0, h = 0; h < nums.length; h++) {
            if (nums[h] == 0)
                zeroIndex.offer(h);
            if (zeroIndex.size() > k)
                l = zeroIndex.poll() + 1;
            max = Math.max(max, h - l + 1);
        }
        return max;
    }

 */