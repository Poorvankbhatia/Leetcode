/*

Given an integer array, find three numbers whose product is maximum and output the maximum product.

Example 1:
Input: [1,2,3]
Output: 6
Example 2:
Input: [1,2,3,4]
Output: 24
Note:
The length of the given array will be in range [3,104] and all elements are in the range [-1000, 1000].
Multiplication of any three numbers in the input won't exceed the range of 32-bit signed integer.

 */
package arrays.easy;

/**
 * Created by poorvank on 25/06/17.
 */
public class MaxProductOfThreeNos {

    public int maximumProduct(int[] nums) {

        int[] leftMax = new int[nums.length];
        int[] rightMax = new int[nums.length];
        int[] leftMin = new int[nums.length];
        int[] rightMin = new int[nums.length];

        leftMax[0]=-1;
        leftMin[0]=-1;
        rightMax[nums.length-1]=-1;
        rightMin[nums.length-1]=-1;

        int maxProduct = Integer.MIN_VALUE;

        int maxSum = nums[0];

        int minSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            leftMax[i] = maxSum;
            if (nums[i] > maxSum)
                maxSum = nums[i];

            leftMin[i] = minSum;
            if (nums[i] < minSum)
                minSum = nums[i];
        }

        maxSum = nums[nums.length - 1];


        minSum = nums[nums.length - 1];

        for (int j = nums.length - 2; j >= 0; j--) {
            rightMax[j] = maxSum;
            if (nums[j] > maxSum)
                maxSum = nums[j];

            rightMin[j] = minSum;
            if (nums[j] < minSum)
                minSum = nums[j];
        }


        for (int i = 1; i < nums.length - 1; i++) {
            int max1 = Math.max(nums[i] * leftMax[i] * rightMax[i],
                    nums[i] * leftMin[i] * rightMin[i]);

            int max2 = Math.max(nums[i] * leftMax[i] * rightMin[i],
                    nums[i] * leftMin[i] * rightMax[i]);

            maxProduct = Math.max(maxProduct, Math.max(max1, max2));
        }

        return maxProduct;
    }


}

/*

Construct four auxiliary arrays leftMax[], rightMax[], leftMin[] and rightMin[] of same size as input array.

Fill leftMax[], rightMax[], leftMin[] and rightMin[] in below manner.

leftMax[i] will contain maximum element on left of arr[i] excluding arr[i]. For index 0, left will contain -1.

leftMin[i] will contain minimum element on left of arr[i] excluding arr[i]. For index 0, left will contain -1.

rightMax[i] will contain maximum element on right of arr[i] excluding arr[i]. For index n-1, right will contain -1.

rightMin[i] will contain minimum element on right of arr[i] excluding arr[i]. For index n-1, right will contain -1.

For all array indexes i except first and last index, compute maximum of arr[i]*x*y where x can be leftMax[i] or leftMin[i] and y
can be rightMax[i] or rightMin[i].

Return the maximum from step 3.

 */