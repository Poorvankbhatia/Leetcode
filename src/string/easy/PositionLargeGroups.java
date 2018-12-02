/*

In a string S of lowercase letters, these letters form consecutive groups of the same character.

For example, a string like S = "abbxxxxzyy" has the groups "a", "bb", "xxxx", "z" and "yy".

Call a group large if it has 3 or more characters.  We would like the starting and ending positions of every large group.

The final answer should be in lexicographic order.



Example 1:

Input: "abbxxxxzzy"
Output: [[3,6]]
Explanation: "xxxx" is the single large group with starting  3 and ending positions 6.
Example 2:

Input: "abc"
Output: []
Explanation: We have "a","b" and "c" but no large group.
Example 3:

Input: "abcdddeeeeaabbbcd"
Output: [[3,5],[6,9],[12,14]]


Note:  1 <= S.length <= 1000

 */
package string.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank.b on 06/05/18.
 */
public class PositionLargeGroups {

    public List<List<Integer>> largeGroupPositions(String S) {

        List<List<Integer>> result = new ArrayList<>();

        char lastChar = S.charAt(0);
        int lastIndex = 0;
        int n = S.length();
        int diff=0;
        for (int i=1;i<n;i++) {
            if(S.charAt(i)!=lastChar) {
                if(diff>=3) {
                    List<Integer> list = new ArrayList<>();
                    list.add(lastIndex);
                    list.add(i-1);
                    result.add(list);
                    diff=0;
                }
                lastChar = S.charAt(i);
                lastIndex = i;
            } else {
                diff = i-lastIndex+1;
            }
        }

        if(diff>=3) {
            List<Integer> list = new ArrayList<>();
            list.add(lastIndex);
            list.add(n-1);
            result.add(list);
        }

        return result;

    }

    public static void main(String[] args) {
        String s =  "aaa";
        System.out.println(new PositionLargeGroups().largeGroupPositions(s));
    }

}