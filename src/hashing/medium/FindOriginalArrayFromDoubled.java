/*
An integer array original is transformed into a doubled array changed by appending twice the value of
every element in original, and then randomly shuffling the resulting array.

Given an array changed, return original if changed is a doubled array. If changed is not a doubled array,
return an empty array. The elements in original may be returned in any order.



Example 1:

Input: changed = [1,3,4,2,6,8]
Output: [1,3,4]
Explanation: One possible original array could be [1,3,4]:
- Twice the value of 1 is 1 * 2 = 2.
- Twice the value of 3 is 3 * 2 = 6.
- Twice the value of 4 is 4 * 2 = 8.
Other original arrays could be [4,3,1] or [3,1,4].
Example 2:

Input: changed = [6,3,0,1]
Output: []
Explanation: changed is not a doubled array.
Example 3:

Input: changed = [1]
Output: []
Explanation: changed is not a doubled array.


Constraints:

1 <= changed.length <= 105
0 <= changed[i] <= 105
 */
package hashing.medium;

import java.util.*;

public class FindOriginalArrayFromDoubled {

    public int[] findOriginalArray(int[] changed) {
        // if we do not sort, then incorrect answer in case of [4,4,16,20,8,8,2,10]
        // Sort to start from the lowest and check if double value is present in map.
        Arrays.sort(changed);
        if(changed.length%2!=0) {
            return new int[0];
        }
        List<Integer> list = new ArrayList<>();
        Map<Integer,Integer> map = new HashMap<>();
        for(int n : changed) {
            if(n%2==0) {
                int half = n/2;
                if(map.getOrDefault(half,0)>0) {
                    map.put(half,map.get(half)-1);
                    list.add(half);
                    continue;
                }
            }
            map.put(n,map.getOrDefault(n,0)+1);
        }

        if(list.size()==changed.length/2) {
            int[] result = new int[list.size()];
            for (int k = 0; k<list.size();k++) {
                result[k] = list.get(k);
            }
            return result;
        } else {
            return new int[0];
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new FindOriginalArrayFromDoubled().findOriginalArray(
                new int[]{4,4,16,20,8,8,2,10}
        )));
    }

}
