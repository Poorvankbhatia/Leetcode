/*

Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or
vertically neighboring. The same letter cell may not be used more than once in a word.

For example,
Given words = ["oath","pea","eat","rain"] and board =

[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
Return ["eat","oath"].

 */
package trie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 15/10/16.
 */
public class WordSearch2 {

    private final int[] xMove = {-1,0,1,0};
    private final int[] yMove = {0,1,0,-1};
    Trie trie;

    public List<String> findWords(char[][] board, String[] words) {

        trie = new Trie();
        for(String word: words) {
            trie.insert(word);
        }

        int row = board.length;
        int col = board[0].length;
        boolean[][] visited = new boolean[row][col];

        List<String> result = new ArrayList<>();

        for (int i=0;i<row;i++) {
            for (int j=0;j<col;j++) {
                if(trie.startsWith(Character.toString(board[i][j]))) {
                    findWordsUtil(new StringBuilder(),board,visited,row,col,i,j,result);
                }
            }
        }

        return result;

    }

    private void findWordsUtil(StringBuilder sb,char[][] board,boolean[][] visited,int row,int col,int x,int y,List<String> result) {

        sb.append(board[x][y]);

        if(trie.search(sb.toString())) {
            result.add(sb.toString());
            trie.delete(sb.toString());
        }

        visited[x][y] = true;

        for (int i=0;i<4;i++) {
            int nextX = xMove[i] + x;
            int nextY = yMove[i] + y;



            if(isSafe(nextX,nextY,row,col,visited)) {
                StringBuilder intermediate = new StringBuilder();
                intermediate.append(sb.toString()).append(board[nextX][nextY]);
                if(trie.startsWith(intermediate.toString())) {
                    findWordsUtil(sb,board,visited,row,col,nextX,nextY,result);
                }
            }

        }

        sb.deleteCharAt(sb.length()-1);
        visited[x][y] = false;

    }

    private boolean isSafe(int x,int y,int row,int col,boolean[][] visited) {
        return (x>=0 && x<row && y>=0 && y<col && !visited[x][y]);
    }

    public static void main(String[] args) {
        char[][] arr = new char[][]{
                {'n','a','o','o'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}
        };

        String[] words = new String[]{"oath","pea","eat","rain","oathf","eathk"};

        System.out.println(new WordSearch2().findWords(arr,words));
    }


}
