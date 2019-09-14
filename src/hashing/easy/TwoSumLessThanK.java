/*

Given an array A of integers and integer K, return the maximum S such that there exists i < j with A[i] + A[j] = S and S < K.
If no i, j exist satisfying this equation, return -1.



Example 1:

Input: A = [34,23,1,24,75,33,54,8], K = 60
Output: 58
Explanation:
We can use 34 and 24 to sum 58 which is less than 60.
Example 2:

Input: A = [10,20,30], K = 15
Output: -1
Explanation:
In this case it's not possible to get a pair sum less that 15.


Note:

1 <= A.length <= 100
1 <= A[i] <= 1000
1 <= K <= 2000


 */
package hashing.easy;

import java.util.TreeMap;

public class TwoSumLessThanK {

    public int twoSumLessThanK(int[] A, int K) {
        TreeMap<Integer,Integer> map = new TreeMap<>();
        for (int i=0;i<A.length;i++) {
            map.put(A[i], i);
        }
        int ans = Integer.MIN_VALUE;
        for(int i=0;i<A.length;i++) {
            Integer small = (map.floorKey(K-A[i]));
            if(small!=null && map.get(small)!=i && A[i]+small!=K) {
                ans = Math.max(ans,A[i]+small);
            }
        }
        return ans;
    }

}

/*

Another sol:

Sort the input A;
Push from the two ends and attempt to find any addition of the two elements < K; if the addition >= K,
then decrease the high bound and hence tentatively get a smaller addition; otherwise, increase low bound to find a bigger addition;
repeat 2 till the end.

 public int twoSumLessThanK(int[] A, int K) {
        Arrays.sort(A); // Time cost O(nlogn).
        int max = -1, i = 0, j = A.length - 1;
        while (i < j) {
            int sum = A[i] + A[j];
            if (sum < K) { // find one candidate.
                max = Math.max(max, sum);
                ++i; // increase the smaller element.
            }else { // >= sum.
                --j; // decrease the bigger element.
            }
        }
        return max;
    }

Time: O(nlogn), space: O(1), where n = A.length.

 */