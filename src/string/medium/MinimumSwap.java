/*

You are given two strings s1 and s2 of equal length consisting of letters "x" and "y" only. Your task is to make these two strings equal to each other. You can swap any two characters that belong to different strings, which means: swap s1[i] and s2[j].

Return the minimum number of swaps required to make s1 and s2 equal, or return -1 if it is impossible to do so.



Example 1:

Input: s1 = "xx", s2 = "yy"
Output: 1
Explanation:
Swap s1[0] and s2[1], s1 = "yx", s2 = "yx".
Example 2:

Input: s1 = "xy", s2 = "yx"
Output: 2
Explanation:
Swap s1[0] and s2[0], s1 = "yy", s2 = "xx".
Swap s1[0] and s2[1], s1 = "xy", s2 = "xy".
Note that you can't swap s1[0] and s1[1] to make s1 equal to "yx", cause we can only swap chars in different strings.
Example 3:

Input: s1 = "xx", s2 = "xy"
Output: -1
Example 4:

Input: s1 = "xxyyxyxyxx", s2 = "xyyxyxxxyx"
Output: 4


Constraints:

1 <= s1.length, s2.length <= 1000
s1, s2 only contain 'x' or 'y'.

 */
package string.medium;

import java.util.HashMap;
import java.util.Map;

public class MinimumSwap {
    public int minimumSwap(String s1, String s2) {
        if(s1.length()!=s2.length()) {
            return -1;
        }
        int n = s1.length();
        int count =0;
        Map<String,Integer> map = new HashMap<>();
        for(int i=0;i<n;i++) {
            if(s1.charAt(i)!=s2.charAt(i)) {
                String s = s1.charAt(i)+"_"+s2.charAt(i);
                if(map.containsKey(s)) {
                    count++;
                    map.remove(s);
                } else {
                    map.put(s,1);
                }
            }
        }
        if(map.size()%2!=0) {
            return -1;
        } else {
            return map.size()+count;
        }
    }

    public static void main(String[] args) {
        System.out.println(new MinimumSwap().minimumSwap("xy", "yx"));
    }

}

/*

The possible combinations for map with key as one character from first string and another form second are :
xy & yx.

We know that xy + xy =>(swaps) 1 (i.e Strings being : xx & yy)
Also yx + xy =>(swaps) 1 (i.e Strings being : xx & yy )
And left ones (xy & yx) and (yx & xy) =>(swaps) 2

If odd count of (xy or yx) keys: Making equal is not possible. Else just add map.size().


 */