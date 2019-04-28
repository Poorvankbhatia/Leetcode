/*

A string S of lowercase letters is given.  Then, we may make any number of moves.

In each move, we choose one of the first K letters (starting from the left), remove it, and place it at the end of the string.

Return the lexicographically smallest string we could have after any number of moves.



Example 1:

Input: S = "cba", K = 1
Output: "acb"
Explanation:
In the first move, we move the 1st character ("c") to the end, obtaining the string "bac".
In the second move, we move the 1st character ("b") to the end, obtaining the final result "acb".
Example 2:

Input: S = "baaca", K = 3
Output: "aaabc"
Explanation:
In the first move, we move the 1st character ("b") to the end, obtaining the string "aacab".
In the second move, we move the 3rd character ("c") to the end, obtaining the final result "aaabc".


Note:

1 <= K <= S.length <= 1000
S consists of lowercase letters only.

 */
package string.hard;

import java.util.Arrays;

/**
 * Created by poorvank.b on 02/09/18.
 */
public class OrderlyQueue {

    public String orderlyQueue(String S, int K) {
        if(K == 1) return slideString(S);
        char[] arr = S.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }

    private String slideString(String S) {
        String res = S, cur = S;
        for(int i = 0; i < S.length(); i ++) {
            cur = cur.substring(1) + cur.charAt(0);
            if(cur.compareTo(res) < 0)
                res = cur;
        }
        return res;
    }

}

/*

For K = 1, try all queue positions: O(N)
For K > 1, characters may be re-arranged in any order, so, we may just sort the queue: O(N) in case of counting

 */
