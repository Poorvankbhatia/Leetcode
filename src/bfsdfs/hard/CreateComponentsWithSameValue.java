/**
 There is an undirected tree with n nodes labeled from 0 to n - 1.

 You are given a 0-indexed integer array nums of length n where nums[i] represents the value of the ith node.
 You are also given a 2D integer array edges of length n - 1 where edges[i] = [ai, bi] indicates that
 there is an edge between nodes ai and bi in the tree.

 You are allowed to delete some edges, splitting the tree into multiple connected components.
 Let the value of a component be the sum of all nums[i] for which node i is in the component.

 Return the maximum number of edges you can delete, such that every connected component in the tree has the same value.



 Input: nums = [6,2,2,2,6], edges = [[0,1],[1,2],[1,3],[3,4]]
 Output: 2
 Explanation: The above figure shows how we can delete the edges [0,1] and [3,4].
 The created components are nodes [0], [1,2,3] and [4]. The sum of the values in each component equals 6.
 It can be proven that no better deletion exists, so the answer is 2.
 Example 2:

 Input: nums = [2], edges = []
 Output: 0
 Explanation: There are no edges to be deleted.
 */
package bfsdfs.hard;

import java.util.*;

public class CreateComponentsWithSameValue {

    public int componentValue(int[] nums, int[][] edges) {
        List<List<Integer>> list = new ArrayList<>(nums.length);
        int[] degree = new int[nums.length];
        int sum = 0;
        for (int num : nums) {
            list.add(new ArrayList<>());
            sum += num;
        }
        for (int[] e : edges) {
            degree[e[0]]++;
            degree[e[1]]++;
            list.get(e[0]).add(e[1]);
            list.get(e[1]).add(e[0]);
        }
        for (int i = nums.length; i > 1; i--) {
            if (sum % i == 0) {
                if (bfs(sum / i, nums.clone(), degree.clone(), list)) {
                    // edges to be deleted == number of partition - 1
                    return i - 1;
                }
            }
        }
        return 0;
    }


    private boolean bfs(int target, int[] nums, int[] degree, List<List<Integer>> al) {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < degree.length; i++) {
            if (degree[i] == 1) {
                q.add(i);
            }
        }
        while (!q.isEmpty()) {
            int curr = q.poll();
            for (int next : al.get(curr)) {
                if (nums[curr] > target) {
                    return false;
                }
                nums[next] += nums[curr] < target ? nums[curr] : 0;
                degree[next]--;
                if (degree[next] == 1) {
                    q.add(next);
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2};
        int[][] edges = new int[][]{
        };
        System.out.println(new CreateComponentsWithSameValue().componentValue(nums, edges));
    }

}

/*

If we split the tree into i components, the target value of each component is sum / i.

Note that we cannot split into i components if sum % i != 0.

For each target value, we can unambiguously discover all components starting from leaves.
 This works because we have a tree, not a graph.

When processing from leaves, we track the value of the current component:

If the value is greater than the target, we cannot split the tree.
If the value is less, we add it to the parent.
There is no more than one parent as we go from a leaf.
If the value is equal, we do not add it to the parent.
It will start a new component from that parent.
Thus, the solution is to try to sum / n, sum / (n - 1), ..., sum / 2 values,
and use BFS to check if we can do the split.

Complexity Analysis

Time: O(n * log nm), where m is max(nums).
BFS is O(n), and we do it for each divisor of sum.
The maximum sum is nm, so the maximum number of divisors is log2 nm.
Memory: O(n)

 */