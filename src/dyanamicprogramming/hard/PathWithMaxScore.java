/*

You are given a square board of characters. You can move on the board starting at the bottom right square marked with the character 'S'.

You need to reach the top left square marked with the character 'E'. The rest of the squares are labeled either with a numeric character 1, 2, ..., 9
or with an obstacle 'X'. In one move you can go up, left or up-left (diagonally) only if there is no obstacle there.

Return a list of two integers: the first integer is the maximum sum of numeric characters you can collect, and the second is the number of such paths
that you can take to get that maximum sum, taken modulo 10^9 + 7.

In case there is no path, return [0, 0].



Example 1:

Input: board = ["E23","2X2","12S"]
Output: [7,1]
Example 2:

Input: board = ["E12","1X1","21S"]
Output: [4,2]
Example 3:

Input: board = ["E11","XXX","11S"]
Output: [0,0]


Constraints:

2 <= board.length == board[i].length <= 100

 */
package dyanamicprogramming.hard;
import java.util.Arrays;
import java.util.List;

public class PathWithMaxScore {
    private int mod = (int)(Math.pow(10,9))+7;
    public int[] pathsWithMaxScore(List<String> board) {
        int[][][] result = new int[101][101][2];
        result[0][0][0] = 0; // Maximum Sum
        result[0][0][1] = 1; // Maximum Path count.
        for (int j=1;j<board.get(0).length();j++) {
            char c = board.get(0).charAt(j);
            if(c!='X') {
                result[0][j][0]=((c-'0')%mod+result[0][j-1][0]%mod)%mod;
                result[0][j][1]=1;
            } else {
                break;
            }
        }
        for (int i=1;i<board.size();i++) {
            char c = board.get(i).charAt(0);
            if(c!='X') {
                result[i][0][0]=((c-'0')%mod+result[i-1][0][0]%mod)%mod;
                result[i][0][1]=1;
            } else {
                break;
            }
        }
        for (int i=1;i<board.size();i++) {
            for (int j=1;j<board.get(0).length();j++) {
                char c = board.get(i).charAt(j);
                int sum=0;
                int[] maxTillNow = util(result,i,j);
                if((maxTillNow[0]==0 && maxTillNow[1]==0) || c=='X') {
                    continue;
                }
                sum = maxTillNow[0];
                if(c>='0' && c<='9') {
                    sum += (c-'0');
                }
                result[i][j][0] = sum;
                result[i][j][1] = maxTillNow[1]%mod;
            }
        }
        return new int[]{result[board.size()-1][board.get(0).length()-1][0],result[board.size()-1][board.get(0).length()-1][1]};
    }

    private int[] util(int[][][] result,int i,int j) {
        int sum =  Math.max(result[i-1][j-1][0],Math.max(result[i-1][j][0],result[i][j-1][0]));
        int path=0;
        if(result[i-1][j][0]==sum) {
            path = (path%mod+result[i-1][j][1]%mod)%mod;
        }
        if(result[i][j-1][0]==sum) {
            path = (path%mod+result[i][j-1][1]%mod)%mod;
        }
        if(result[i-1][j-1][0]==sum) {
            path = (path%mod+result[i-1][j-1][1]%mod)%mod;
        }
        return new int[]{sum,path};
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList(
                "E999999999",
                "9999999999",
                "9999999999",
                "999999999S");
        System.out.println(Arrays.toString(new PathWithMaxScore().pathsWithMaxScore(list)));
    }

}

/*

2D array solution:

private static final int[][] DIRS = new int[][]{{0, -1}, {-1, 0}, {-1, -1}};
    public int[] pathsWithMaxScore(List<String> board) {
        int m = board.size(), n = board.get(0).length();
        int[][] dpSum = new int[m][n];
        int[][] dpCnt = new int[m][n];
        dpCnt[m - 1][n - 1] = 1; // start at the bottom right square
        for (int r = m - 1; r >= 0; r--) {
            for (int c = n - 1; c >= 0; c--) {
                if (dpCnt[r][c] == 0) continue; // can't reach to this square
                for (int[] dir : DIRS) {
                    int nr = r + dir[0], nc = c + dir[1];
                    if (nr >= 0 && nc >= 0 && board.get(nr).charAt(nc) != 'X') {
                        int nsum = dpSum[r][c];
                        if (board.get(nr).charAt(nc) != 'E')
                            nsum += board.get(nr).charAt(nc) - '0';
                        if (nsum > dpSum[nr][nc]) {
                            dpCnt[nr][nc] = dpCnt[r][c];
                            dpSum[nr][nc] = nsum;
                        } else if (nsum == dpSum[nr][nc]) {
                            dpCnt[nr][nc] = (dpCnt[nr][nc] + dpCnt[r][c]) % 1000000007;
                        }
                    }
                }
            }
        }
        return new int[]{dpSum[0][0], dpCnt[0][0]};
    }

 */