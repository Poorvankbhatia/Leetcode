/*

Given a collection of distinct numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]

 */
package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 24/08/16.
 */
public class Permutations {

    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        List<Integer> intList = new ArrayList<>();
        for (int num : nums) {
            intList.add(num);
        }
        List<Integer> soFar = new ArrayList<>();
        permuteUtil(soFar,intList);

        return result;
    }

    @SuppressWarnings("unchecked")
    private void permuteUtil(List<Integer> soFar,List<Integer> remaining) {

        if(remaining.size()==0) {
            // Do not do the classic mistake of result.add(soFar), As you will change soFar inner List will also change
            result.add(new ArrayList<>(soFar));
            soFar.remove(soFar.size()-1);
        } else {

            for (int i=0;i<remaining.size();i++) {

                List<Integer> newRemaining = new ArrayList<>(remaining.subList(0,i));
                newRemaining.addAll(remaining.subList(i+1,remaining.size()));


                soFar.add(remaining.get(i));
                permuteUtil(soFar,newRemaining);
            }

            if(!soFar.isEmpty()) {
                soFar.remove(soFar.size()-1);
            }

        }

    }


    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        System.out.println(new Permutations().permute(nums));
    }

}

