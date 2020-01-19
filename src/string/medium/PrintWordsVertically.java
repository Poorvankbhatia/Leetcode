/*
Given a string s. Return all the words vertically in the same order in which they appear in s.
Words are returned as a list of strings, complete with spaces when is necessary. (Trailing spaces are not allowed).
Each word would be put on only one column and that in one column there will be only one word.



Example 1:

Input: s = "HOW ARE YOU"
Output: ["HAY","ORO","WEU"]
Explanation: Each word is printed vertically.
 "HAY"
 "ORO"
 "WEU"
Example 2:

Input: s = "TO BE OR NOT TO BE"
Output: ["TBONTB","OEROOE","   T"]
Explanation: Trailing spaces is not allowed.
"TBONTB"
"OEROOE"
"   T"
Example 3:

Input: s = "CONTEST IS COMING"
Output: ["CIC","OSO","N M","T I","E N","S G","T"]


Constraints:

1 <= s.length <= 200
s contains only upper case English letters.
It's guaranteed that there is only one space between 2 words.
 */
package string.medium;

import java.util.ArrayList;
import java.util.List;

public class PrintWordsVertically {

    public List<String> printVertically(String s) {
        List<String> result = new ArrayList<>();
        if(s==null || s.length()==0) {
            return result;
        }
        String[] split = s.split(" ");
        int max = Integer.MIN_VALUE;
        for (String k : split) {
            max = Math.max(k.length(),max);
        }
        for (int i=0;i<max;i++) {
            StringBuilder res = new StringBuilder();
            for (String k : split) {
                if(i<k.length()) {
                    res.append(k.charAt(i));
                } else {
                    res.append(" ");
                }
            }
            int len=res.length();
            for (int x=res.length()-1;x>=0;x--) {
                if(res.charAt(x)!=' '){
                    len=x;
                    break;
                }
            }
            result.add(res.substring(0,len+1));
        }
        return result;
    }

}
