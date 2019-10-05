/*

A Stepping Number is an integer such that all of its adjacent digits have an absolute difference of exactly 1. For example, 321 is a Stepping Number while 421 is not.

Given two integers low and high, find and return a sorted list of all the Stepping Numbers in the range [low, high] inclusive.



Example 1:

Input: low = 0, high = 21
Output: [0,1,2,3,4,5,6,7,8,9,10,12,21]


Constraints:

0 <= low <= high <= 2 * 10^9

 */
package bfsdfs.medium;

import java.util.*;


public class SteppingNumbers {

    List<Integer> list = new ArrayList<>();

    public ArrayList<Integer> countSteppingNumbers(int A, int B) {
        ArrayList<Integer> res = new ArrayList<>();
        if (A > B) return res;

        Queue<Long> q = new ArrayDeque<>();
        for (long i = 1; i <= 9; i++) q.add(i);

        if (A == 0) res.add(0);
        while (!q.isEmpty()) {
            long p = q.poll();
            if (p < B) {
                long last = p % 10;
                if (last > 0) q.add(p * 10 + last - 1);
                if (last < 9) q.add(p * 10 + last + 1);
            }
            if (p >= A && p <= B) {
                res.add((int) p);
            }
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(new SteppingNumbers().countSteppingNumbers(0,1000000000));
    }

}


/*

BFS:
start node = 0
From 0, we can move to 1 2 3 4 5 6 7 8 9 [ Add these to queue. ]
now from 1, we can move to 12 and 10
from 2, 23 and 21
from 3, 34 and 32
.
.
and so on

 */