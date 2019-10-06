/*

There are some chips, and the i-th chip is at position chips[i].

You can perform any of the two following types of moves any number of times (possibly zero) on any chip:

Move the i-th chip by 2 units to the left or to the right with a cost of 0.
Move the i-th chip by 1 unit to the left or to the right with a cost of 1.
There can be two or more chips at the same position initially.

Return the minimum cost needed to move all the chips to the same position (any position).



Example 1:

Input: chips = [1,2,3]
Output: 1
Explanation: Second chip will be moved to positon 3 with cost 1. First chip will be moved to position 3 with cost 0. Total cost is 1.
Example 2:

Input: chips = [2,2,2,3,3]
Output: 2
Explanation: Both fourth and fifth chip will be moved to position two with cost 1. Total minimum cost will be 2.


Constraints:

1 <= chips.length <= 100
1 <= chips[i] <= 10^9

 */
package arrays.easy;

public class PlayWithChips {

    public int minCostToMoveChips(int[] chips) {
        int odd = 0, even = 0;
        for(int chip : chips)
            if(chip % 2 == 0)
                ++even;
            else
                ++odd;
        return Math.min(odd,even);
    }

}

/*

There's no cost to move numbers by a difference of 2. Hence, we can move all even numbers to 0 and all odd numbers to 1 without any cost.
Now, finally, we need to bring all numbers to zero or 1. Hence, we find out whether moving the odd numbers or the even numbers would incur a greater cost.
We return the minimum of the two

 */