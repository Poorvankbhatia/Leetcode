/*

On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.

A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.

The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].

Given a puzzle board, return the least number of moves required so that the state of the board is solved.
If it is impossible for the state of the board to be solved, return -1.

Examples:

Input: board = [[1,2,3],[4,0,5]]
Output: 1
Explanation: Swap the 0 and the 5 in one move.
Input: board = [[1,2,3],[5,4,0]]
Output: -1
Explanation: No number of moves will make the board solved.
Input: board = [[4,1,2],[5,0,3]]
Output: 5
Explanation: 5 is the smallest number of moves that solves the board.
An example path:
After move 0: [[4,1,2],[5,0,3]]
After move 1: [[4,1,2],[0,5,3]]
After move 2: [[0,1,2],[4,5,3]]
After move 3: [[1,0,2],[4,5,3]]
After move 4: [[1,2,0],[4,5,3]]
After move 5: [[1,2,3],[4,5,0]]
Input: board = [[3,2,4],[1,5,0]]
Output: 14
Note:

board will be a 2 x 3 array as described above.
board[i][j] will be a permutation of [0, 1, 2, 3, 4, 5].

 */
package dyanamicprogramming.hard;

import java.util.*;

/**
 * Created by poorvank.b on 28/01/18.
 */
public class SlidingPuzzle {

    private class Node {
        int i;
        int j;
        int step;
        String boardStr;
        int[][] board;

        public Node(int i, int j, int step, String boardStr,int[][] board) {
            this.i = i;
            this.j = j;
            this.step = step;
            this.boardStr = boardStr;
            this.board = board;
        }
    }

    int[][] directions = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};

    public int slidingPuzzle(int[][] board) {

        if(board==null || board.length==0) {
            return -1;
        }

        int startI=0;
        int startJ=0;

        for (int i=0;i<board.length;i++) {
            for (int j=0;j<board[0].length;j++) {
                if(board[i][j]==0) {
                    startI = i;
                    startJ = j;
                    break;
                }
            }
        }



        Queue<Node> queue = new LinkedList<>();

        queue.add(new Node(startI,startJ,0,getBoardString(board),board));

        Set<String> set = new HashSet<>();
        set.add(getBoardString(board));

        String target = getBoardString(new int[][]{{1,2,3}, {4,5,0}});

        while (!queue.isEmpty()) {
            Node pop = queue.remove();
           // System.out.println("evaluating - " + pop.boardStr);
            if(pop.boardStr.equals(target)) {
                return pop.step;
            }

            for (int[] dir : directions) {
                int nextI = dir[0]+pop.i;
                int nextJ = dir[1]+pop.j;

                if(!isSafe(nextI,nextJ)) {
                    continue;
                }

                int[][] newBoard = new int[2][3];

                int t = 0;
                for (int[] row: pop.board)
                    newBoard[t++] = row.clone();

                newBoard[pop.i][pop.j]=newBoard[nextI][nextJ];
                newBoard[nextI][nextJ]=0;

                String newBoardString = getBoardString(newBoard);

                Node newNode = new Node(nextI,nextJ,pop.step+1,newBoardString,newBoard);
                if(set.contains(newBoardString)) {
                    //System.out.println("Already seen - " + newBoardString);
                    continue;
                }
                queue.add(newNode);
                set.add(newBoardString);

            }

        }


        return -1;

    }

    private boolean isSafe(int i,int j) {
        return i>=0 && i<2 && j>=0 && j<3;
    }


    private String getBoardString(int[][] board) {

        StringBuilder sb = new StringBuilder();

        for (int[] aBoard : board) {
            for (int j = 0; j < board[0].length; j++) {
                sb.append(aBoard[j]);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {4,1,2},
                {5,0,3}
        };

        System.out.println(new SlidingPuzzle().slidingPuzzle(arr));

    }

}
/*

Simple BFS.

 */
