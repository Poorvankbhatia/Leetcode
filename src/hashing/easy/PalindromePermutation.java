/*

Given a string, determine if a permutation of the string could form a palindrome.
For example, "code" -> False, "aab" -> True, "carerac" -> True.


 */

package hashing.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by poorvank.b on 23/04/17.
 */
public class PalindromePermutation {

    public boolean canPermutePalindrome(String s) {

        if(s==null || s.length()==0) {
            return true;
        }

        Map<Character,Integer> map = new HashMap<>();
        for (Character ch : s.toCharArray()) {
            if(map.containsKey(ch)) {
                map.put(ch,map.get(ch)+1);
            } else {
                map.put(ch,1);
            }
        }

        int count =0;

        for (Character key : map.keySet()) {
            if(map.get(key)%2!=0) {
                count++;
            }
        }

        return count <= 1;

    }

}
