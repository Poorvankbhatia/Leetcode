/*
 You are given an even number of people num_people that stand around a circle and each person shakes hands with someone else, so that there are num_people / 2 handshakes total.

Return the number of ways these handshakes could occur such that none of the handshakes cross.

Since this number could be very big, return the answer mod 10^9 + 7



Example 1:

Input: num_people = 2
Output: 1

Input: num_people = 6
Output: 5
Example 4:

Input: num_people = 8
Output: 14


Constraints:

2 <= num_people <= 1000
num_people % 2 == 0
 */
package dyanamicprogramming.hard;

public class HandshakesCross {

    public int numberOfWays(int num_people) {
        long M = 1_000_000_007;
        long[] cache = new long[num_people+1];
        cache[0] = 1;
        for (int i = 2; i <= num_people; i += 2) {
            for (int j = 2; j <= i; j += 2) {
                cache[i] = (cache[i] + (cache[j-2]*cache[i-j])) % M;
            }
        }
        return (int)cache[num_people];
    }

}


/*
Consider there are n people (n is even)
For those people to not cross hand, person 1 can shake 2, 4, 6, 8, ..., n:

Shake 2: divide into 2 sets (an emtpy set and a set of people from 3 to n)
Shake 4: divide into 2 sets (a set of people 2 & 3 and a set of people from 5 to n)
Shake 6: divide into 2 sets (a set of people from 2 to 5 and a set of people from 7 to n)
...
Shake n: divide into 2 sets (a set of people from 2 to n-1 and an empty set)
For for n people, there are n/2 way for perosn 1 to shake hand. If person 1 shake hand with person k, there are count(2 to k-1)*count(k+1 to n) scenarios.

If we store an array cache where cache[i] denotes numbers of way when there are i people. Then:
count(2 to k-1)*count(k+1 to n) = cache[k-2]*cache[n-k]


--------



dp[n] is the number of shaking ways of n pairs people
In the the view of first people in these n pairs,
he/she can choose anyone, split i pairs on his left and n - 1 - i pairs on his right.

So here comes the equation of dynamic programme:
dp[n + 1] = dp[0] * dp[n] + dp[1] * dp[n - 1] + ..... + dp[n] * dp[0]


Complexity
Time O(N^2)
Space O(N)


public int numberOfWays(int n) {
        long mod = (long)1e9 + 7;
        long[] dp = new long[n / 2 + 1];
        dp[0] = 1;
        for (int k = 1; k <= n / 2; ++k) {
            for (int i = 0; i < k; ++i) {
                dp[k] = (dp[k] + dp[i] * dp[k - 1 - i]) % mod;
            }
        }
        return (int)dp[n / 2];
    }
 */