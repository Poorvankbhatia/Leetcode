/*

Given an integer n, your task is to count how many strings of length n can be formed under the following rules:

Each character is a lower case vowel ('a', 'e', 'i', 'o', 'u')
Each vowel 'a' may only be followed by an 'e'.
Each vowel 'e' may only be followed by an 'a' or an 'i'.
Each vowel 'i' may not be followed by another 'i'.
Each vowel 'o' may only be followed by an 'i' or a 'u'.
Each vowel 'u' may only be followed by an 'a'.
Since the answer may be too large, return it modulo 10^9 + 7.



Example 1:

Input: n = 1
Output: 5
Explanation: All possible strings are: "a", "e", "i" , "o" and "u".
Example 2:

Input: n = 2
Output: 10
Explanation: All possible strings are: "ae", "ea", "ei", "ia", "ie", "io", "iu", "oi", "ou" and "ua".
Example 3:

Input: n = 5
Output: 68


Constraints:

1 <= n <= 2 * 10^4

 */
package dyanamicprogramming.hard;

public class CountVowelPermutation {

    long[][] dp;
    int mod = ((int)Math.pow(10,9))+7;
    public int countVowelPermutation(int n) {
        dp = new long[n+1][5]; // 0-> a, 1->e, 2->i, 3->o,4->u
        for(int i=0;i<5;i++) {
            dp[1][i]=1;
        }
        int sum=0;
        for (int i=0;i<5;i++) {
            sum=(sum+util(n,i))%mod;
        }
        return sum;
    }

    private int util(int n,int index) {
        if(dp[n][index]!=0) { // Number of ways when we have n count left and we are at the vowel with index i.
            return (int) dp[n][index];
        }
        long sum=0;
        switch(index) {
            case 0:// If 'a' is the current char we can only have an 'e' as the next char
                sum = (sum+util(n-1,1))%mod;
                break;
            case 1:// If 'e' is the current char we can only have 'a' & 'i' as the next char
                sum = (sum+util(n-1,0)+util(n-1,2))%mod;//
                break;
            case 2:// If 'i' is the current char we can have any character follow it except 'i'
                sum = (sum+util(n-1,0)+util(n-1,1)+util(n-1,3)+util(n-1,4))%mod;
                break;
            case 3:// If 'o' is the current char we can only have 'i' & 'u' as the next char
                sum = (sum+util(n-1,2)+util(n-1,4))%mod;
                break;
            case 4:// If 'u' is the current char we can only have an 'a' as the next char
                sum = (sum+util(n-1,0))%mod;
                break;
        }

        dp[n][index]=sum;
        return (int) sum;

    }

    public static void main(String[] args) {
        System.out.println(new CountVowelPermutation().countVowelPermutation(5));
    }

}

/*

It can also be viewed as a graph:
From the above rules, we can create a directed graph where an edge between characters first and second imply that
it is permissible to write second immediately after first. Hence, the question converts to,
Given a directed graph, how many paths of length n are there?
Look Images

Now, Let us say that dp[n][char] denotes the number of directed paths of length n which end at a particular vertex char.
Then, we know that the last vertex in our path was char.
However, let's focus on the last second vertex.
It could have been any of the vertex which has a direct edge to char.
Hence, if we can find the number of paths of length n-1 ending at these vertices,
then we can append char at the end of every path and we would have exhausted all possibilities.

Hence, dp[n+1][x] = sum of all dp[n][y] such that there is a directed edge from y to x.

 */