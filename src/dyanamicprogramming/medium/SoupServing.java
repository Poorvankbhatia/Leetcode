/*

There are two types of soup: type A and type B. Initially we have N ml of each type of soup. There are four kinds of operations:

Serve 100 ml of soup A and 0 ml of soup B
Serve 75 ml of soup A and 25 ml of soup B
Serve 50 ml of soup A and 50 ml of soup B
Serve 25 ml of soup A and 75 ml of soup B
When we serve some soup, we give it to someone and we no longer have it.  Each turn, we will choose from the four
operations with equal probability 0.25. If the remaining volume of soup is not enough to complete the operation, we will
serve as much as we can.  We stop once we no longer have some quantity of both types of soup.

Note that we do not have the operation where all 100 ml's of soup B are used first.

Return the probability that soup A will be empty first, plus half the probability that A and B become empty at the same time.



Example:
Input: N = 50
Output: 0.625
Explanation:
If we choose the first two operations, A will become empty first. For the third operation, A and B will become empty at the same time.
For the fourth operation, B will become empty first. So the total probability of A becoming empty first plus half the probability that
A and B become empty at the same time, is 0.25 * (1 + 1 + 0.5 + 0) = 0.625.

Notes:

0 <= N <= 10^9.
Answers within 10^-6 of the true value will be accepted as correct.

 */
package dyanamicprogramming.medium;

/**
 * Created by poorvank.b on 10/01/19.
 */
public class SoupServing {

    public double soupServings(int N) {
        if(N>5000) {
            return 1.0;
        }
        return helper(new double[N+1][N+1],N,N);
    }

    private double helper(double[][] memo,int a,int b) {

        if(a<=0 && b<=0) {
            return 0.5;
        }
        if(a<=0) {
            return 1.0;
        }
        if(b<=0) {
            return 0.0;
        }

        if(memo[a][b]>0.0) {
            return memo[a][b];
        }

        int[] servingA = new int[]{100,75,50,25};
        int[] servingB = new int[]{0,25,50,75};

        double val = 0.0;

        for(int i=0;i<4;i++) {
            val+=helper(memo,a-servingA[i],b-servingB[i]);
        }

        memo[a][b]=(val*.25);
        return memo[a][b];

    }

}

/*

f(a,b) means the result probability for a ml of soup A and b ml of soup B.
f(a-4,b) means that we take the first operation: Serve 100 ml of soup A and 0 ml of soup B. f(a-3,b-1), f(a-2,b-2), f(a-1,b-3)
are other 3 operations.
The condition a <= 0 and b <= 0 means that we run out of soup A and B at the same time, so we should return a probability of 0.5,
which is half of 1.0.
The same idea for other two conditions.
I cached the process as we do for Fibonacci sequence. It calculate every case for only once and it can be reused for every test case.

"Note that we do not have the operation where all 100 ml's of soup B are used first. "
It's obvious that A is easier to be empty than B. And when N gets bigger, we have less chance to run out of B first.
So as N increases, our result increases and it gets closer to 100 percent = 1.

"Answers within 10^-6 of the true value will be accepted as correct."
Now it's obvious that when N is big enough, result is close enough to 1 and we just need to return 1.
When value of N is increased:
When N = 4800, the result = 0.999994994426
When N = 4801, the result = 0.999995382315
So if N>= 4800, just return 1 and it will be enough.

Complexity
O(N^2) for time and space.
But we restrict the value of N to be smaller than 4800.



 */