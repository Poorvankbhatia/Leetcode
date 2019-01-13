package arrays.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by poorvank.b on 13/01/19.
 */
public class SubArraySumDivisibleByK {

    public int subarraysDivByK(int[] A, int K) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int count = 0, sum = 0;
        for(int a : A) {
            sum = (sum + a) % K;
            if(sum < 0) {
                sum += K;
            }
            count += map.getOrDefault(sum, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

}
/*

About the problems of contiguous subarray, presum is a common technique.
Another thing is if presum[i] % K == presum[j] % K, so the sum of subarray [i + 1, j] is divisible by K.
Because (presum[j] - presum[i]) % K = presum[j] % K - presum[i] % K = 0
So for current index j, we need to find out how many index i (i < j) exit that has the same mod of K.
Now it easy to come up with HashMap <mod, frequency>

Time Complexity: O(N)
Space Complexity: O(K)


 */
