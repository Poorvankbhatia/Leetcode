/*
Design a data structure to find the frequency of a given value in a given subarray.

The frequency of a value in a subarray is the number of occurrences of that value in the subarray.

Implement the RangeFreqQuery class:

RangeFreqQuery(int[] arr) Constructs an instance of the class with the given 0-indexed integer array arr.
int query(int left, int right, int value) Returns the frequency of value in the subarray arr[left...right].
A subarray is a contiguous sequence of elements within an array. arr[left...right] denotes the subarray that contains
the elements of nums between indices left and right (inclusive).



Example 1:

Input
["RangeFreqQuery", "query", "query"]
[[[12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]], [1, 2, 4], [0, 11, 33]]
Output
[null, 1, 2]

Explanation
RangeFreqQuery rangeFreqQuery = new RangeFreqQuery([12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]);
rangeFreqQuery.query(1, 2, 4); // return 1. The value 4 occurs 1 time in the subarray [33, 4]
rangeFreqQuery.query(0, 11, 33); // return 2. The value 33 occurs 2 times in the whole array.


Constraints:

1 <= arr.length <= 105
1 <= arr[i], value <= 104
0 <= left <= right < arr.length
At most 105 calls will be made to query
 */
package hashing.medium;

import java.util.*;

public class RangeFreqQuery {

    Map<Integer, TreeMap<Integer, Integer>> map = new HashMap<>();
    public RangeFreqQuery(int[] arr) {
        for(int i = 0; i < arr.length;i++){
            map.putIfAbsent(arr[i], new TreeMap<>());
            map.get(arr[i]).put(i, map.get(arr[i]).size());
        }

    }

    public int query(int left, int right, int value) {
        if(!map.containsKey(value)) return 0;
        TreeMap<Integer, Integer> nums = map.get(value);
        Integer a = nums.ceilingKey(left), b = nums.floorKey(right);
        if(a == null || b == null) return 0;
        return nums.get(b) - nums.get(a) +1;
    }

    public static void main(String[] args) {
        RangeFreqQuery rangeFreqQuery = new RangeFreqQuery(new int[]{12,33,4,56,22,2,34,33,22,12,34,56});
        System.out.println(rangeFreqQuery.query(1,2,4));
    }

}
