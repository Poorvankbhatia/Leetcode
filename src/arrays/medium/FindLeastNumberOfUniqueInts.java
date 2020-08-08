/*
Given an array of integers arr and an integer k. Find the least number of unique integers after removing exactly k elements.



Example 1:

Input: arr = [5,5,4], k = 1
Output: 1
Explanation: Remove the single 4, only 5 is left.
Example 2:
Input: arr = [4,3,1,1,3,3,2], k = 3
Output: 2
Explanation: Remove 4, 2 and either one of the two 1s or three 3s. 1 and 3 will be left.


Constraints:

1 <= arr.length <= 10^5
1 <= arr[i] <= 10^9
0 <= k <= arr.length
 */
package arrays.medium;

import java.util.*;

public class FindLeastNumberOfUniqueInts {

    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int a: arr) {
            map.put(a,map.getOrDefault(a,0)+1);
        }
        List<Map.Entry<Integer,Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());
        int remove = 0, n = list.size();
        for (int i = 0; i < n && k > 0; i++) {
            k -= list.get(i).getValue();
            if (k >= 0) {
                remove++;
            }
        }
        return n-remove;
    }

    public static void main(String[] args) {
        System.out.println(new FindLeastNumberOfUniqueInts().findLeastNumOfUniqueInts(new int[]{2,1,1,3,3,3}, 3));
    }


}

/*

    treeMap method:

 public int findLeastNumOfUniqueInts(int[] arr, int k) {
       Map<Integer,Integer> map = new HashMap<>();
        for(int a: arr) {
            map.put(a,map.getOrDefault(a,0)+1);
        }
        Map<Integer, List<Integer>> treeMap = new TreeMap<>();
        for(Map.Entry<Integer,Integer> entry : map.entrySet()) {
            treeMap.putIfAbsent(entry.getValue(), new ArrayList<>());
            treeMap.get(entry.getValue()).add(entry.getKey());
        }

        for(Map.Entry<Integer,List<Integer>> entry : treeMap.entrySet()) {
           if(k>0) {
               int sub= (entry.getKey());
               for (int v : entry.getValue()) {
                   if(k>=sub) {
                       map.remove(v);
                       k -= sub;
                   } else {
                       k = 0;
                   }
               }
           } else {
               break;
           }
        }
        return map.size();
    }
 */