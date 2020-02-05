/*
Given an array of integers A with even length, return true if and only if it is possible to reorder it such that A[2 * i + 1] = 2 * A[2 * i] for every 0 <= i < len(A) / 2.



Example 1:

Input: [3,1,3,6]
Output: false
Example 2:

Input: [2,1,2,6]
Output: false
Example 3:

Input: [4,-2,2,-4]
Output: true
Explanation: We can take two groups, [-2,-4] and [2,4] to form [-2,-4,2,4] or [2,4,-2,-4].
Example 4:

Input: [1,2,4,16,8,4]
Output: false


Note:

0 <= A.length <= 30000
A.length is even
-100000 <= A[i] <= 100000
 */
package hashing.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CanReorderDouble {

    public boolean canReorderDoubled(int[] A) {
        int n = A.length;
        if (n % 2 != 0) {
            return false;
        }
        Arrays.sort(A);
        Map<Integer, Integer> map = new HashMap<>(); // <num, freq>
        for (int item : A) {
            if (item == 0) continue;
            map.put(item, map.getOrDefault(item, 0) + 1);
        }
        for (int el : A) {
            if (el != 0 && map.get(el) > 0) {
                int target = el * 2; // (3). positive, target = 2x
                if (el < 0) { // (2) for negative (-4), the match should be half of it.
                    if (el % 2 != 0) { // A[i] = -7, A[i] / 2 = -3
                        return false;
                    } else {
                        target = el / 2; // negative: target = x
                    }
                }
                if (map.getOrDefault(target, 0) < map.get(el)) {
                    return false;
                } else {
                    // (4) once matched, update the map
                    map.put(target, map.get(target) - map.get(el));
                    map.put(el, 0);
                }
            }
        }
        return true;
    }

}

/*
// (0). one map only: to count the frequency of apperance of each number
// (1). Sort the array, then scan from the smalllest to find (x, 2x) pairs
// ___ (a) for negative number, we will see 2x first, the match should be half of it.
// ___ (b). for positive, we will see x first, the match should be two times of it.
// (2) once matched, update the map
// (3). no need to put 0 into the map.
Example: [3, 6, -4, -2, 0, 0, 14, 7, -2, -4]
Your stdout
map: {-2=2, -4=2, 3=1, 6=1, 7=1, 14=1}
map: {-2=0, -4=0, 3=1, 6=1, 7=1, 14=1} after process (-4, -2) pair
map: {-2=0, -4=0, 3=0, 6=0, 7=1, 14=1} after process (3, 6) pair
map: {-2=0, -4=0, 3=0, 6=0, 7=0, 14=0} after process (7, 14) pair
 */