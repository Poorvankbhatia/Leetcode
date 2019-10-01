/*

Given a non-empty array of unique positive integers A, consider the following graph:

There are A.length nodes, labelled A[0] to A[A.length - 1];
There is an edge between A[i] and A[j] if and only if A[i] and A[j] share a common factor greater than 1.
Return the size of the largest connected component in the graph.



Example 1:

Input: [4,6,15,35]
Output: 4

Input: [2,3,6,7,4,12,21,39]
Output: 8

 */
package bfsdfs.hard;

import bfsdfs.UnionFind;

import java.util.*;

public class LargestComponentSize {

    public int largestComponentSize(int[] A) {

        int n = A.length;
        UnionFind uf = new UnionFind(n);
        Map<Integer,Integer> map = new HashMap<>();

        for (int i=0;i<n;i++) {
            int a = A[i];
            for (int j = 2; j * j <= a; j++){
                if (a % j == 0){
                    if (!map.containsKey(j)){//this means that no index has claimed the factor yet
                        map.put(j, i);
                    }else{//this means that one index already claimed, so union that one with current
                        uf.union(i, map.get(j));
                    }
                    if (!map.containsKey(a/j)){
                        map.put(a/j, i);
                    }else{
                        uf.union(i, map.get(a/j));
                    }
                }
            }
            if (!map.containsKey(a)){
                map.put(a, i);
            }else{
                uf.union(i, map.get(a));
            }
        }

        return uf.getMaxSize();

    }

    public static void main(String[] args) {
        int[] arr = new int[]{65,27,100,37,12,19,4,58,91,5};
        System.out.println(new LargestComponentSize().largestComponentSize(arr));
    }

}

/*

HashMap: key is the factor, val is the index in A
Time complexity: O(N*sqrt(Max val of A[i]))

 */