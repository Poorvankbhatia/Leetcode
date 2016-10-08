/*
Determine if a Sudoku is valid

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.

 */

package hashing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by poorvank on 07/10/16.
 */
public class ValidSudoku {

    public boolean isValidSudoku(char[][] board) {


        for (char[] aBoard : board) {
            HashMap<Character, Integer> map = new HashMap<>();
            for (int j = 0; j < board[0].length; j++) {
                if (aBoard[j] == '.') {
                    continue;
                }
                if (map.containsKey(aBoard[j])) {
                    return false;
                } else {
                    map.put(aBoard[j], 1);
                }
            }
        }

        for (int j=0;j<board[0].length;j++) {
            HashMap<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < board.length; i++) {
                if (board[i][j] == '.') {
                    continue;
                }
                if (map.containsKey(board[i][j])) {
                    return false;
                } else {
                    map.put(board[i][j], 1);
                }
            }
        }

        //Imp
        for (int i=0;i<board.length;i++) {
            for (int j=0;j<board[0].length;j++) {
                if(!checkBox(i-(i%3),j-(j%3),board)) {
                    return false;
                }
            }
        }

        return true;

    }

    private boolean checkBox(int row,int col,char[][] board) {
        Set<Character> set = new HashSet<>();
        for (int i=0;i<3;i++) {
            for (int j=0;j<3;j++) {
                if(set.contains(board[i+row][j+col]) && board[i+row][j+col]!='.') {
                    return false;
                }
                set.add(board[i+row][j+col]);
            }
        }
        return true;
    }
}

