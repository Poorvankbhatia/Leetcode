/*

Design and implement a TwoSum class. It should support the following operations: add and find.

add - Add the number to an internal data structure.
find - Find if there exists any pair of numbers which sum is equal to the value.

For example,

add(1);
add(3);
add(5);
find(4) -> true
find(7) -> false

 */

package design.medium;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by poorvank on 18/09/16.
 */
public class TwoSum {

    private HashMap<Integer,Integer> map = new HashMap<>();

    public void add(int x) {
        if(!map.containsKey(x)) {
            map.put(x,1);
        } else {
            map.put(x,map.get(x)+1);
        }
    }

    public boolean find(int x) {

        Set<Integer> keys = map.keySet();

        for (Integer element : keys) {
            if(map.containsKey(x-element)) {
                int target = map.get(x-element);
                if(target==x && map.get(target)<2) {
                    continue;
                }
                return true;
            }
        }

        return false;

    }



}
