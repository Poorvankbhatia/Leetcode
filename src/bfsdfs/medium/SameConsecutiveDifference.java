/*

Return all non-negative integers of length N such that the absolute difference between every two consecutive digits is K.

Note that every number in the answer must not have leading zeros except for the number 0 itself.
For example, 01 has one leading zero and is invalid, but 0 is valid.

You may return the answer in any order.



Example 1:

Input: N = 3, K = 7
Output: [181,292,707,818,929]
Explanation: Note that 070 is not a valid number, because it has leading zeroes.
Example 2:

Input: N = 2, K = 1
Output: [10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]


Note:

1 <= N <= 9
0 <= K <= 9


 */
package bfsdfs.medium;

import java.util.*;

/**
 * Created by poorvank.b on 30/12/18.
 */
public class SameConsecutiveDifference {

    public int[] numsSameConsecDiff(int N, int K) {

        List<Integer> result = new ArrayList<>();
        for(int i=1;i<=9;i++) {
            if(N>1 && i==0) {
                continue;
            }
            dfs(i,N,K,1,result,i);
        }

        int[] arr = new int[result.size()];
        int i=0;
        for (Integer v : result) {
            arr[i]=v;
            i++;
        }

        return arr;

    }

    private void dfs(int start,int N,int k,int size,List<Integer> list,int lastDigit) {

        if(size==N) {
            list.add(start);
            return;
        }
        if(lastDigit+k<=9) {
            dfs(start*10+(lastDigit+k),N,k,size+1,list,lastDigit+k);
        }
        if(lastDigit-k>=0 && k!=0) {
            dfs(start*10+(lastDigit-k),N,k,size+1,list,lastDigit-k);
        }

    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new SameConsecutiveDifference().numsSameConsecDiff(2,0)));
    }


}
