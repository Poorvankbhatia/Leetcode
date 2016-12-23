/*

Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters.
No two characters may map to the same character but a character may map to itself.

For example,
Given "egg", "add", return true.

Given "foo", "bar", return false.

Given "paper", "title", return true.

 */
package hashing.easy;

import java.util.HashMap;

/**
 * Created by poorvank on 11/11/16.
 */
public class IsomorphicStrings {

    public boolean isIsomorphic(String s, String t) {

        if(s.length()!=t.length()) {
            return false;
        }

        HashMap<Character,Character> map = new HashMap<>();

        for (int i=0;i<s.length();i++) {
            if(map.containsKey(s.charAt(i))) {
                if(map.get(s.charAt(i))!=t.charAt(i)) {
                    return false;
                }
            } else {
                map.put(s.charAt(i),t.charAt(i));
            }
        }

        map.clear();

        for (int i=0;i<t.length();i++) {
            if(map.containsKey(t.charAt(i))) {
                if(map.get(s.charAt(i))!=t.charAt(i)) {
                    return false;
                }
            } else {
                map.put(t.charAt(i),s.charAt(i));
            }
        }

        return true;

    }

}
