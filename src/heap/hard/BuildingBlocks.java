/*

You are given a list of blocks, where blocks[i] = t means that the i-th block needs t units of time to be built. A block can only be built by exactly one worker.

A worker can either split into two workers (number of workers increases by one) or build a block then go home. Both decisions cost some time.

The time cost of spliting one worker into two workers is given as an integer split. Note that if two workers split at the same time,
they split in parallel so the cost would be split.

Output the minimum time needed to build all blocks.

Initially, there is only one worker.



Example 1:

Input: blocks = [1], split = 1
Output: 1
Explanation: We use 1 worker to build 1 block in 1 time unit.
Example 2:

Input: blocks = [1,2], split = 5
Output: 7
Explanation: We split the worker into 2 workers in 5 time units then assign each of them to a block so the cost is 5 + max(1, 2) = 7.
Example 3:

Input: blocks = [1,2,3], split = 1
Output: 4
Explanation: Split 1 worker into 2, then assign the first worker to the last block and split the second worker into 2.
Then, use the two unassigned workers to build the first two blocks.
The cost is 1 + max(3, 1 + max(1, 2)) = 4.


Constraints:

1 <= blocks.length <= 1000
1 <= blocks[i] <= 10^5
1 <= split <= 100

 */
package heap.hard;

import java.util.PriorityQueue;

public class BuildingBlocks {

    public int minBuildTime(int[] blocks, int split) {
        PriorityQueue <Integer> pq = new PriorityQueue();
        for (int block : blocks) {
            pq.offer(block);
        }

        while(pq.size() > 1) {
            int block1 = pq.poll();
            int block2 = pq.poll();
            int ans = split + Math.max(block1, block2);
            pq.offer(ans);
        }
        return pq.poll();
    }

}


/*

We can model this entire question as a binary tree that we need to construct with a minimum max depth cost.
Each of the blocks is a leaf node, with a cost of its face value. And then each inner node will be of cost split.
nodes that are sitting at the same level represent work that is done in parallel. We know there will be len(blocks) - 1 of these inner nodes,
so the question now is how can we construct the tree such that it has the minimum depth.

For example, a possible (not optimal) tree for the data set
[1, 2, 4, 7, 10] with split cost 3 is:
(Sorry for ascii art, 2 AM is too late at night to do this properly :) )

........3
...../....\
.....3.....\
../.....\....\
 3.......3....\
/..\..../..\...\
1...2...4...7...10

This tree has a maximum depth of 16 (3 -> 3 -> 3 -> 7).

So, how can we optimise the construction of this tree? Huffman's algorithm!

https://www.geeksforgeeks.org/huffman-coding-greedy-algo-3/

Instead of actually building the whole tree, we can just keep track of the nodes the huffman algorithm
still needs to consider, as the maximum depth below them. So put all the leaf node (blocks) onto a priority queue,
and then repeatedly take the 2 smallest off and add split onto the biggest of the 2 (this is Huffman's algorithm, it's greedy)
and put the result back onto the priority queue.

Once there is only one left, this is the depth of the root node, and we return it.

The question describes a situation where we are given a list of blocks and each block needs t amount of time to be built.
Initially we only have one worker and a worker can only work on single block.
At each point we have a choice whether we want to use a worker for building a block or split the worker into two workers.

Observe that eventually we will need n workers for n blocks.
The pattern here is at each point we can use one worker to split and other worker to build a block.
Look at the following two trees to get a better idea.

Tree1:
					(split)

			(split)				(3)

		(1)           (2)
Tree2:
					(split)

			(split)				(1)

		(2)           (3)
The two tree represents the steps taken at each step. So at each step we split the worker and perform a block build at the same time.

 */