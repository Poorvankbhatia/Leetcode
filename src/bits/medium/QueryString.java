/*

Given a binary string S (a string consisting only of '0' and '1's) and a positive integer N, return true if and only
if for every integer X from 1 to N, the binary representation of X is a substring of S.



Example 1:

Input: S = "0110", N = 3
Output: true
Example 2:

Input: S = "0110", N = 4
Output: false

 */
package bits.medium;

public class QueryString {
    /*public boolean queryString(String S, int N) {
        for (int i = N; i > 0; --i)
            if (!S.contains(Integer.toBinaryString(i)))
                return false;
        return true;
    }*/

    public boolean queryString(String S, int N) {
        boolean[] list = new boolean[N];
        for (int i = 0; i < S.length(); ++i) {
            for (int j = i, num = 0; num <= N && j < S.length(); ++j) {
                num = (num << 1) + S.charAt(j) - '0';
                System.out.println(num);
                if (num > 0 && num <= N) {
                    list[num - 1]=true;
                }
            }
        }
        for (Boolean b : list) {
            if(!b) {
                return false;
            }
        }
        return true;
    }

}

/*

We can process the entire string and track all numbers [1..N] that we can build.

Solution
For each position i in S, gradually build num while num <= N.
Track each num in seen.
Return true if we built all numbers from 1 to N.


 O(m log N), where m is the size of S. For every position in S, we analyze log N digits.

 */
