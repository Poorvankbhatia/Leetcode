/*
On an NxN chessboard, a knight starts at the r-th row and c-th column and attempts to make exactly K moves.
The rows and columns are 0 indexed, so the top-left square is (0, 0), and the bottom-right square is (N-1, N-1).

A chess knight has 8 possible moves it can make, as illustrated below.
Each move is two squares in a cardinal direction, then one square in an orthogonal direction.

Each time the knight is to move, it chooses one of eight possible moves uniformly at random (even if the piece would go off the chessboard)
and moves there.

The knight continues moving until it has made exactly K moves or has moved off the chessboard. Return the probability that the knight remains
on the board after it has stopped moving.

Example:
Input: 3, 2, 0, 0
Output: 0.0625
Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight on the board.
From each of those positions, there are also two moves that will keep the knight on the board.
The total probability the knight stays on the board is 0.0625.
 */
package bfsdfs.medium;

/**
 * Created by poorvank.b on 01/10/17.
 */
public class KnightProbability {

    int X[] = { 1, 2, 2, 1, -1, -2, -2, -1 };
    int Y[] = { 2, 1, -1, -2, -2, -1, 1, 2 };

    public double knightProbability(int N, int steps, int r, int c) {

        double dpTable[][][] = new double[N][N][steps+1];

        for (int i = 0; i < N; ++i)
            for (int j = 0; j < N; ++j)
                dpTable[i][j][0] = 1;

        // for every number of steps s
        for (int s = 1; s <= steps; ++s)
        {
            for (int x = 0; x < N; ++x)
            {
                for (int y = 0; y < N; ++y)
                {
                    double prob = 0.0;

                    for (int i = 0; i < 8; ++i)
                    {
                        int nx = x + X[i];
                        int ny = y + Y[i];

                        if (isSafe(nx,ny,N)) {
                            prob += dpTable[nx][ny][s-1] / 8.0;
                        }
                    }

                    dpTable[x][y][s] = prob;
                }
            }
        }


        return dpTable[r][c][steps];

    }

    private static boolean isSafe(int i,int j,int n) {
        return (i>=0 && j>=0 && i<n && j<n);
    }

    public static void main(String[] args) {
        System.out.println(new KnightProbability().knightProbability(8,30,6,4));
    }

}

/*

One thing that we can observe is that at every step the Knight has 8 choices to choose from. Suppose, the Knight has to take k
steps and after taking the Kth step the knight reaches (x,y). There are 8 different positions from where the Knight can reach to (x,y)
 in one step, and they are: (x+1,y+2), (x+2,y+1), (x+2,y-1), (x+1,y-2), (x-1,y-2), (x-2,y-1), (x-2,y+1), (x-1,y+2).

What if we already knew the probabilities of reaching these 8 positions after K-1 steps?
Then, the final probability after K steps will  be = Σ (probability of reaching each of these 8 positions after K-1 steps)/8;

Here we are dividing by 8 because each of these 8 positions have 8 choices and position (x,y) is one of the choice.
For the positions that lie outside the board, we will either take their probabilities as 0 or simply neglect it.

Since, we need to keep track of the probabilities at each position for every number of steps, we need Dynamic Programming to solve this problem.
We are going to take an array dp[x][y][steps] which will store the probability of reaching (x,y) after (steps) number of moves.
Base case : if number of steps is 0, then the probability that the Knight will remain inside the board is 1.

 */
