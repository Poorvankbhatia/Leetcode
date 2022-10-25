/*
You are given a 2D integer array items where items[i] = [pricei, beautyi] denotes the price and beauty of an item respectively.

You are also given a 0-indexed integer array queries. For each queries[j], you want to determine the maximum beauty of
an item whose price is less than or equal to queries[j]. If no such item exists, then the answer to this query is 0.

Return an array answer of the same length as queries where answer[j] is the answer to the jth query.



Example 1:

Input: items = [[1,2],[3,2],[2,4],[5,6],[3,5]], queries = [1,2,3,4,5,6]
Output: [2,4,5,5,6,6]
Explanation:
- For queries[0]=1, [1,2] is the only item which has price <= 1. Hence, the answer for this query is 2.
- For queries[1]=2, the items which can be considered are [1,2] and [2,4].
  The maximum beauty among them is 4.
- For queries[2]=3 and queries[3]=4, the items which can be considered are [1,2], [3,2], [2,4], and [3,5].
  The maximum beauty among them is 5.
- For queries[4]=5 and queries[5]=6, all items can be considered.
  Hence, the answer for them is the maximum beauty of all items, i.e., 6.
Example 2:

Input: items = [[1,2],[1,2],[1,3],[1,4]], queries = [1]
Output: [4]
Explanation:
The price of every item is equal to 1, so we choose the item with the maximum beauty 4.
Note that multiple items can have the same price and/or beauty.
Example 3:

Input: items = [[10,1000]], queries = [5]
Output: [0]
Explanation:
No item has a price less than or equal to 5, so no item can be chosen.
Hence, the answer to the query is 0.


Constraints:

1 <= items.length, queries.length <= 105
items[i].length == 2
1 <= pricei, beautyi, queries[j] <= 109
 */
package binarysearch.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class MostBeautifulItem {

    public int[] maximumBeauty(int[][] items, int[] queries) {

        int[] result = new int[queries.length];
        Arrays.sort(items, Comparator.comparingInt(a -> a[0]));
        TreeMap<Integer, Integer> maxValueMap = new TreeMap<>();
        for (int i = 0; i < queries.length; i++) {
            int find = bs(items, queries[i]);
            if (find == -1) {
                result[i] = 0;
            } else {
                result[i] = maxBeauty(items, find, maxValueMap);
            }
        }
        return result;

    }

    private int maxBeauty(int[][] items, int end, TreeMap<Integer, Integer> maxValueMap) {
        Map.Entry<Integer, Integer> floorKey = maxValueMap.floorEntry(end);
        int start = floorKey == null ? 0 : floorKey.getKey();
        int max = floorKey == null ? 0 : floorKey.getValue();
        for (int i = start; i <= end; i++) {
            max = Math.max(max, items[i][1]);
        }
        maxValueMap.put(end, max);
        return max;
    }

    private int bs(int[][] items, int toFind) {
        int start = 0;
        int end = items.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (items[mid][0] == toFind && (items[mid][0] == end || items[mid + 1][0] > toFind)) {
                return mid;
            }
            if (items[mid][0] > toFind) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return items[start][0] > toFind ? start - 1 : start;
    }

    public static void main(String[] args) {
        int[][] a = new int[][]{
                {2, 2}, {2, 4}, {3, 2}, {3, 5}, {5, 6}
        };
        int[] q = new int[]{1, 2, 3, 4, 5, 6};
        System.out.println(Arrays.toString(new MostBeautifulItem().maximumBeauty(a, q)));
    }

}

/*

TreeMap solution:

class Solution {
    public int[] maximumBeauty(int[][] items, int[] queries) {
        int[] result = new int[queries.length];
        Arrays.sort(items, (a, b) -> (a[0] - b[0]));

        TreeMap<Integer, Integer> map = new TreeMap<>(){{put(0, 0);}};
        int currMax = 0;
        for(int[] item : items) {
            currMax = Math.max(currMax, item[1]);      //maintain largerst beauty so far
            map.put(item[0], currMax);                 //store in treeMap
        }

        for(int i = 0; i < queries.length; ++i)
            result[i] = map.floorEntry(queries[i]).getValue();

        return result;
    }
}

class Solution {
    public int[] maximumBeauty(int[][] items, int[] queries) {
        int[] result = new int[queries.length];
        Arrays.sort(items, (a, b) -> (a[0] - b[0]));

        for(int i = 1; i < items.length; ++i)
            items[i][1] = Math.max(items[i][1], items[i - 1][1]);

        for(int j = 0; j < queries.length; ++j)
            result[j] = binarySearch(items, queries[j]);

        return result;
    }

    private int binarySearch(int[][] items, int target) {
        int lo = 0, hi = items.length - 1;
        while(lo < hi) {
            int mid = (lo + hi + 1) >> 1;  //find rightmost item
            if(items[mid][0] > target)
                hi = mid - 1;
            else
                lo = mid;
        }
        return items[lo][0] <= target ? items[lo][1] : 0;
    }
}

 */