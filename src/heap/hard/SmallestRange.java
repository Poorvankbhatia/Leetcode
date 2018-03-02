/*

You have k lists of sorted integers in ascending order. Find the smallest range that includes at least one number from each of the k lists.

We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c if b-a == d-c.

Example 1:
Input:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
Output: [20,24]
Explanation:
List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
List 2: [0, 9, 12, 20], 20 is in range [20,24].
List 3: [5, 18, 22, 30], 22 is in range [20,24].
Note:
The given list may contain duplicates, so ascending order means >= here.
1 <= k <= 3500
-105 <= value of elements <= 105.
For Java users, please note that the input type has been changed to List<List<Integer>>. And after you reset the code template,
 you'll see this point.

 */

package heap.hard;

import heap.MinPriorityQueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by poorvank on 13/08/17.
 */
public class SmallestRange {

    private class HeapObject implements Comparable<HeapObject> {

        private Integer listIndex;
        private Integer value;
        private Integer valIndex;

        public HeapObject(int listIndex, int value, int valIndex) {
            this.listIndex = listIndex;
            this.value = value;
            this.valIndex = valIndex;
        }

        public HeapObject() {
        }

        @Override
        public int compareTo(HeapObject o) {
            return this.value.compareTo(o.value);
        }
    }

    public int[] smallestRange(List<List<Integer>> nums) {

        if (nums == null || nums.size() == 0) {
            return new int[]{};
        }

        int k = nums.size();
        int totalSize = 0;

        for (List<Integer> list : nums) {
            totalSize += list.size();
        }

        HeapObject[] result = new HeapObject[totalSize]; // Consist of all objects after sorting
        MinPriorityQueue<HeapObject> minPriorityQueue = new MinPriorityQueue<>(k);

        //ADDING OBJECTS TO A MINIMUM HEAP.

        for (int i = 0; i < k; i++) {
            HeapObject heapObject = new HeapObject(i, nums.get(i).get(0), 0);
            minPriorityQueue.insert(heapObject);
        }

        for (int i = 0; i < totalSize; i++) {
            HeapObject heapObject = minPriorityQueue.deleteMin();
            int index = heapObject.listIndex;
            int valIndex = heapObject.valIndex;
            result[i] = heapObject;
            HeapObject next = new HeapObject();
            if (nums.get(index).size() > valIndex + 1) {
                next.listIndex = heapObject.listIndex;
                next.valIndex = heapObject.valIndex + 1;
                next.value = nums.get(index).get(valIndex + 1);
            } else {
                next.listIndex = heapObject.listIndex;
                next.valIndex = heapObject.valIndex + 1;
                next.value = Integer.MAX_VALUE;
            }

            minPriorityQueue.insert(next);

        }

        HashMap<Integer, Integer> map = new HashMap<>(); // Map of children index and there count since duplicate elements can be there

        int start = 0;
        int minLength = Integer.MAX_VALUE;
        int finalStart = 0, finalEnd = 0;

        // Same as minimum window with exactly K unique objects/chars problem
        for (int i = 0; i < totalSize; i++) {

            if (!map.containsKey(result[i].listIndex)) {
                map.put(result[i].listIndex, 1);
            } else {
                map.put(result[i].listIndex, map.get(result[i].listIndex) + 1);
            }

            while (map.size() == k) {

                int diff = result[i].value - result[start].value;
                if ((diff < minLength) || (diff == minLength && result[start].value < result[finalStart].value)) {
                    minLength = diff;
                    finalStart = start;
                    finalEnd = i + 1;
                }

                if (map.get(result[start].listIndex) == 1) {
                    map.remove(result[start].listIndex);
                } else {
                    map.put(result[start].listIndex, map.get(result[start].listIndex) - 1);
                }
                start++;
            }


        }

        return new int[]{result[finalStart].value, result[finalEnd - 1].value};

    }

    public static void main(String[] args) {

        List<Integer> l1 = new ArrayList<>();
        l1.add(4);l1.add(10);l1.add(15);l1.add(24);l1.add(26);
        List<Integer> l2 = new ArrayList<>();
        l2.add(0);l2.add(9);l2.add(12);l2.add(20);
        List<Integer> l3 = new ArrayList<>();
        l3.add(5);l3.add(18);l3.add(22);l3.add(30);

       /* List<Integer> l1 = new ArrayList<>();
        l1.add(1);l1.add(2);l1.add(3);
        List<Integer> l2 = new ArrayList<>();
        l2.add(1);l2.add(2);l2.add(3);
        List<Integer> l3 = new ArrayList<>();
        l3.add(1);l3.add(2);l3.add(3);*/

        List<List<Integer>> list = new ArrayList<>();
        list.add(l1);list.add(l2);list.add(l3);

        System.out.println(Arrays.toString(new SmallestRange().smallestRange(list)));

    }

}


/*

A combination of merge K sorted children and Minimum window substring problem.

 */

