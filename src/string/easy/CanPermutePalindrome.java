/*

Given a string, determine if a permutation of the string could form a palindrome.

Example 1:

Input: "code"
Output: false
Example 2:

Input: "aab"
Output: true
Example 3:

Input: "carerac"
Output: true

 */
package string.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by poorvank.b on 08/06/18.
 */
public class CanPermutePalindrome {

    public boolean canPermutePalindrome(String s) {
        if(s.length()==0) {
            return true;
        }

        Map<Character,Integer> map = new HashMap<>();
        for(Character c : s.toCharArray()) {
            map.put(c,map.getOrDefault(c,0)+1);
        }

        int oddCount=0;
        for(Map.Entry<Character,Integer> entry : map.entrySet()) {
            if(entry.getValue()%2!=0) {
                oddCount++;
            }
        }

        return oddCount==1 || oddCount==0;
    }

}

/*

public boolean canPermutePalindrome(String s) {
        Set < Character > set = new HashSet < > ();
        for (int i = 0; i < s.length(); i++) {
            if (!set.add(s.charAt(i)))
                set.remove(s.charAt(i));
        }
        return set.size() <= 1;
    }


 */
