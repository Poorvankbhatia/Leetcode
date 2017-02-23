/*

Given a word, you need to judge whether the usage of capitals in it is right or not.

We define the usage of capitals in a word to be right when one of the following cases holds:

All letters in this word are capitals, like "USA".
All letters in this word are not capitals, like "leetcode".
Only the first letter in this word is capital if it has more than one letter, like "Google".
Otherwise, we define that this word doesn't use capitals in a right way.
Example 1:
Input: "USA"
Output: True
Example 2:
Input: "FlaG"
Output: False

 */
package string.easy;

/**
 * Created by poorvank on 19/02/17.
 */
public class DetectCapital {

    public boolean detectCapitalUse(String word) {

        if(null==word || word.length()==0 || word.length()==1) {
            return true;
        }

        if(Character.isLowerCase(word.charAt(0))) {
            for (char c : word.toCharArray()) {
                if(Character.isUpperCase(c)) {
                    return false;
                }
            }
        } else {
            if(Character.isLowerCase(word.charAt(1))) {
                for (int i=1;i<word.length();i++) {
                    if(Character.isUpperCase(word.charAt(i))) {
                        return false;
                    }
                }
            } else {
                for (int i=1;i<word.length();i++) {
                    if(Character.isLowerCase(word.charAt(i))) {
                        return false;
                    }
                }
            }
        }

        return true;

    }

    public static void main(String[] args) {
        System.out.println(new DetectCapital().detectCapitalUse("linKK"));
    }

}
