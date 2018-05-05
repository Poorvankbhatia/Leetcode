/*

Given an array of unique integers, each integer is strictly greater than 1.

We make a binary tree using these integers and each number may be used for any number of times.

Each non-leaf node's value should be equal to the product of the values of it's children.

How many binary trees can we make?  Return the answer modulo 10 ** 9 + 7.

Example 1:

Input: A = [2, 4]
Output: 3
Explanation: We can make these trees: [2], [4], [4, 2, 2]
Example 2:

Input: A = [2, 4, 5, 10]
Output: 7
Explanation: We can make these trees: [2], [4], [5], [10], [4, 2, 2], [10, 2, 5], [10, 5, 2].


Note:

1 <= A.length <= 1000.
2 <= A[i] <= 10 ^ 9.

 */
package tree.hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by poorvank.b on 28/04/18.
 */
public class BinaryTreeWithFactors {

    int pow  = (int) Math.pow(10,9)+7;

    public int numFactoredBinaryTrees(int[] A) {

        Arrays.sort(A);

        Map<Integer,Long> countMap = new HashMap<>();

        countMap.put(A[0],1L);

        for (int i=1;i<A.length;i++) {
            long count = 1;
            for (int key : countMap.keySet()) {
                if(A[i]%key==0 && countMap.containsKey(A[i]/key)) {
                    count+=countMap.get(key)*countMap.get(A[i]/key);
                }
            }
            countMap.put(A[i],count);
        }

        long sum=0;

        for (int key : countMap.keySet()) {
            sum = (sum+countMap.get(key))%pow;
        }

        return (int) sum;

    }

}

/**
 * sort the array
 * and use HashMap to record each Integer, and the number of trees with that Integer as root
 * (1) each integer A[i] will always have one tree with only itself
 * (2) if A[i] has divisor (a) in the map, and if A[i]/a also in the map
 *     then a can be the root of its left subtree, and A[i]/a can be the root of its right subtree;
 *     the number of such tree is map.get(a) * map.get(A[i]/a)
 * (3) sum over the map
 */