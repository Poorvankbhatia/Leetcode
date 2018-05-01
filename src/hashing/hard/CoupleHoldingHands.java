/*

N couples sit in 2N seats arranged in a row and want to hold hands. We want to know the minimum number of swaps so that every
couple is sitting side by side. A swap consists of choosing any two people, then they stand up and switch seats.

The people and seats are represented by an integer from 0 to 2N-1, the couples are numbered in order, the first couple being (0, 1),
the second couple being (2, 3), and so on with the last couple being (2N-2, 2N-1).

The couples' initial seating is given by row[i] being the value of the person who is initially sitting in the i-th seat.

Example 1:

Input: row = [0, 2, 1, 3]
Output: 1
Explanation: We only need to swap the second (row[1]) and third (row[2]) person.
Example 2:

Input: row = [3, 2, 0, 1]
Output: 0
Explanation: All couples are already seated side by side.
Note:

len(row) is even and in the range of [4, 60].
row is guaranteed to be a permutation of 0...len(row)-1.

 */
package hashing.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by poorvank.b on 11/04/18.
 */
public class CoupleHoldingHands {

    public int minSwapsCouples(int[] row) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < row.length; i += 2) {
            map.put(row[i], row[i + 1]);
            map.put(row[i + 1], row[i]);
        }

        int count = 0;
        for (int index = 0; index < row.length; index += 2) {
            if (map.get(index) != index + 1) {
                int curr = map.get(index);
                int next = map.get(index + 1);
                map.put(curr, next);
                map.put(next, curr);
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] row = new int[]{0, 2, 1, 3};
        System.out.println(new CoupleHoldingHands().minSwapsCouples(row));
    }



}

/*

The whole idea is find out where current element’s couple is, and if it’s not the current neighbor, swap it with
current neighbor and update the neighbor’s position in hash map.

 */