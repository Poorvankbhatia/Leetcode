/*
You are given a string s and a robot that currently holds an empty string t. Apply one of the following operations until s and t are both empty:

Remove the first character of a string s and give it to the robot. The robot will append this character to the string t.
Remove the last character of a string t and give it to the robot. The robot will write this character on paper.
Return the lexicographically smallest string that can be written on the paper.



Example 1:

Input: s = "zza"
Output: "azz"
Explanation: Let p denote the written string.
Initially p="", s="zza", t="".
Perform first operation three times p="", s="", t="zza".
Perform second operation three times p="azz", s="", t="".
Example 2:

Input: s = "bac"
Output: "abc"
Explanation: Let p denote the written string.
Perform first operation twice p="", s="c", t="ba".
Perform second operation twice p="ab", s="c", t="".
Perform first operation p="ab", s="", t="c".
Perform second operation p="abc", s="", t="".
Example 3:

Input: s = "bdda"
Output: "addb"
Explanation: Let p denote the written string.
Initially p="", s="bdda", t="".
Perform first operation four times p="", s="", t="bdda".
Perform second operation four times p="addb", s="", t="".


Constraints:

1 <= s.length <= 105
s consists of only English lowercase letters.
 */
package stack.medium;

import java.util.Stack;

public class RobotLexicographicSmallestString {

    public String robotWithString(String s) {

        if(s==null || s.length()==0) {
            return "";
        }

        int[] freq = new int[26];
        for (char c: s.toCharArray()) {
            freq[c-'a']++;
        }
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            stack.add(c);
            freq[c-'a']--;
            while (!stack.isEmpty()) {
                if(remainingStringHasSmaller(stack.peek(),freq)) {
                    break;
                }
                sb.append(stack.pop());
            }
        }
        return sb.toString();
    }

    private boolean remainingStringHasSmaller(char c, int[] freq) {
        for (int i=0;i<(c-'a');i++) {
            if(freq[i]>0) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new RobotLexicographicSmallestString().robotWithString("bydizfve"));
    }

}

/*

The first loop is to record the frequence of every character, the array freq,
the second loop is to add every character into a stack, when adding the character into the stack,
decrease the frequency of the character by one in the array freq, then the array freq is the
frequency of every character in the rest of string.
When adding one character from the top of the stack to the result,
we check if there is one smaller character in the rest of the string,
if there is, keep pushing the character of the rest of the string into the stack,
if there is not, then add the top character into the result.



 */
