/*
Design a number container system that can do the following:

Insert or Replace a number at the given index in the system.
Return the smallest index for the given number in the system.
Implement the NumberContainers class:

NumberContainers() Initializes the number container system.
void change(int index, int number) Fills the container at index with the number. If there is already a number at that index, replace it.
int find(int number) Returns the smallest index for the given number, or -1 if there is no index that is filled by number in the system.


Example 1:

Input
["NumberContainers", "find", "change", "change", "change", "change", "find", "change", "find"]
[[], [10], [2, 10], [1, 10], [3, 10], [5, 10], [10], [1, 20], [10]]
Output
[null, -1, null, null, null, null, 1, null, 2]

Explanation
NumberContainers nc = new NumberContainers();
nc.find(10); // There is no index that is filled with number 10. Therefore, we return -1.
nc.change(2, 10); // Your container at index 2 will be filled with number 10.
nc.change(1, 10); // Your container at index 1 will be filled with number 10.
nc.change(3, 10); // Your container at index 3 will be filled with number 10.
nc.change(5, 10); // Your container at index 5 will be filled with number 10.
nc.find(10); // Number 10 is at the indices 1, 2, 3, and 5. Since the smallest index that is filled with 10 is 1, we return 1.
nc.change(1, 20); // Your container at index 1 will be filled with number 20. Note that index 1 was filled with 10 and then replaced with 20.
nc.find(10); // Number 10 is at the indices 2, 3, and 5. The smallest index that is filled with 10 is 2. Therefore, we return 2.


Constraints:

1 <= index, number <= 109
At most 105 calls will be made in total to change and find.
 */
package design.medium;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class NumberContainers {

    // number to ordered indexes.
    private Map<Integer, TreeSet<Integer>> map;
    // index to stored number.
    private Map<Integer, Integer> idxNumberMap;

    public NumberContainers() {
        map = new HashMap<>();
        idxNumberMap = new HashMap<>();
    }

    public void change(int index, int number) {
        // get the current stored number.
        int storedNum = idxNumberMap.getOrDefault(index, -1);
        map.putIfAbsent(number, new TreeSet<>());
        // update the index.
        idxNumberMap.put(index, number);
        // If not stored, store
        if (storedNum == -1) {
            map.get(number).add(index);
        } else {
            // remove from the current number mapping, move to the new one.
            // O(log n)
            map.get(storedNum).remove(index);
            // O(1)
            map.get(number).add(index);
        }
    }

    // O(1)
    public int find(int number) {
        TreeSet<Integer> set = map.get(number);
        return (set == null || set.isEmpty()) ? -1 : set.first();
    }

    public static void main(String[] args) {
        NumberContainers nc = new NumberContainers();
        nc.change(25, 50); // Your container at index 2 will be filled with number 10.
        nc.change(56, 31); // Your container at index 1 will be filled with number 10.
        System.out.println(nc.find(50));
        System.out.println(nc.find(50));
        System.out.println(nc.find(43));
        nc.change(30, 50); // Your container at index 3 will be filled with number 10.
        System.out.println(nc.find(31));
        System.out.println(nc.find(43));
        nc.change(25, 20);
        System.out.println(nc.find(50));
        nc.change(56, 43);
        nc.change(68, 31);
        nc.change(56, 31);
        System.out.println(nc.find(20));
        System.out.println(nc.find(43));
    }
}