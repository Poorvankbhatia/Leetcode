/*

On an N x N board, the numbers from 1 to N*N are written boustrophedonically starting from the bottom left of the board, and alternating
direction each row.  For example, for a 6 x 6 board, the numbers are written as follows:

36 35 34 33 32 31
25 26 27 28 29 30
24 23 22 21 20 19
13 14 15 16 17 18
12 11 10 09 08 07
01 02 03 04 05 06
You start on square 1 of the board (which is always in the last row and first column).  Each move, starting from square x, consists of the following:

You choose a destination square S with number x+1, x+2, x+3, x+4, x+5, or x+6, provided this number is <= N*N.
If S has a snake or ladder, you move to the destination of that snake or ladder.  Otherwise, you move to S.
A board square on row r and column c has a "snake or ladder" if board[r][c] != -1.  The destination of that snake or ladder is board[r][c].

Note that you only take a snake or ladder at most once per move: if the destination to a snake or ladder is the start of another snake or ladder,
you do not continue moving.

Return the least number of moves required to reach square N*N.  If it is not possible, return -1.

Example 1:

Input: [
[-1,-1,-1,-1,-1,-1],
[-1,-1,-1,-1,-1,-1],
[-1,-1,-1,-1,-1,-1],
[-1,35,-1,-1,13,-1],
[-1,-1,-1,-1,-1,-1],
[-1,15,-1,-1,-1,-1]]
Output: 4
Explanation:
At the beginning, you start at square 1 [at row 5, column 0].
You decide to move to square 2, and must take the ladder to square 15.
You then decide to move to square 17 (row 3, column 5), and must take the snake to square 13.
You then decide to move to square 14, and must take the ladder to square 35.
You then decide to move to square 36, ending the game.
It can be shown that you need at least 4 moves to reach the N*N-th square, so the answer is 4.
Note:

2 <= board.length = board[0].length <= 20
board[i][j] is between 1 and N*N or is equal to -1.
The board square with number 1 has no snake or ladder.
The board square with number N*N has no snake or ladder.

 */
package bfsdfs.medium;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Created by poorvank.b on 23/09/18.
 */
public class SnakeAndLadders {

    private class Cell {
        private int id;
        private int minMovesFromStart;

        Cell(int id) {
            this.id = id;
        }

        Cell() {
        }
    }

    public int snakesAndLadders(int[][] board) {

        Queue<Cell> queue = new LinkedList<>();
        queue.add(new Cell(1));
        int n = board.length*board.length;
        boolean[] visited = new boolean[n+1];
        visited[1]=true;

        Map<Integer,Integer> map = getCellValueMap(board);
        while (!queue.isEmpty()) {

            int v = queue.peek().id;
            if(v==n) {
                break;
            }

            Cell current = queue.poll();

            for (int j=(v+1);j<=(v+6) && j<=n;j++) {
                if(!visited[j]) {
                    visited[j]=true;

                    Cell cell = new Cell();
                    cell.minMovesFromStart = current.minMovesFromStart+1;
                    if(map.containsKey(j)) {
                        cell.id=map.get(j);
                    } else {
                        cell.id=j;
                    }
                    queue.add(cell);
                }
            }

        }

        return !queue.isEmpty()?queue.peek().minMovesFromStart:-1;

    }

    // Get Snake and ladder end positions if any.
    private Map<Integer,Integer> getCellValueMap(int[][] board) {
       boolean flag = true;
       Map<Integer,Integer> map=new HashMap<>();
       int val=1;
       for (int i=board.length-1;i>=0;i--) {
           if(flag) {
               for (int j=0;j<=board.length-1;j++) {
                   if(board[i][j]!=-1) {
                       map.put(val,board[i][j]);
                   }
                   val++;
               }
           } else {
               for (int j=board.length-1;j>=0;j--) {
                   if(board[i][j]!=-1) {
                       map.put(val,board[i][j]);
                   }
                   val++;
               }
           }
           flag=!flag;
       }
        return map;
    }

}
