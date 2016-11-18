/*

Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".

What constitutes a word?
A sequence of non-space characters constitutes a word.
Could the input string contain leading or trailing spaces?
Yes. However, your reversed string should not contain leading or trailing spaces.
How about multiple spaces between two words?
Reduce them to a single space in the reversed string.

 */

package string.easy;

/**
 * Created by poorvank on 02/09/16.
 */
public class ReverseString {

    public String reverseWords(String s) {

        String[] arr = s.split("\\s+");
        StringBuilder result = new StringBuilder();
        for (int i=0;i<arr.length;i++) {
            if(arr[i].equals(" ")) {
                continue;
            }
            arr[i] = reverseString(arr[i]);
            result.append(arr[i]).append(" ");
        }
        return result.reverse().toString().trim();
    }

    private String reverseString(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    public static void main(String[] args) {

        ReverseString rs  = new ReverseString();
        System.out.println(rs.reverseWords("   a   b "));

    }

}
