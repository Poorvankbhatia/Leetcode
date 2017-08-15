/*

You are given an integer array sorted in ascending order (may contain duplicates),
you need to split them into several subsequences, where each subsequences consist of at least 3 consecutive integers.
Return whether you can make such a split.

Example 1:
Input: [1,2,3,3,4,5]
Output: True
Explanation:
You can split them into two consecutive subsequences :
1, 2, 3
3, 4, 5
Example 2:
Input: [1,2,3,3,4,4,5,5]
Output: True
Explanation:
You can split them into two consecutive subsequences :
1, 2, 3, 4, 5
3, 4, 5
Example 3:
Input: [1,2,3,4,4,5]
Output: False
Note:
The length of the input is in range of [1, 10000]

 */
package hashing.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by poorvank.b on 15/08/17.
 */

/*COPIED*/
public class SplitArray {

    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        Map<Integer,Integer> lastFreq = new HashMap<>();
        for (int i : nums) {
            freq.put(i, freq.getOrDefault(i,0) + 1);
        }
        for (int i : nums) {
            if (freq.get(i) == 0) {
                continue;
                /**
                 * If current element can be next element of one of previous consecutive sequences,
                 * it means we can append it to that sequence. We don't need to worry about whether
                 * we can use this element to be a new start point of a new consecutive sequence,
                 * that's because even though the current element can be a new start point of a consecutive sequence,
                 * we can simply append those consecutive elements following this current element at the end of the previous consecutive sequence.
                 */
            } else if (lastFreq.getOrDefault(i,0) > 0) {
                lastFreq.put(i, lastFreq.get(i) - 1);
                lastFreq.put(i+1, lastFreq.getOrDefault(i+1,0) + 1);
            } else if (freq.getOrDefault(i+1,0) > 0 && freq.getOrDefault(i+2,0) > 0) {
                freq.put(i+1, freq.get(i+1) - 1);
                freq.put(i+2, freq.get(i+2) - 1);
                lastFreq.put(i+3, lastFreq.getOrDefault(i+3,0) + 1);
            } else {
                return false;
            }
            freq.put(i, freq.get(i) - 1);
        }
        return true;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,3,4,4,5,5};
        System.out.print(new SplitArray().isPossible(arr));
    }

}

/*

We iterate through the array once to get the frequency of all the elements in the array
We iterate through the array once more and for each element we either see if it can be appended to a previously constructed consecutive sequence
or if it can be the start of a new consecutive sequence. If neither are true, then we return false.

 */
