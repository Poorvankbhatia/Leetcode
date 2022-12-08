/*

You are given an m x n matrix board, representing the current state of a crossword puzzle.
The crossword contains lowercase English letters (from solved words), ' '
to represent any empty cells, and '#' to represent any blocked cells.

A word can be placed horizontally (left to right or right to left) or vertically (top to bottom or bottom to top)
in the board if:

It does not occupy a cell containing the character '#'.
The cell each letter is placed in must either be ' ' (empty) or match the letter already on the board.
There must not be any empty cells ' ' or other lowercase letters directly left or right of the word if the word was placed horizontally.
There must not be any empty cells ' ' or other lowercase letters directly above or below the word if the word was placed vertically.
Given a string word, return true if word can be placed in board, or false otherwise.

Input: board = [["#", " ", "#"], [" ", " ", "#"], ["#", "c", " "]], word = "abc"
Output: true
Explanation: The word "abc" can be placed as shown above (top to bottom).


Input: board = [[" ", "#", "a"], [" ", "#", "c"], [" ", "#", "a"]], word = "ac"
Output: false
Explanation: It is impossible to place the word because there will always be a space/letter above or below it.

Input: board = [["#", " ", "#"], [" ", " ", "#"], ["#", " ", "c"]], word = "ca"
Output: true
Explanation: The word "ca" can be placed as shown above (right to left).


Constraints:

m == board.length
n == board[i].length
1 <= m * n <= 2 * 105
board[i][j] will be ' ', '#', or a lowercase English letter.
1 <= word.length <= max(m, n)
word will contain only lowercase English letters.

 */
package backtracking.medium;

public class WordPlacedOnCardBoard {

    int[][] dirs = {{0,1}, {0,-1}, {1, 0}, {-1,0}};
    public boolean placeWordInCrossword(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                // if matched or it is a empty string
                if (board[i][j] == ' ' || board[i][j] == word.charAt(0)) {
                    // try for each direction
                    for (int[] dir : dirs) {
                        int x = i;
                        int y = j;
                        int index = 0;
                        // check if the cell is at the bound or near block
                        if (isAtBound(i - dir[0], j - dir[1], board)) {
                            // start to match the string
                            while (index < word.length()) {
                                // out of bound
                                if(x < 0 || y < 0 || x >= m || y >= n) break;
                                if (board[x][y] == ' '|| word.charAt(index) == board[x][y]) {
                                    index ++;
                                    x += dir[0];
                                    y += dir[1];
                                } else {
                                    break;
                                }
                            }
                            // check endpoint condition
                            if (index == word.length() && isAtBound(x, y, board))
                                return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean isAtBound(int i, int j, char[][] board) {
        return i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] == '#';
    }

    public static void main(String[] args) {
        char[][] board = new char[][] {
                {'z',' '},
                {'z', ' '}
        };
        System.out.println(new WordPlacedOnCardBoard().placeWordInCrossword(board,"abcdef"));
    }

}

/*

O(mn)+O(mn);

 */
