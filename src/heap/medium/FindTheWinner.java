/*

There are n friends that are playing a game. The friends are sitting in a circle and are
numbered from 1 to n in clockwise order. More formally, moving clockwise from the ith friend brings
you to the (i+1)th friend for 1 <= i < n, and moving clockwise from the nth friend brings you to the 1st friend.

The rules of the game are as follows:

Start at the 1st friend.
Count the next k friends in the clockwise direction including the friend you started at.
The counting wraps around the circle and may count some friends more than once.
The last friend you counted leaves the circle and loses the game.
If there is still more than one friend in the circle, go back to step 2 starting from the
friend immediately clockwise of the friend who just lost and repeat.
Else, the last friend in the circle wins the game.
Given the number of friends, n, and an integer k, return the winner of the game.

Input: n = 5, k = 2
Output: 3
Explanation: Here are the steps of the game:
1) Start at friend 1.
2) Count 2 friends clockwise, which are friends 1 and 2.
3) Friend 2 leaves the circle. Next start is friend 3.
4) Count 2 friends clockwise, which are friends 3 and 4.
5) Friend 4 leaves the circle. Next start is friend 5.
6) Count 2 friends clockwise, which are friends 5 and 1.
7) Friend 1 leaves the circle. Next start is friend 3.
8) Count 2 friends clockwise, which are friends 3 and 5.
9) Friend 5 leaves the circle. Only friend 3 is left, so they are the winner.
Example 2:

Input: n = 6, k = 5
Output: 1
Explanation: The friends leave in this order: 5, 4, 6, 2, 3. The winner is friend 1.


Constraints:

1 <= k <= n <= 500


Follow up:

Could you solve this problem in linear time with constant space?

 */
package heap.medium;

import java.util.LinkedList;
import java.util.Queue;

public class FindTheWinner {

    public int findTheWinner(int n, int k) {
        Queue<Integer> friends = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            friends.add(i);
        }

        int killed = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k-1; j++) {
                friends.add(friends.poll());
            }
            killed = friends.poll();
        }
        return killed;
    }

}

/*
=== Initialization ===
1 => 2 => 3 => 4 => 5
=== First round ===
1) we put the 1 to the end :
2 => 3 => 4 => 5 => 1
2) Second step, meaning current step = k, we just poll the node :
3 => 4 => 5 => 1
=== Second round ===
1) We put 3 at the end
4 => 5 => 1 => 3
2) We poll 4
5 => 1 => 3
=== Third round ===
1) We put 5 at the end
1 => 3 => 5
2) We poll 1
3 => 5
=== Forth round ===
1) We put 3 at the end
5 => 3
2) We poll 5
3

Time complexity is O(n × k) as we count k times for the n turns.
Space complexity is O(k) as we create k nodes for playing the game


Better sol: (Josephus Problem)

 public int findTheWinner(int n, int k) {
        return helper(n,k)+1;
    }
    public int helper(int n,int k){
     if(n==1)
        return 0;
    else
        return (helper(n-1,k)+k)%n;
    }
 */