/*

On an alphabet board, we start at position (0, 0), corresponding to character board[0][0].

Here, board = ["abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"].

We may make the following moves:

'U' moves our position up one row, if the square exists;
'D' moves our position down one row, if the square exists;
'L' moves our position left one column, if the square exists;
'R' moves our position right one column, if the square exists;
'!' adds the character board[r][c] at our current position (r, c) to the answer.
Return a sequence of moves that makes our answer equal to target in the minimum number of moves.  You may return any path that does so.



Example 1:

Input: target = "leet"
Output: "DDR!UURRR!!DDD!"
Example 2:

Input: target = "code"
Output: "RR!DDRR!UUL!R!"

 */
package arrays.medium;

public class AlphabetBoard {

    public String alphabetBoardPath(String target) {
        StringBuilder ans = new StringBuilder();
        int x = 0, y = 0;
        for (char c : target.toCharArray()) {
            int i = (c - 'a')/5;
            int j = (c - 'a')%5;
            if(i > x) {
                while(x != i) {
                    if(x == 4 && y > 0 )
                        break;
                    ans.append("D");
                    x++;
                }
            } else {
                while (x != i) {
                    ans.append("U");
                    x--;
                }
            }
            if(j > y) {
                while(y != j) {
                    ans.append("R");
                    y++;
                }
            } else {
                while(y != j) {
                    ans.append("L");
                    y--;
                }
            }
            if(x != i) {
                ans.append("D");
                x++;
            }
            ans.append("!");
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        String s = "leet";
        System.out.println(new AlphabetBoard().alphabetBoardPath(s));
    }

}
