/*
Given an array A of positive integers, call a (contiguous, not necessarily distinct) subarray of A good if the number of
different integers in that subarray is exactly K.

(For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.)

Return the number of good subarrays of A.



Example 1:

Input: A = [1,2,1,2,3], K = 2
Output: 7
Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
Example 2:

Input: A = [1,2,1,3,4], K = 3
Output: 3
Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].


Note:

1 <= A.length <= 20000
1 <= A[i] <= A.length
1 <= K <= A.length

 */
package arrays.Hard;

import java.util.HashMap;
import java.util.Map;

public class SubArraysWithKDistinctIntegers {

    public int subarraysWithKDistinct(int[] A, int K) {

        int count = 0;
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        int left1 = 0;
        int left2 = 0;

        for (int val : A) {
            map1.put(val, map1.getOrDefault(val, 0) + 1);
            map2.put(val, map2.getOrDefault(val, 0) + 1);

            while (map1.size() > K) {
                int l1 = map1.getOrDefault(A[left1], 0);
                if (l1 == 1)
                    map1.remove(A[left1]);
                else
                    map1.put(A[left1], l1 - 1);
                left1++;
            }

            while (map2.size() >= K) {
                int l2 = map2.getOrDefault(A[left2], 0);
                if (l2 == 1)
                    map2.remove(A[left2]);
                else
                    map2.put(A[left2], l2 - 1);
                left2++;
            }

            count += left2 - left1;
        }

        return count;
    }

    public static void main(String[] args) {
        int[] A = new int[]{1,2,1,2,3};
        System.out.println(new SubArraysWithKDistinctIntegers().subarraysWithKDistinct(A,2));
    }

}
/*

The two windows track two different things. The loop incrementally adds array elements to the windows so both have the same rightmost element.
 The first window is as large as it can be. It either has all elements added in the loop up to that point or is the largest possible valid array
 from a given start point on the right. The second window will remove elements while it is a valid window so that it is either all elements or
 one less than a valid window, again from that same start point. As a result all values between the first and second window's
 length with a given endpoint are valid sub-arrays (plus the first window itself). So when you add all valid sub-arrays
 for each given endpoint, you get the solution.

In your example, A = [1,2,1,2,3], K = 2
i = 0: w1 = [1], w2 = [1], w1.length - w2.length = 0 // No valid subarray if you only have the first element
i = 1: w1 = [1,2], w2 = [2], 2-1 = 1 //Only one valid subarray with those elements
i = 2: w1= [1,2,1], w1 = [1], 3-1 = 2 //Two valid because there is a value between w1 and w2
i = 3: w1 = [1,2,1,2], w2 = [2], 4-1 = 3
i = 4: w1 = [2,3], w2 = [3], 2-1 = 1

total = 7 = 1+2+3+1

 */
