/*

Return the length of the shortest, non-empty, contiguous subarray of A with sum at least K.

If there is no non-empty subarray with sum at least K, return -1.



Example 1:

Input: A = [1], K = 1
Output: 1
Example 2:

Input: A = [1,2], K = 4
Output: -1
Example 3:

Input: A = [2,-1,2], K = 3
Output: 3


Note:

1 <= A.length <= 50000
-10 ^ 5 <= A[i] <= 10 ^ 5
1 <= K <= 10 ^ 9

 */
package arrays.hard;

import java.util.ArrayDeque;
import java.util.Deque;

public class ShortestSubArray {

    public int shortestSubarray(int[] A, int K) {
        int N = A.length;
        int res = N + 1;
        int[] sum = new int[N + 1];
        for (int i = 0; i < N; i++)  {
            sum[i + 1] = sum[i] + A[i];
        }
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < N + 1; i++) {
            while (!dq.isEmpty() && sum[i] <= sum[dq.getLast()]) {
                dq.pollLast();
            }
            while (!dq.isEmpty() && sum[i] - sum[dq.getFirst()] >=  K) {
                res = Math.min(res, i - dq.getFirst());
                dq.pollFirst();
            }
            dq.addLast(i);
        }
        return res <= N ? res : -1;
    }

    public static void main(String[] args) {
        System.out.println(new ShortestSubArray().shortestSubarray(new int[]{2,-1,2},3));
    }

}


/*

For the current prefix sum sum[i], it covers all sub array ending at A[i-1].
We want know if there is a subarray, which starts from an index, ends at A[i-1] and has at least sum K.
So we start to compare sum[i] with the smallest prefix sum in our deque,
which is B[D[0]], hoping that [i] - B[d[0]] >= K.
So if B[i] - B[d[0]] >= K, we can update our result res = min(res, i - d.popleft()).
The while loop helps compare one by one, until this condition isn't valid anymore.


Problem of the Sliding window with negative values
Now, let's take an example with negative values nums = [3, -2, 5]
and target=4. Initially start=0, we keep moving the end pointer until we satisfy the condition,
here we will have start=0 and end=2. Now we are going to move the start pointer start=1.
The sum of the current subarray is -2+5=3 < 4 so we violate the condition.
However if we just move the start pointer another time start=2 we will find 5 >= 4
and we are satisfying the condition. And this is not what the Sliding window assumes.


Variation of Max Sliding window.

 */