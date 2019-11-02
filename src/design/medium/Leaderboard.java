/*

Design a Leaderboard class, which has 3 functions:

addScore(playerId, score): Update the leaderboard by adding score to the given player's score.
If there is no player with such id in the leaderboard, add him to the leaderboard with the given score.
top(K): Return the score sum of the top K players.
reset(playerId): Reset the score of the player with the given id to 0. It is guaranteed that the player
was added to the leaderboard before calling this function.
Initially, the leaderboard is empty.



Example 1:

Input:
["Leaderboard","addScore","addScore","addScore","addScore","addScore","top","reset","reset","addScore","top"]
[[],[1,73],[2,56],[3,39],[4,51],[5,4],[1],[1],[2],[2,51],[3]]
Output:
[null,null,null,null,null,null,73,null,null,null,141]

Explanation:
Leaderboard leaderboard = new Leaderboard ();
leaderboard.addScore(1,73);   // leaderboard = [[1,73]];
leaderboard.addScore(2,56);   // leaderboard = [[1,73],[2,56]];
leaderboard.addScore(3,39);   // leaderboard = [[1,73],[2,56],[3,39]];
leaderboard.addScore(4,51);   // leaderboard = [[1,73],[2,56],[3,39],[4,51]];
leaderboard.addScore(5,4);    // leaderboard = [[1,73],[2,56],[3,39],[4,51],[5,4]];
leaderboard.top(1);           // returns 73;
leaderboard.reset(1);         // leaderboard = [[2,56],[3,39],[4,51],[5,4]];
leaderboard.reset(2);         // leaderboard = [[3,39],[4,51],[5,4]];
leaderboard.addScore(2,51);   // leaderboard = [[2,51],[3,39],[4,51],[5,4]];
leaderboard.top(3);           // returns 141 = 51 + 51 + 39;


Constraints:

1 <= playerId, K <= 10000
It's guaranteed that K is less than or equal to the current number of players.
1 <= score <= 100
There will be at most 1000 function calls.

 */
package design.medium;

import java.util.*;

public class Leaderboard {

    private class Node {
        private int playerId;
        private int score;

        public Node(int playerId, int score) {
            this.playerId = playerId;
            this.score = score;
        }
    }

    PriorityQueue<Node> priorityQueue;
    Map<Integer,Node> map;

    public Leaderboard() {
        map = new HashMap<>();
        priorityQueue = new PriorityQueue<>((a,b)->b.score-a.score);
    }

    public void addScore(int playerId, int score) {
        if(!map.containsKey(playerId)) {
            Node n = new Node(playerId,score);
            map.put(playerId,n);
            priorityQueue.add(n);
        } else {
            int sc = map.get(playerId).score;
            reset(playerId);
            addScore(playerId,sc+score);
        }
    }

    public int top(int K) {
        List<Node> list = new ArrayList<>();
        int sum=0;
        for (int i=0;i<K;i++) {
            Node node = priorityQueue.poll();
            sum+=node.score;
            list.add(node);
        }
        priorityQueue.addAll(list);
        return sum;
    }

    public void reset(int playerId) {
        Node n = map.get(playerId);
        priorityQueue.remove(n);
        map.remove(playerId);
    }

    public static void main(String[] args) {
        Leaderboard leaderboard = new Leaderboard();
        leaderboard.addScore(1,13);
        leaderboard.addScore(2,93);
        leaderboard.addScore(3,84);
        leaderboard.addScore(4,6);
        leaderboard.addScore(5,89);
        leaderboard.addScore(6,31);
        leaderboard.addScore(7,7);
        leaderboard.addScore(8,1);
        leaderboard.addScore(9,98);
        leaderboard.addScore(10,42);
        System.out.println(leaderboard.top(5));
        leaderboard.reset(1);
        leaderboard.reset(2);
        leaderboard.addScore(3,76);
        leaderboard.addScore(4,68);
        System.out.println(leaderboard.top(1));
    }

}
