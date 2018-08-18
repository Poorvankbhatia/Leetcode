/*

Given a blacklist B containing unique integers from [0, N), write a function to return a uniform random integer from [0, N) which is NOT in B.

Optimize it such that it minimizes the call to system’s Math.random().

Note:

1 <= N <= 1000000000
0 <= B.length < min(100000, N)
[0, N) does NOT include N. See interval notation.
Example 1:

Input:
["Solution","pick","pick","pick"]
[[1,[]],[],[],[]]
Output: [null,0,0,0]
Example 2:

Input:
["Solution","pick","pick","pick"]
[[2,[]],[],[],[]]
Output: [null,1,1,1]
Example 3:

Input:
["Solution","pick","pick","pick"]
[[3,[1]],[],[],[]]
Output: [null,0,0,2]
Example 4:

Input:
["Solution","pick","pick","pick"]
[[4,[2]],[],[],[]]
Output: [null,1,3,1]


 */
package math.medium;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by poorvank.b on 18/08/18.
 */
public class RandomPickBlacklist {

    private class Solution {

        Map<Integer,Integer> map;
        Random random;
        int remainingSize = 0;

        public Solution(int N, int[] blacklist) {
            map = new HashMap<>();
            random = new Random();
            for (int b : blacklist) {
                map.put(b,-1);
            }
            remainingSize = N-map.size();

            for (int b : blacklist) {
                if(b<remainingSize) {
                    while (map.containsKey(N-1)) {
                        N--;
                    }
                    map.put(b,N-1);
                    N--;
                }
            }

        }

        public int pick() {
            int val = random.nextInt(remainingSize);
            if(map.containsKey(val)) {
                return map.get(val);
            }
            return val;
        }

    }

}
