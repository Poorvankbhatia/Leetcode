/*

Given an integer array A, and an integer target, return the number of tuples i, j, k  such that i < j < k and A[i] + A[j] + A[k] == target.

As the answer can be very large, return it modulo 10^9 + 7.



Example 1:

Input: A = [1,1,2,2,3,3,4,4,5,5], target = 8
Output: 20
Explanation:
Enumerating by the values (A[i], A[j], A[k]):
(1, 2, 5) occurs 8 times;
(1, 3, 4) occurs 8 times;
(2, 2, 4) occurs 2 times;
(2, 3, 3) occurs 2 times.
Example 2:

Input: A = [1,1,2,2,2,2], target = 5
Output: 12
Explanation:
A[i] = 1, A[j] = A[k] = 2 occurs 12 times:
We choose one 1 from [1,1] in 2 ways,
and two 2s from [2,2,2,2] in 6 ways.


Note:

3 <= A.length <= 3000
0 <= A[i] <= 100
0 <= target <= 300


 */
package arrays.medium;

import java.util.HashMap;
import java.util.Map;

public class ThreeSumMultiplicity {

    private int mod = (int)Math.pow(10,9)+7;

    public int threeSumMulti(int[] A, int target) {
        int n = A.length;
        int count = 0;
        for(int i=0;i<n;i++) {
            int val = target-A[i];
            Map<Integer,Integer> map = new HashMap<>();
            for(int k=i+1;k<n;k++) {
                map.put(A[k],map.getOrDefault(A[k],0)+1);
            }
            for (int j=i+1;j<n;j++) {
                map.put(A[j],map.get(A[j])-1);
                int x =(map.getOrDefault(val-A[j],0));
                count = ((count)+(x%mod))%mod;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new ThreeSumMultiplicity().threeSumMulti(new int[]{1,1,2,2,3,3,4,4,5,5},8));
    }

}

/*

Another sol:

public int threeSumMulti(int[] A, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        int res = 0;
        int mod = 1000000007;
        for (int i = 0; i < A.length; i++) {
            res = (res + map.getOrDefault(target - A[i], 0)) % mod;

            for (int j = 0; j < i; j++) {
                int temp = A[i] + A[j];
                map.put(temp, map.getOrDefault(temp, 0) + 1);
            }
        }
        return res;
    }

 */