/*

Given a string S that only contains "I" (increase) or "D" (decrease), let N = S.length.

Return any permutation A of [0, 1, ..., N] such that for all i = 0, ..., N-1:

If S[i] == "I", then A[i] < A[i+1]
If S[i] == "D", then A[i] > A[i+1]


Example 1:

Input: "IDID"
Output: [0,4,1,3,2]
Example 2:

Input: "III"
Output: [0,1,2,3]
Example 3:

Input: "DDI"
Output: [3,2,0,1]


Note:

1 <= S.length <= 10000
S only contains characters "I" or "D".

 */
package string.easy;

/**
 * Created by poorvank.b on 18/11/18.
 */
public class DIStringMatch {

    public int[] diStringMatch(String S) {
        int n = S.length();
        int[] result = new int[n+1];
        int start=0,end=n;

        for(int i=0;i<n;i++) {
            if(S.charAt(i)=='D') {
                result[i]=end--;
            } else {
                result[i]=start++;
            }
        }

        if(S.charAt(n-1)=='I') {
            result[n]=start;
        } else {
            result[n]=end;
        }

        return result;

    }

}
