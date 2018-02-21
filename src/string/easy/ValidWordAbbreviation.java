/*

Given a non-empty string s and an abbreviation abbr, return whether the string matches with the given abbreviation.
A string such as "word" contains only the following valid abbreviations:
["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
Notice that only the above abbreviations are valid abbreviations of the string "word". Any other string is not a valid abbreviation of "word".
Note:
Assume s contains only lowercase letters and abbr contains only lowercase letters and digits.
Example 1:
Given s = "internationalization", abbr = "i12iz4n":

Return true.

Example 2:
Given s = "apple", abbr = "a2e":

Return false.


 */
package string.easy;

/**
 * Created by poorvank.b on 31/12/17.
 */
public class ValidWordAbbreviation {

    public boolean validWordAbbreviation(String word, String abbr) {
        if (word.length() == 0) {
            return abbr.length() == 0;
        }
        int w = 0, ab = 0;
        while (w < word.length() && ab < abbr.length()) {
            char a = abbr.charAt(ab);
            if (Character.isLetter(a)) {
                if (word.charAt(w) != abbr.charAt(ab)) {
                    return false;
                }
                w++;
                ab++;
            } else if (Character.isDigit(a)) {
                if (a == '0') {
                    return false;
                }
                int org = ab;
                while (ab < abbr.length() && Character.isDigit(abbr.charAt(ab))) {
                    ab++;
                }
                int num = Integer.valueOf(abbr.substring(org, ab));
                while (w < word.length() && num > 0) {
                    w++;
                    num--;
                }
                if (num > 0) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

}

/*

Given a non-empty string s and an abbreviation abbr, return whether the string matches with the given abbreviation.
A string such as "word" contains only the following valid abbreviations:
["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
Notice that only the above abbreviations are valid abbreviations of the string "word". Any other string is not a valid abbreviation of "word".
Note:
Assume s contains only lowercase letters and abbr contains only lowercase letters and digits.
Example 1:
Given s = "internationalization", abbr = "i12iz4n":

Return true.

Example 2:
Given s = "apple", abbr = "a2e":

Return false.


 */