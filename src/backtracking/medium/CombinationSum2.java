/*

Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where
the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8,
A solution set is:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]

 */

package backtracking.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by poorvank on 01/09/16.
 */
public class CombinationSum2 {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result,target,0,new ArrayList<>(),candidates);
        return result;
    }

    private void backtrack(List<List<Integer>> result, int target, int index, List<Integer> list,
                           int[] candidates) {
        if(target<0) return;
        if(target==0) {
            result.add(new ArrayList<>(list));
            return;
        }
        int prev = -1;
        for(int i=index;i<candidates.length;i++) {
            if(prev==candidates[i]) continue;
            if(candidates[i]>target) break;
            list.add(candidates[i]);
            backtrack(result,target-candidates[i],i+1,list,candidates);
            list.remove(list.size()-1);
            prev = candidates[i];
        }
    }

    public static void main(String[] args) {

        int[] arr = new int[]{1};
        int target =4;

        CombinationSum2 sum = new CombinationSum2();
        System.out.println(sum.combinationSum2(arr,target));

    }



}
