/*

Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9
can be used and each combination should be a unique set of numbers.


Example 1:

Input: k = 3, n = 7

Output:

[[1,2,4]]

Example 2:

Input: k = 3, n = 9

Output:

[[1,2,6], [1,3,5], [2,3,4]]

 */
package arrays.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 20/01/17.
 */
public class CombinationSum3 {

    public List<List<Integer>> combinationSum3(int k, int n) {

        List<List<Integer>> result = new ArrayList<>();

        if(k>=n) {
            return result;
        }

        combinationSumUtil(n,9,k,new ArrayList<>(),result,1);

        return result;

    }

    private void combinationSumUtil(int n,int max,int k,List<Integer> currentList,List<List<Integer>> result,int start) {


        if(n==0 && k==0) {
            result.add(new ArrayList<>(currentList));
            return;
        }

        if(k<=0) {
            return;
        }

        for (int i=start;i<=max;i++) {
            currentList.add(i);
            combinationSumUtil(n-i,max,k-1,currentList,result,i+1);
            currentList.remove(currentList.size()-1);
        }

    }

    public static void main(String[] args) {
        System.out.println(new CombinationSum3().combinationSum3(3,15));
    }

}

/*

Simple DFS is used.

 */
