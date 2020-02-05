/*

Given an array of integers nums and an integer k. A subarray is called nice if there are k odd numbers on it.

Return the number of nice sub-arrays.



Example 1:

Input: nums = [1,1,2,1,1], k = 3
Output: 2
Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].
Example 2:

Input: nums = [2,4,6], k = 1
Output: 0
Explanation: There is no odd numbers in the array.
Example 3:

Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
Output: 16


Constraints:

1 <= nums.length <= 50000
1 <= nums[i] <= 10^5
1 <= k <= nums.length

 */
package arrays.medium;

public class NiceSubArrays {

    public int numberOfSubarrays(int[] nums, int k) {
        return atMost(nums,k)-atMost(nums,k-1);
    }

    private int atMost(int[] nums,int k) {
        int oddCount=0;
        int count=0;
        int n = nums.length;
        int end=0;
        int start=0;
        while(end<n) {
            if(nums[end]%2!=0) {
                oddCount++;
            }
            end++;
            while(start<end && oddCount>k) {
                if(nums[start]%2!=0) {
                    oddCount--;
                }
                start++;
            }
            count+=end-start;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new NiceSubArrays().numberOfSubarrays(new int[]{3, 2, 3, 2},1));
    }

}
/*

Also check SubArraysWithKDistinctIntegers.

Another solution:
public int numberOfSubarrays(int[] nums, int k) {
        List<Integer> oddIndexList = new ArrayList<>();
        oddIndexList.add(-1);
        for(int i = 0;i<nums.length;i++){
            if(nums[i] % 2 == 1){
                oddIndexList.add(i);
            }
        }
        oddIndexList.add(nums.length);
        //E.g. {-1,3,6,10}  in example nums = [2,2,2,1,2,2,1,2,2,2], k = 2
        int ans = 0;
        for(int i = 1;i+k<oddIndexList.size();i++){
            ans += (oddIndexList.get(i) - oddIndexList.get(i-1))*
                (oddIndexList.get(i+k) - oddIndexList.get(i-1+k));
        }
        return ans;
    }

 */
