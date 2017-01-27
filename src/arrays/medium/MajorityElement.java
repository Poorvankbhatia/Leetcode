/*

Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. The algorithm should run in linear time and in O(1) space.

 */

package arrays.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by poorvank on 28/11/16.
 */
public class MajorityElement {

    public List<Integer> majorityElement(int[] nums) {

        int k =3;
        return majorityElementUtil(nums,k);

    }

    private List<Integer> majorityElementUtil(int[] nums,int k) {

        List<Integer> list = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        if (nums.length == 0) return list;


        if(nums.length<k) {
            for (Integer n  : nums) {
                map.put(n,1);
            }
            for (Integer e  : map.keySet()) {
                list.add(e);
            }

            return list;
        }

        for (int n : nums) {
            if (map.containsKey(n)) map.put(n, map.get(n) + 1);
            //there are at most k-1 elements that could have more than n/k occurrences in an array,
            // for example, at most 2 distinct number could have a frequency of 1/3 in an array.
            else if (map.size() < k-1) map.put(n, 1);
            else {
                boolean flag = false;
                for (Integer i : map.keySet()) {
                    if (map.get(i) == 0) {
                        map.remove(i);
                        map.put(n, 1);
                        flag = true;
                        break;
                    }
                }
                //Decrease all the entries because any of them could be majority element
                if (!flag) {
                    for (Integer i : map.keySet()) {
                        map.put(i, map.get(i) - 1);
                    }
                }
            }
        }

        for (Integer i : map.keySet()) {
            map.put(i, 0);
        }
        for (int n : nums) {
            if (map.containsKey(n)) map.put(n, map.get(n) + 1);
        }
        for (Integer i : map.keySet()) {
            if (map.get(i) > nums.length / k) list.add(i);
        }
        return list;


    }


    public static void main(String[] args) {
        int[] arr = new int[]{1,1,1,1,2,3,3,2,2,2,3};
        System.out.print(new MajorityElement().majorityElement(arr));
    }

}
