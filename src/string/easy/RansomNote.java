/*

Given an arbitrary ransom note string and another string containing letters from all the magazines,
 write a function that will return true if the ransom note can be constructed from the magazines ; otherwise, it will return false.

Each letter in the magazine string can only be used once in your ransom note.

Note:
You may assume that both strings contain only lowercase letters.

canConstruct("a", "b") -> false
canConstruct("aa", "ab") -> false
canConstruct("aa", "aab") -> true

 */

package string.easy;

/**
 * Created by poorvank on 14/11/16.
 */
public class RansomNote {

    public boolean canConstruct(String ransomNote, String magazine) {

        if(magazine!=null && ransomNote!=null && (magazine.length()==ransomNote.length()) && magazine.length()==0){
            return true;
        }

        if(magazine==null || magazine.length()==0 || ransomNote==null){
            return false;
        }

        int[] count = new int[26];

        for (int i=0;i<magazine.length();i++) {
            count[magazine.charAt(i)-'a']++;
        }

        for (int i=0;i<ransomNote.length();i++) {
            if(--count[magazine.charAt(i)-'a']<0) {
                return false;
            }
        }



        return true;
    }

}
