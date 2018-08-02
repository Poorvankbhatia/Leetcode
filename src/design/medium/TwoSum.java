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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by poorvank on 18/09/16.
 */
public class TwoSum {

    private List<Integer> list = new ArrayList<>();
    private Map<Integer, Integer> map = new HashMap<>();

    // Add the number to an internal data structure.
    public void add(int number) {
        if (map.containsKey(number)) map.put(number, map.get(number) + 1);
        else {
            map.put(number, 1);
            list.add(number);
        }
    }

    // Find if there exists any pair of numbers which sum is equal to the value.
    public boolean find(int value) {
        for (Integer aList : list) {
            int num1 = aList, num2 = value - num1;
            if ((num1 == num2 && map.get(num1) > 1) || (num1 != num2 && map.containsKey(num2))) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {1,4,6,5,4};
        TwoSum twoSum = new TwoSum();
        for (int e : arr) {
            twoSum.add(e);
        }
        System.out.println(twoSum.find(8));
    }


}
