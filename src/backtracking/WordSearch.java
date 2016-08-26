package backtracking;/*

Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally
or vertically neighboring. The same letter cell may not be used more than once.

For example,
Given board =

[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.


 */

/**
 * Created by poorvank on 20/08/16.
 */
public class WordSearch {

    private final int[] xMove = {-1,0,1,0};
    private final int[] yMove = {0,1,0,-1};

    public boolean exist(char[][] board, String word) {
        int row = board.length;
        int col = board[0].length;
        boolean[][] visited = new boolean[row][col];

        for (int i=0;i<row;i++) {
            for (int j=0;j<col;j++) {
                if(board[i][j] == word.charAt(0)) {
                    if(existUtil(board,word,row,col,i,j,new StringBuilder(),visited,0)) {
                        return true;
                    }
                }
            }
        }

        return false;

    }

    private boolean existUtil(char[][] board,String word,int row,int col,int x,int y,StringBuilder sb,boolean[][] visited,int index) {

        sb.append(board[x][y]);
        if(sb.toString().equals(word)) {
            return true;
        }
        if(index==word.length()-1) {
            return false;
        }

        visited[x][y] = true;
        int nextIndex = index+1;

        for (int i=0;i<4;i++) {
            int nextX = xMove[i] + x;
            int nextY = yMove[i] + y;

            if(isSafe(nextX,nextY,row,col,visited,word,board,nextIndex)) {
                if(existUtil(board,word,row,col,nextX,nextY,sb,visited,nextIndex)) {
                    return true;
                }
            }

        }
        //Mark visited false while backtracking
        visited[x][y] = false;
        //Deleted the character while backtracking
        sb.deleteCharAt(sb.length()-1);
        return false;

    }

    private static boolean isSafe(int x,int y,int row,int col,boolean[][] visited,String word,char[][] board,int index) {
        return (x>=0 && x<row && y>=0 && y<col && !visited[x][y] && board[x][y]==word.charAt(index));
    }


    public static void main(String[] args) {

        char[][] board = new char[][]{
                {'C','A','A'},
                {'A','A','A'},
                {'B','C','D'}
        };

        String word = "AAB";
        WordSearch ws = new WordSearch();
        System.out.println(ws.exist(board,word));

    }

}

/*

A simple DFS Approach is followed

 */