/*

A string is a valid parentheses string (denoted VPS) if and only if it consists of "(" and ")" characters only, and:

It is the empty string, or
It can be written as AB (A concatenated with B), where A and B are VPS's, or
It can be written as (A), where A is a VPS.
We can similarly define the nesting depth depth(S) of any VPS S as follows:

depth("") = 0
depth(A + B) = max(depth(A), depth(B)), where A and B are VPS's
depth("(" + A + ")") = 1 + depth(A), where A is a VPS.
For example,  "", "()()", and "()(()())" are VPS's (with nesting depths 0, 1, and 2), and ")(" and "(()" are not VPS's.



Given a VPS seq, split it into two disjoint subsequences A and B, such that A and B are VPS's (and A.length + B.length = seq.length).

Now choose any such A and B such that max(depth(A), depth(B)) is the minimum possible value.

Return an answer array (of length seq.length) that encodes such a choice of A and B:  answer[i] = 0 if seq[i] is part of A, else answer[i] = 1.
Note that even though multiple answers may exist, you may return any of them.



Example 1:

Input: seq = "(()())"
Output: [0,1,1,1,1,0]
Example 2:

Input: seq = "()(())()"
Output: [0,0,0,1,1,0,1,1]

 */
package string.hard;

public class MaximumNestingValidParenthesis {

    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] res = new int[n];
        int level = 0;
        for (int i = 0; i < n; i++) {
            char c = seq.charAt(i);
            if (c == '(') {
                level++;
                res[i] = level % 2;
            } else {
                res[i] = level % 2;
                level--;
            }
        }
        return res;
    }

}

/*

Just shave off all sub-VPSs of level > max_level / 2 to 1 and appoint the rest to 0.
This may be visualized with a mountain that corresponds to a VPS.

The visualization method:

      _
( =  /     (Uphill)

     _
) =   \    (Downhill)
()(())() will give the following hill

( ) ( ( ) ) ( )
       __
 __  _/  \_  __  ____________________<] FIRE LASER
/  \/      \/  \                      | & SHAVE THE MOUNTAIN!

                     ||
                    \||/
                	 \/

       __
      /  \           Top of mountain  = "   ()   "

 __  ______  __
/  \/      \/  \     Base of mountain = "()(  )()"

Another method:

public int[] maxDepthAfterSplit(String seq) {
        int depth = 0, cur = 0, n = seq.length();
        for (int i = 0; i < n; ++i) {
            cur +=  seq.charAt(i) == '(' ?  1 : -1;
            depth = max(depth, cur);
        }
        int[] res = new int[n];
        for (int i = 0; i < n; ++i) {
            if (seq.charAt(i) == '(') {
                if (++cur > depth / 2)
                    res[i] = 1;
            } else {
                if (cur-- > depth / 2)
                    res[i] = 1;
            }
        }
        return res;
    }

Count the number of level of whole string.
Then split it by half.
Group 0: the part under the half height
Group 1: the part above the half height

 */