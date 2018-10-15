/*

On a horizontal number line, we have gas stations at positions stations[0], stations[1], ..., stations[N-1], where N = stations.length.

Now, we add K more gas stations so that D, the maximum distance between adjacent gas stations, is minimized.

Return the smallest possible value of D.

Example:

Input: stations = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], K = 9
Output: 0.500000
Note:

stations.length will be an integer in range [10, 2000].
stations[i] will be an integer in range [0, 10^8].
K will be an integer in range [1, 10^6].
Answers within 10^-6 of the true value will be accepted as correct.

 */


package binarysearch.hard;


/**
 * Created by poorvank.b on 31/07/18.
 */
public class MinimizeMaxDistance {

    class Solution {
        public double minmaxGasDist(int[] stations, int K) {
            double l = 0;
            double r = 1e8;
            while(r - l > 1e-6){
                double m = l + (r-l)/2.0;
                if(possible(stations, K, m)){
                    r = m;
                }else{
                    l = m;
                }
            }

            return l;
        }

        private boolean possible(int [] stations, int K, double d){
            int count = 0;
            for(int i = 0; i<stations.length-1; i++){
                count += (int)((stations[i+1] - stations[i])/d);
            }

            return count <= K;
        }
    }

}

/*

The basic idea for the trial and error algorithm is actually very simple and summarized below:

Step 1: Construct a candidate solution.
Step 2: Verify if it meets our requirements.
Step 3: If it does, accept the solution; else discard it and repeat from Step 1.

However, to make this algorithm work efficiently, the following two conditions need to be true:

Condition 1: We have an efficient verification algorithm in Step 2;
Condition 2: The search space formed by all candidate solutions is small or we have efficient ways to search this space if it is large.

The first condition ensures that each verification operation can be done quickly while the second condition limits the total number of
such operations that need to be done. The two combined will guarantee that we have an efficient trial and error algorithm (which also means
if any of them cannot be satisfied, you should probably not even consider this algorithm).

Now let's look at this problem: Minimize Max Distance to Gas Station, and see how we can apply the above trial and error algorithm.

I -- Construct a candidate solution

To construct a candidate solution, we need to understand first what the desired solution is. The problem description requires we output
the minimum value of the maximum distance between adjacent gas stations. This value is nothing more than a non-negative double value,
since the distance between any adjacent stations cannot be negative and in general it should be of double type. Therefore our candidate
solution should also be a non-negative double value.

II -- Search space formed by all the candidate solutions

Let max be the maximum value of the distances between any adjacent stations without adding those additional stations, then our desired
solution should lie within the range [0, max]. This is because adding extra stations (and placing them at proper positions) can only
reduce the maximum distance between any adjacent stations. Therefore our search space will be [0, max] (or the upper bound should be at least max).

III -- Verify a given candidate solution

This is the key part of this trial and error algorithm. So given a candidate double value d, how do we determine if it can be the
minimum value of the maximum distance between any adjacent stations after adding K extra stations? The answer is by counting.

If d can be the minimum value of the maximum distance between any adjacent stations after adding K extra stations, then the
following two conditions should be true at the same time:

The total number of stations we can add between all adjacent stations cannot go beyond K. In other words, assume we added cnt_i
stations in between the pair of adjacent stations (i, i+1), then we should have sum(cnt_i) <= K.

For each pair of adjacent stations (i, i+1), the minimum value of the maximum distance between adjacent stations after adding cnt_i
additional stations cannot go beyond d. If the original distance between station i and i+1 is d_i = stations[i+1] - stations[i],
then after adding cnt_i additional stations, the minimum value of maximum distance between any adjacent stations we can obtain is
d_i / (cnt_i + 1), which is achieved by placing those stations evenly in between stations i and i+1.
Therefore we require: d_i / (cnt_i + 1) <= d, which is equivalent to cnt_i >= (d_i/d - 1).

So you can see that the two conditions are actually "contradictory" to each other: to meet condition 1, we want cnt_i to be
as small as possible; but to meet condition 2, we'd like it to be as large as possible. So in practice, we can alway choose
the set of smallest cnt_i's that satisfy the second condition and double check if they also meet the first condition.
If they do, then d will set an upper limit on the final minimum value obtainable; otherwise d will set a lower limit on this minimum value.

This verification algorithm runs at O(n), where n is the length of the stations array. This is acceptable if we can walk
the search space very efficiently (which can be done at the order of O(log(max/step)), with step = 10^-6). In particular,
this is much faster than the straightforward O(Klogn) solution where we add the stations one by one in a greedy manner
(i.e., always reduce the current maximum distance first), given that K could be orders of magnitude larger than n
(note this greedy algorithm can be optimized to run at O(nlogn), see wannacry89's post here).

IV -- How to walk the search space efficiently

Up to this point, we know the search space, know how to construct the candidate solution and how to verify it by counting,
we still need one last piece for the puzzle: how to walk the search space.

Of course we can do the naive linear walk by trying each double value from 0 up to max at a step of 10^-6 and choose
the first double value d such that sum(d_i/d - 1) <= K. The time complexity will be O(n * max/step). However,
given that max/step can be much larger than K, this algorithm could end up being much worse than the aforementioned O(Klogn) solution.

The key observation here is that the candidate solutions are ordered naturally in ascending order, so a binary search
is possible. Another fact is the non-decreasing property of the sum function: give two double values d1 and d2 such
that d1 < d2, then sum(d_i/d1 - 1) >= sum(d_i/d2 - 1). So here is what a binary walk of the search space may look like:

Let [l, r] be the current search space that is initialized as l = 0, r = max.

As long as r - l >= 10^-6, compute the middle point d = (l + r) / 2 and evaluate sum(d_i/d - 1).

If sum(d_i/d - 1) <= K, we throw away the right half of current search space by setting r = d; else we throw away the
left half by setting l = d. Then go to step 2.

V -- Putting everything together, aka, solutions

Despite the above lengthy analyses, the final solution is much simpler to write once you understand it.
Here is the Java program for the trial and error algorithm. The time complexity is O(n*log(max/step)) and space complexity is O(1)

Do READ: https://leetcode.com/problems/find-k-th-smallest-pair-distance/discuss/109082/approach-the-problem-using-the-trial-and-error-algorithm

 */
