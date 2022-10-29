/*
A certain bug's home is on the x-axis at position x. Help them get there from position 0.

The bug jumps according to the following rules:

It can jump exactly a positions forward (to the right).
It can jump exactly b positions backward (to the left).
It cannot jump backward twice in a row.
It cannot jump to any forbidden positions.
The bug may jump forward beyond its home, but it cannot jump to positions numbered with negative integers.

Given an array of integers forbidden, where forbidden[i] means that the bug cannot jump to the position forbidden[i],
and integers a, b, and x, return the minimum number of jumps needed for the bug to reach its home.
If there is no possible sequence of jumps that lands the bug on position x, return -1.



Example 1:

Input: forbidden = [14,4,18,1,15], a = 3, b = 15, x = 9
Output: 3
Explanation: 3 jumps forward (0 -> 3 -> 6 -> 9) will get the bug home.
Example 2:

Input: forbidden = [8,3,16,6,12,20], a = 15, b = 13, x = 11
Output: -1
Example 3:

Input: forbidden = [1,6,2,14,5,17,4], a = 16, b = 9, x = 7
Output: 2
Explanation: One jump forward (0 -> 16) then one jump backward (16 -> 7) will get the bug home.


Constraints:

1 <= forbidden.length <= 1000
1 <= a, b, forbidden[i] <= 2000
0 <= x <= 2000
All the elements in forbidden are distinct.
Position x is not forbidden.
 */
package bfsdfs.medium;

import java.util.*;
import java.util.stream.Collectors;

public class MinimumJumpsToReachHome {

    private class Pair {
        private int val;
        private boolean isForward;

        public Pair(int val, boolean isForward) {
            this.val = val;
            this.isForward = isForward;
        }
    }

    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        Set<Integer> set = Arrays.stream(forbidden).boxed().collect(Collectors.toSet());
        Set<String> visited = new HashSet<>();
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(0,true));
        visited.add(0+"true");
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Pair pair = queue.poll();
                if(pair.val==x) {
                    return ans;
                }
                if(!set.contains(pair.val+a) && visited.add((pair.val+a)+"true") && pair.val+a<10000) {
                    queue.add(new Pair(pair.val+a,true));
                }
                if(pair.isForward && !set.contains(pair.val-b) && (pair.val-b)>=0 && visited.add((pair.val-b)+"false")) {
                    queue.add(new Pair(pair.val-b,false));
                }
            }
            ans++;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new MinimumJumpsToReachHome().minimumJumps(new int[]{72}
                ,29,98,80));
    }

}

/*
Here , the problem says that you cannot jump backward twice , but there can be a valid case where we can reach a
particular point from both forward and backward jump(only once), but by keeping a visited array we will be reject
one of them , so that leads to wrong answer , therefore we need to maintain direction as well .


 */