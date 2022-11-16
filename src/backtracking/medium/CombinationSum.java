/*

Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
For example, given candidate set [2, 3, 6, 7] and target 7,
A solution set is:
[
  [7],
  [2, 2, 3]
]

 */

package backtracking.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by poorvank on 01/09/16.
 */
public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        Arrays.sort(candidates);
        List<List<Integer>> lists = new ArrayList<>();
        combinationSumUtil(candidates,target,new ArrayList<>(),lists,0);
        return lists;

    }

    private void combinationSumUtil(int[] candidates,int target,List<Integer> list,
                                    List<List<Integer>> result,int arrayIndex) {

        if(target<0 || (target!=0 && candidates[arrayIndex]>target)) {
            return;
        }

        if(target==0) {
            result.add(new ArrayList<>(list));
            return;
        }

        for(int i=arrayIndex;i<candidates.length;i++) {

            if(candidates[i]>target) {
                break;
            }
            list.add(candidates[i]);
            combinationSumUtil(candidates,target-candidates[i],list,result,i);
            list.remove(list.size()-1);
        }

    }

    public static void main(String[] args) {

        int[] arr = new int[]{1,1,1};
        int target =3;

        CombinationSum sum = new CombinationSum();
        System.out.println(sum.combinationSum(arr,target));

    }

}
