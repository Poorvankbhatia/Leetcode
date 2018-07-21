/*

Given two arrays A and B of equal size, the advantage of A with respect to B is the number of indices i for which A[i] > B[i].

Return any permutation of A that maximizes its advantage with respect to B.



Example 1:

Input: A = [2,7,11,15], B = [1,10,4,11]
Output: [2,11,7,15]
Example 2:

Input: A = [12,24,8,32], B = [13,25,32,11]
Output: [24,32,8,12]


Note:

1 <= A.length = B.length <= 10000
0 <= A[i] <= 10^9
0 <= B[i] <= 10^9

 */
package arrays.medium;

import java.util.TreeMap;

/**
 * Created by poorvank.b on 21/07/18.
 */
public class AdvantageCount {

    public int[] advantageCount(int[] A, int[] B) {
        TreeMap<Integer, Integer> map = new TreeMap<>();

        //Add each number to the map along with count
        for (int a : A) map.put(a, map.getOrDefault(a, 0)+1);

        int[] ret = new int[A.length];
        for (int i = 0; i < B.length; i++) {
            //Find the best number to beat B[i]
            ret[i] = findBestMatch(B[i], map);
        }
        return ret;
    }

    private int findBestMatch(int target, TreeMap<Integer, Integer> map) {
        // See if there exists a number higher than the target
        Integer res = map.higherKey(target);

        // If a number higher than target does not exist, use the smalles available number
        if (res == null) res = map.firstKey();

        //Update the TreeMap, remove the key if the number has 0 remaining occurences
        map.put(res, map.get(res) - 1);
        if (map.get(res) == 0) map.remove(res);

        return res;
    }

}
/*

Count elements in A to a map m.
For each element in B, find the least bigger element in map m.
Otherwise, we'll take the smallest element.
Then we update the m.

Time Complexity:
O(NlogN)

 */
