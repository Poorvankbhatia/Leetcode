/*

A string S represents a list of words.

Each letter in the word has 1 or more options.  If there is one option, the letter is represented as is.
If there is more than one option, then curly braces delimit the options.  For example, "{a,b,c}" represents options ["a", "b", "c"].

For example, "{a,b,c}d{e,f}" represents the list ["ade", "adf", "bde", "bdf", "cde", "cdf"].

Return all words that can be formed in this manner, in lexicographical order.



Example 1:

Input: "{a,b}c{d,e}f"
Output: ["acdf","acef","bcdf","bcef"]
Example 2:

Input: "abcd"
Output: ["abcd"]


Note:

1 <= S.length <= 50
There are no nested curly brackets.
All characters inside a pair of consecutive opening and ending curly brackets are different.

 */
package string.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BraceExpansion {

    public String[] expand(String S) {
        List<List<String>> seprated = new ArrayList<>();
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (c == '{') {
                if (sb.length() != 0) {
                    list.add(sb.toString());
                    seprated.add(list);
                    list = new ArrayList<>();
                }
                i++;

                while (S.charAt(i) != '}') {
                    if (S.charAt(i) != ',') {
                        list.add(S.charAt(i) + "");
                    }
                    i++;
                }
                seprated.add(list);
                sb = new StringBuilder();
                list = new ArrayList<>();
            } else {
                sb.append(c);
            }
        }
        if (sb.length() != 0) {
            list.add(sb.toString());
            seprated.add(list);
        }

        List<String> result = new ArrayList<>();
        dfs(seprated, 0, new StringBuilder(), result);
        Collections.sort(result);
        return result.toArray(new String[0]);

    }


    private void dfs(List<List<String>> seprated, int count, StringBuilder sb, List<String> result) {

        if (count == seprated.size()) {
            result.add(sb.toString());
            return;
        }

        for (String s : seprated.get(count)) {
            sb.append(s);
            dfs(seprated, count + 1, sb, result);
            sb.setLength(sb.length() - s.length());
        }


    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new BraceExpansion().expand("{k}{js}")));
    }

}
