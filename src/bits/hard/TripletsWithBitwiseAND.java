/*

Given an array of integers A, find the number of triples of indices (i, j, k) such that:

0 <= i < A.length
0 <= j < A.length
0 <= k < A.length
A[i] & A[j] & A[k] == 0, where & represents the bitwise-AND operator.


Example 1:

Input: [2,1,3]
Output: 12
Explanation: We could choose the following i, j, k triples:
(i=0, j=0, k=1) : 2 & 2 & 1
(i=0, j=1, k=0) : 2 & 1 & 2
(i=0, j=1, k=1) : 2 & 1 & 1
(i=0, j=1, k=2) : 2 & 1 & 3
(i=0, j=2, k=1) : 2 & 3 & 1
(i=1, j=0, k=0) : 1 & 2 & 2
(i=1, j=0, k=1) : 1 & 2 & 1
(i=1, j=0, k=2) : 1 & 2 & 3
(i=1, j=1, k=0) : 1 & 1 & 2
(i=1, j=2, k=0) : 1 & 3 & 2
(i=2, j=0, k=1) : 3 & 2 & 1
(i=2, j=1, k=0) : 3 & 1 & 2


Note:

1 <= A.length <= 1000
0 <= A[i] < 2^16

 */
package bits.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by poorvank.b on 27/01/19.
 */
public class TripletsWithBitwiseAND {

    public int countTriplets(int[] A) {
        int result=0;
        Map<Integer, Integer> table= new HashMap<>();
        for (int aA : A) {
            for (int aA1 : A) {
                int tmp = aA & aA1;
                if (!table.containsKey(tmp)) {
                    table.put(tmp, 1);
                } else {
                    table.replace(tmp, table.get(tmp) + 1);
                }
            }
        }
        for (int aA : A) {
            Integer[] a = new Integer[table.keySet().size()];
            table.keySet().toArray(a);
            for (Integer k : a) {
                if ((aA & k) == 0)
                    result += table.get(k);
            }
        }
        return result;
    }

}
