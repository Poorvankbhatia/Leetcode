/*

On a broken calculator that has a number showing on its display, we can perform two operations:

Double: Multiply the number on the display by 2, or;
Decrement: Subtract 1 from the number on the display.
Initially, the calculator is displaying the number X.

Return the minimum number of operations needed to display the number Y.



Example 1:

Input: X = 2, Y = 3
Output: 2
Explanation: Use double operation and then decrement operation {2 -> 4 -> 3}.
Example 2:

Input: X = 5, Y = 8
Output: 2
Explanation: Use decrement and then double {5 -> 4 -> 8}.
Example 3:

Input: X = 3, Y = 10
Output: 3
Explanation:  Use double, decrement and double {3 -> 6 -> 5 -> 10}.
Example 4:

Input: X = 1024, Y = 1
Output: 1023
Explanation: Use decrement operations 1023 times.


Note:

1 <= X <= 10^9
1 <= Y <= 10^9

 */
package math.medium;

public class BrokenCalculator {

    public int brokenCalc(int X, int Y) {
        int count=0;
        while(Y>X) {
            if(Y%2==1) {
                Y+=1;
                count++;
            } else {
                Y/=2;
                count++;
            }
        }
        return (X-Y)+count;
    }

}

/*
(1) when X > Y... multiple by 2 will only bring us far away from target...
 *     so each step, we can only subtract 1 from X to get the target value...
 *     thus we just need to return X - Y;
 * (2) when Y > X... we can think reversely...
 *     we can achieve Y from the previous number by either of two cases
 *     (a) pre - 1 = Y;
 *     (b) pre * 2 = Y...
 *     which step to take is dependent on if Y is even or odd...
 *     if (Y is even), pre = Y / 2;
 *     if (Y is odd), pre = Y + 1...
 *     then we reset Y as pre...
 *     do the same thing, until Y <= X... which is the first case...
 */

/*
this problem can be treated as finding the shortest path in a graph, and each node will have branching factor of two.
When we search from X to Y.. then one branch will lead to X - 1, the other will lead to 2 * X..
when we search from Y to X, one branch will lead to Y + 1, the other will lead to Y / 2...
The reason we start from Y to X is that when we searching from this direction, the parity of the
node can guide the search.. for example when Y is odd.. the Y/2 branch is not valid
(since a odd number cannot be achieve directly by multiply any number by 2)... and when Y is even,
the branch Y + 1 will not contribute to the shortest path, because we know Y > X,
so Y + 1 will only bring us even far away from the target, so we need to follow the Y/2 branch.
When we search from X to Y... we cannot prune the search using the similar property of the node...
we will need to do BFS to search all branches until finding the target...
which will easily get TLE...
 */