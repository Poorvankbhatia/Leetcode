/*

Given an integer array arr and an integer k, modify the array by repeating it k times.

For example, if arr = [1, 2] and k = 3 then the modified array will be [1, 2, 1, 2, 1, 2].

Return the maximum sub-array sum in the modified array. Note that the length of the sub-array can be 0 and its sum in that case is 0.

As the answer can be very large, return the answer modulo 10^9 + 7.



Example 1:

Input: arr = [1,2], k = 3
Output: 9
Example 2:

Input: arr = [1,-2,1], k = 5
Output: 2
Example 3:

Input: arr = [-1,-2], k = 7
Output: 0

 */
package dyanamicprogramming.medium;

public class KConcatenationSum {
    int mod  = (int) Math.pow(10,9)+7;
    public int kConcatenationMaxSum(int[] ar, int k) {
        long kadanes= kadanesAlgo(ar);
        if(k==1){
            return (int)kadanes;
        }
        long prefixSum= prefixSum(ar);
        long suffixSum=suffixSum(ar);
        long sum=0;
        for (int i1 : ar) {
            sum += i1;
        }
        if(sum>0){
            return (int)(Math.max(((sum*(k-2))%mod+suffixSum%mod+prefixSum%mod)%mod,kadanes%mod));
        }
        else{
            return (int)(Math.max((prefixSum%mod+suffixSum%mod)%mod,kadanes%mod));
        }

    }
    public  long kadanesAlgo(int[] ar){

        long currentSum=0;
        long maxSum=Integer.MIN_VALUE;
        for(int i=0;i<ar.length;i++){
            currentSum=currentSum>0?(currentSum+ar[i])%mod:ar[i];
            maxSum=Math.max(currentSum,maxSum);
        }
        return maxSum<0?0:maxSum%mod;

    }


    public  long prefixSum(int[] ar){

        long currentSum=0;
        long maxSum=Integer.MIN_VALUE;
        for(int i=0;i<ar.length;i++){
            currentSum= (currentSum+ar[i])%mod;
            maxSum=Math.max(maxSum,currentSum);
        }
        return maxSum;


    }

    public  long suffixSum(int[] ar){


        long currentSum=0;
        long maxSum=Integer.MIN_VALUE;
        for(int i=ar.length-1;i>=0;i--){
            currentSum=(currentSum+ar[i])%mod;
            maxSum=Math.max(currentSum,maxSum);
        }
        return maxSum;

    }
}
/*

Explanation:
The problem is to find the maximum sub array of concatenatedarr.
Maximum SubArray of an array A is a continuous SubArray within the array A that has the largest Sum.
The best method for finding Maximum SubArray is Kadanae's algorithm.

Here you have to find the Maximum SubArray for an array concatenated_arr which is a k-times repetitive array of A.
For e.g.. if A is {3, 2, -1} and K is 3 then B will be {3, 2, -1, 3, 2, -1, 3, 2, -1}. Method:
The maximum SubArray of concatenated_arr can be the sum of all its elements.
For e.g.. if A is {3, 2, -1} and K is 3, then B will be {3, 2, -1, 3, 2, -1, 3, 2, -1}.
The sum of all the elements in concatenated_arr will give us 12. To find this one we don't need to create the array concatenated_arr.
We can simply find the sum of all the elements in array A and we can mutilply it with K.
But wait, we can omit the last term in it so that the sum will become 13.
For this one we can use prefix and suffix calculations.
Eg:
A is repeated k times in concatenatedarr.
Consider the first repetition of A is A1, second is A2 and so on. So now our B array(if K=8) will be {A1, A2, A3, A4, A5, A6, A7, A8}.
If you omit the first two elements in A1 and the last two elements in A8, you might also get the maxsub array.
So here we can check whether it is possible to omit some initial elements in A1 and some Final elements in A8. We use prefix and suffix
variables for that to calculate the sum of A1 and A10 specifically and he adds the remaining elements i.e answer =
{prefix + sum_of_elements(A2) + sum_of_elements(A3) + sum_of_elements(A4) + sum_of_elements(A5) + sum_of_elements(A6) + sum_of_elements(A7) + suffix} ,
which in simplified form becomes {prefix + sum_of_elements(A)*(k-2) + suffix}.

 */
