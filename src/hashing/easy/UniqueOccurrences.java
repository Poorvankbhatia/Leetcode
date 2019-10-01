/*

Given an array of integers arr, write a function that returns true if and only if the number of occurrences of each value in the array is unique.



Example 1:

Input: arr = [1,2,2,1,1,3]
Output: true
Explanation: The value 1 has 3 occurrences, 2 has 2 and 3 has 1. No two values have the same number of occurrences.
Example 2:

Input: arr = [1,2]
Output: false
Example 3:

Input: arr = [-3,0,1,-3,1,1,1,-3,10,0]
Output: true

 */
package hashing.easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UniqueOccurrences {

    public boolean uniqueOccurrences(int[] arr) {

        Map<Integer,Integer> map = new HashMap<>();
        for(int a : arr) {
            map.put(a,map.getOrDefault(a,0)+1);
        }
        Set<Integer> set = new HashSet<>();
        for(Map.Entry<Integer,Integer> entry : map.entrySet()) {
            set.add(entry.getValue());
        }
        return set.size()==map.size();
    }

}
