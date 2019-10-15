/*

On an 8x8 chessboard, there can be multiple Black Queens and one White King.

Given an array of integer coordinates queens that represents the positions of the Black Queens,
and a pair of coordinates king that represent the position of the White King, return the coordinates of all the queens (in any order) that can attack the King.

Input: queens = [[0,1],[1,0],[4,0],[0,4],[3,3],[2,4]], king = [0,0]
Output: [[0,1],[1,0],[3,3]]
Explanation:
The queen at [0,1] can attack the king cause they're in the same row.
The queen at [1,0] can attack the king cause they're in the same column.
The queen at [3,3] can attack the king cause they're in the same diagnal.
The queen at [0,4] can't attack the king cause it's blocked by the queen at [0,1].
The queen at [4,0] can't attack the king cause it's blocked by the queen at [1,0].
The queen at [2,4] can't attack the king cause it's not in the same row/column/diagnal as the king.


Input: queens = [[0,0],[1,1],[2,2],[3,4],[3,5],[4,4],[4,5]], king = [3,3]
Output: [[2,2],[3,4],[4,4]]


Input: queens = [[5,6],[7,7],[2,1],[0,7],[1,6],[5,1],[3,7],[0,3],[4,0],[1,2],[6,3],[5,0],[0,4],
[2,2],[1,1],[6,4],[5,4],[0,0],[2,6],[4,5],[5,2],[1,4],[7,5],[2,3],[0,5],[4,2],[1,0],[2,7],[0,1],[4,6],[6,1],[0,6],[4,3],[1,7]], king = [3,4]
Output: [[2,3],[1,4],[1,6],[3,7],[4,3],[5,4],[4,5]]

Constraints:

1 <= queens.length <= 63
queens[0].length == 2
0 <= queens[i][j] < 8
king.length == 2
0 <= king[0], king[1] < 8
At most one piece is allowed in a cell.


 */
package arrays.medium;

import java.util.*;

public class QueensThatCanAttackKing {

    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {

        List<List<Integer>> result = new ArrayList<>();

        Set<String> stringSet = new HashSet<>();
        for (int[] q : queens) {
            stringSet.add(q[0]+"_"+q[1]);
        }

        int startX = king[0];
        int startY = king[1];

        //right
        for (int i=startX+1;i<8;i++) {
            if(stringSet.contains(i+"_"+startY)) {
                addToResult(i,startY,result);
                break;
            }
        }

        //left
        for (int i=startX-1;i>=0;i--) {
            if(stringSet.contains(i+"_"+startY)) {
                addToResult(i,startY,result);
                break;
            }
        }

        //down
        for (int i=startY+1;i<8;i++) {
            if(stringSet.contains(startX+"_"+i)) {
                addToResult(startX,i,result);
                break;
            }
        }

        //top
        for (int i=startY-1;i>=0;i--) {
            if(stringSet.contains(startX+"_"+i)) {
                addToResult(startX,i,result);
                break;
            }
        }

        //right down diagonal
        for (int i=startX+1,j=startY+1;i<64 && j<64; i++,j++) {
            if(stringSet.contains(i+"_"+j)) {
                addToResult(i,j,result);
                break;
            }
        }


        //left top diagonal
        for (int i=startX-1,j=startY-1;i>=0 && j>=0; i--,j--) {
            if(stringSet.contains(i+"_"+j)) {
                addToResult(i,j,result);
                break;
            }
        }

        // bottom right diagonal
        for (int i=startX+1,j=startY-1;i<64 && j>=0; i++,j--) {
            if(stringSet.contains(i+"_"+j)) {
                addToResult(i,j,result);
                break;
            }
        }

        // left bottom diagonal
        for (int i=startX-1,j=startY+1;i>=0 && j<64; i--,j++) {
            if(stringSet.contains(i+"_"+j)) {
                addToResult(i,j,result);
                break;
            }
        }

        return result;


    }

    private void addToResult(int x,int y,List<List<Integer>> result) {
        List<Integer> list = new ArrayList<>();
        list.add(x);
        list.add(y);
        result.add(list);
    }

}

/*

public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[][] seen = new boolean[8][8];
        for (int[] queen : queens) seen[queen[0]][queen[1]] = true;
        int[] dirs = {-1, 0, 1};
        for (int dx : dirs) {
            for (int dy : dirs) {
                if (dx == 0 && dy == 0) continue;
                int x = king[0], y = king[1];
                while (x + dx >= 0 && x + dx < 8 && y + dy >= 0 && y + dy < 8) {
                    x += dx;
                    y += dy;
                    if (seen[x][y]) {
                        result.add(Arrays.asList(x, y));
                        break;
                    }
                }
            }
        }
        return result;
    }

 */