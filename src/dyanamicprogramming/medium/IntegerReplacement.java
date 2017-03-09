/*

Given a positive integer n and you can do operations as follow:

If n is even, replace n with n/2.
If n is odd, you can replace n with either n + 1 or n - 1.
What is the minimum number of replacements needed for n to become 1?

Example 1:

Input:
8

Output:
3

Explanation:
8 -> 4 -> 2 -> 1
Example 2:

Input:
7

Output:
4

Explanation:
7 -> 8 -> 4 -> 2 -> 1
or
7 -> 6 -> 3 -> 2 -> 1

 */
package dyanamicprogramming.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by poorvank.b on 19/02/17.
 */
public class IntegerReplacement {

    Map<Integer,Integer> map = new HashMap<>();

    public int integerReplacement(int n) {


        if(n<1) {
            return 0;
        }

        map.put(1,0);
        map.put(2,1);

        return util(n);

    }

    /*

    To hand the overflow for test case INT.MAX, use 1 + (n - 1) / 2 instead of (n + 1) / 2.
     The idea comes from solving some binary search questions. To avoid overflow, we use int mid = start + (end - start) / 2
      instead of int mid = (start + end) / 2

     */
    private int util(int n) {

        if(n==1) {
            return 0;
        }

        if(map.containsKey(n)) {
            return map.get(n);
        }

        if(n%2==0) {
            map.put(n,1+util(n/2));
        } else {
            int min;
            if(n-Integer.MAX_VALUE==0) {
                min = 2+util(1 + (n - 1) / 2);
            } else {
                min = 1+Math.min(util(n+1),util(n-1));
            }
            map.put(n,min);
        }

        return map.get(n);

    }



    public static void main(String[] args) {
        System.out.print(new IntegerReplacement().integerReplacement(Integer.MAX_VALUE));
    }

}
