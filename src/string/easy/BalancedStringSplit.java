/*

Balanced strings are those who have equal quantity of 'L' and 'R' characters.

Given a balanced string s split it in the maximum amount of balanced strings.

Return the maximum amount of splitted balanced strings.



Example 1:

Input: s = "RLRRLLRLRL"
Output: 4
Explanation: s can be split into "RL", "RRLL", "RL", "RL", each substring contains same number of 'L' and 'R'.
Example 2:

Input: s = "RLLLLRRRLR"
Output: 3
Explanation: s can be split into "RL", "LLLRRR", "LR", each substring contains same number of 'L' and 'R'.
Example 3:

Input: s = "LLLLRRRR"
Output: 1
Explanation: s can be split into "LLLLRRRR".


Constraints:

1 <= s.length <= 1000
s[i] = 'L' or 'R'


 */
package string.easy;

public class BalancedStringSplit {

    public int balancedStringSplit(String s) {
        int n = s.length();
        int left = 0,right =0;
        int ans = 0;
        for(int i=0;i<n;i++) {
            if(s.charAt(i)=='L') {
                left++;
            } else {
                right++;
            }

            if(left==right) {
                ans++;
                left=0;right=0;
            }

        }
        return ans;
    }

}
