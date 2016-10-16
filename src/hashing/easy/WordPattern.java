/*

Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

Examples:
pattern = "abba", str = "dog cat cat dog" should return true.
pattern = "abba", str = "dog cat cat fish" should return false.
pattern = "aaaa", str = "dog cat cat dog" should return false.
pattern = "abba", str = "dog dog dog dog" should return false.

 */

package hashing.easy;

import java.util.HashMap;

/**
 * Created by poorvank on 16/10/16.
 */
public class WordPattern {

    public boolean wordPattern(String pattern, String str) {

        HashMap<Character,String> map = new HashMap<>();
        HashMap<String,Character> stringCharacterHashMap = new HashMap<>();
        String[] strArr = str.split(" ");
        char[] charArr = pattern.toCharArray();

        if(strArr.length!=charArr.length) {
            return false;
        }

        for (int i=0;i<charArr.length;i++) {
            if(map.containsKey(charArr[i])) {
                if(!strArr[i].equals(map.get(charArr[i]))) {
                    return false;
                }
            } else {
                map.put(charArr[i],strArr[i]);
            }
        }

        for (int i=0;i<strArr.length;i++) {
            if(stringCharacterHashMap.containsKey(strArr[i])) {
                if(charArr[i]!=(stringCharacterHashMap.get(strArr[i]))) {
                    return false;
                }
            } else {
                stringCharacterHashMap.put(strArr[i],charArr[i]);
            }
        }

        return true ;

    }

    public static void main(String[] args) {
        String pattern = "abba";
        String str = "dog cat cat dog";

        System.out.println(new WordPattern().wordPattern(pattern,str));
    }

}
