/*

Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]


 */

package backtracking.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 01/05/17.
 */
public class Combination {

    public List<List<Integer>> combine(int n, int k) {

        List<List<Integer>> result = new ArrayList<>();
        if(n<k || n==0) {
            return result;
        }
        combineUtil(n,k,result,new ArrayList<>(),1);

        return result;

    }

    private void combineUtil(int n,int k,List<List<Integer>> result,List<Integer> list,int start) {

        if(k==0) {
            result.add(new ArrayList<>(list));
            return;
        }

        for (int i=start;i<=n;i++) {
            list.add(i);
            combineUtil(n,k-1,result,new ArrayList<>(list),i+1);
            list.remove(list.size()-1);
        }

    }

    public static void main(String[] args) {
        System.out.println(new Combination().combine(4,2));
    }

}
