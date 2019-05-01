/*

Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].

Note:
Each element in the result should appear as many times as it shows in both arrays.
The result can be in any order.

 */
package hashing.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by poorvank on 11/10/16.
 */
public class IntersectionArray2 {
    public int[] intersect(int[] nums1, int[] nums2) {

        if(nums1==null || nums2==null || nums1.length==0 || nums2.length==0) {
            return new int[]{};
        }

        HashMap<Integer,Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();


        for (int aNums1 : nums1) {
            if(map.containsKey(aNums1)) {
                map.put(aNums1,map.get(aNums1)+1);
            } else {
                map.put(aNums1,1);
            }
        }

        for (int i : nums2) {
            if (map.containsKey(i) && map.get(i) != 0) {
                list.add(i);
                map.put(i, map.get(i) - 1);
            }
        }

        int[] resultArr = new int[list.size()];

        int j=0;
        for (Integer element : list) {
            resultArr[j] = element;
            j++;
        }

        return resultArr;

    }
}

/*

If arrays are sorted we can use 2 pointer technique.

 */