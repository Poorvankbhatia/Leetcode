/*

Given a collection of integers that might contain duplicates, nums, return all possible subsets.

Note: The solution set must not contain duplicate subsets.

For example,
If nums = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]

 */
package bits.medium;

import java.util.*;

/**
 * Created by poorvank.b on 27/04/17.
 */
public class Subsets2 {

    public List<List<Integer>> subsetsWithDup(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        Set<String> set = new HashSet<>();
        if(nums.length==0) {
            return result;
        }
        int n = nums.length;
        for (int i=0;i<(1<<n);i++) {
            List<Integer> list = new ArrayList<>();
            for (int j=0;j<n;j++) {

                if ((i & (1 << j)) > 0) {
                    list.add(nums[j]);
                }

            }
            String s = listToStr(list);
            if(!set.contains(s)) {
                result.add(list);
            }
            set.add(s);
        }

        return result;

    }

    private String listToStr(List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        Collections.sort(list);
        list.forEach(sb::append);
        return sb.toString();
    }

    public static void main(String[] args) {
        int[] arr = {4,1,4};
        System.out.println(new Subsets2().subsetsWithDup(arr));
    }

}
