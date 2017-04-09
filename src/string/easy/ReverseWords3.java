/*

Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.

Example 1:
Input: "Let's take LeetCode contest"
Output: "s'teL ekat edoCteeL tsetnoc"
Note: In the string, each word is separated by single space and there will not be any extra space in the string.

 */

package string.easy;

/**
 * Created by poorvank.b on 09/04/17.
 */
public class ReverseWords3 {

    public String reverseWords(String s) {

        if(s==null || s.length()==0) {
            return s;
        }

        StringBuilder sb = new StringBuilder();
        String[] split = s.split(" ");
        for (String sub : split) {
            for (int i=sub.length()-1;i>=0;i--) {
                sb.append(sub.charAt(i));
            }
            sb.append(" ");
        }

        return sb.toString().trim();

    }

    public static void main(String[] args) {
        System.out.print(new ReverseWords3().reverseWords("Let's take LeetCode contest"));
    }

}
