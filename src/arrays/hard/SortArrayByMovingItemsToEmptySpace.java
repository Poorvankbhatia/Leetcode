/*
You are given an integer array nums of size n containing each element from 0 to n - 1 (inclusive).
Each of the elements from 1 to n - 1 represents an item, and the element 0 represents an empty space.

In one operation, you can move any item to the empty space. nums is considered to be sorted if the numbers
of all the items are in ascending order and the empty space is either at the beginning or at the end of the array.

For example, if n = 4, nums is sorted if:

nums = [0,1,2,3] or
nums = [1,2,3,0]
...and considered to be unsorted otherwise.

Return the minimum number of operations needed to sort nums.



Example 1:

Input: nums = [4,2,0,3,1]
Output: 3
Explanation:
- Move item 2 to the empty space. Now, nums = [4,0,2,3,1].
- Move item 1 to the empty space. Now, nums = [4,1,2,3,0].
- Move item 4 to the empty space. Now, nums = [0,1,2,3,4].
It can be proven that 3 is the minimum number of operations needed.
Example 2:

Input: nums = [1,2,3,4,0]
Output: 0
Explanation: nums is already sorted so return 0.
Example 3:

Input: nums = [1,0,2,4,3]
Output: 2
Explanation:
- Move item 2 to the empty space. Now, nums = [1,2,0,4,3].
- Move item 3 to the empty space. Now, nums = [1,2,3,4,0].
It can be proven that 2 is the minimum number of operations needed.


Constraints:

n == nums.length
2 <= n <= 105
0 <= nums[i] < n
All the values of nums are unique.
 */
package arrays.hard;

import java.util.ArrayList;
import java.util.Collections;

public class SortArrayByMovingItemsToEmptySpace {

    int permute(ArrayList<Integer> npos, int s)
    {
        int cnt = 0, nxt = 1, n = npos.size(), ni;
        while (nxt < n) {
            if (npos.get(0) == s * (n-1)) {
                while (npos.get(nxt) == nxt-s)
                    if (++nxt == n) return cnt;
                ni = nxt;
            } else { ni = npos.get(0) + s; }
            Collections.swap(npos, 0, ni);
            ++cnt;
        }
        return cnt;
    }

    public int sortArray(int[] nums) {
        int n = nums.length;

        var npos = new ArrayList<Integer>(Collections.nCopies(n, 0));
        for (int i = 0; i < n; ++i) npos.set(nums[i], i);
        // note that here we must pass copies of 'npos' because
        // the permutation function makes in-place operations
        return Math.min(permute(new ArrayList<>(npos), 0),
                permute(new ArrayList<>(npos), 1));
    }

}

/*

This solution employs a minimal permutation scheme (cycle sort) on two types of arrays, 0-indexed and 1-indexed.
Time complexity is linear: O(n). Space complexity is linear: O(n).

Comment. When solving this problem, we have to swap 0 with other numbers. In principle, there exist two strategies
for making permutations:

either we first choose a number we want to place correctly, and then (desperately) try to find a series of permutations
 in order to achieve that;
or we choose what's "already there", namely, the position of zero which itself is a valid number from 1 to n-1 (actually
 it can be equal 0, see below) and we get a 100% correct placement using just one permutation.
The first strategy is a mess, thus, we go with the second one which employs a minimal permutation scheme (cycle sort)
that consists in the following:

On each iteration, find the current position of 0 in nums.
This problem is designed in such a way that we can use
either the 0-based indexing of arrays (e.g., position of 0 = 2 for nums=[1,3,0,4,2]) that would eventually lead us
 to nums = [1,2,3,...,n-1,0] (i.e., terminal-zero ordering);
or the 1-based indexing of arrays (e.g., position of 0 = 3 for [1,3,0,4,2] ) that would eventually lead us to
nums = [0,1,2,3,...,n-1] (i.e., leading-zero ordering).
Next, we treat position of 0 as the number with which we permute 0.
It might so happen that, on some iteration, 0 would end up in a terminal position, i.e., either nums[0] = 0
for 1-indexed array or nums[n-1] = 0 for 0-indexed array. In this case, we should swap 0 with the lowest
number that occupies incorrect position (according to the chosen indexing). If all numbers are in correct
positions (i.e., nums was sorted with either of orderings) then we terminate.
Why this works? The proof consists of two observations...

First of all, this algorithm will eventually terminate, because (by design) it fills each position with the correct
number (in either of array indexing schemes). The key point here is that the algorithm does NOT choose a number first,
but rather a position to fill. This way, correct placement of numbers is guaranteed in a finite number of steps.
Second, the placement of each number into its correct position happens in one step (or in two steps when 0 ends up in a
terminal position, e.g., for placement of 2 : [0,1,3,2,4] -> [3,1,0,2,4] -> [3,1,2,0,4]).
The amount of swaps can't be less then that, thus, the algorithm is optimal.


=== GRAPH INTUITION ===

Intuition
Let's look at [0,1,2,3,4] sort. As long as 0 is not at the index 0, we greedly swap it with index[0].
If 0 is at the start, we look at the rest of array and create a directed graph value_to_index created from numbers
 that are not on its destinated indexes. Maybe it's hard to see (I don't know and am too lasy to proof it), but this
 graph will be composed of cycles only. For example [0,2,3,1,5,4] will look like:
2->1->3->2;5->4->5.
Now we can sort each component separately. Number of operations for each component will be size of component + 1.

Summing up, the number of operations for this sort will be number of swaps untill 0 is on index 0 + number of bad
 numbers left + numbers of conected components in graph value_to_index. (bad number - number that is not on its index)

It is easer to just count bad numbers, do the swaps, create graph and count the connected components of the graph.

Approach
Probably we can just create the graph and then count the number of components. Then the solution will be number of
bad numbers + number of connected components in graph.

Second sort
It's the same as the first one except some problems with indexes

Solution
Take the minimum of sort times for [0,1,2,3] and [1,2,3,0].

Complexity
Time complexity:
O(N)

Space complexity:
O(N)

 */