/*

Given a string and an integer k, you need to reverse the first k characters for every 2k characters counting from the start of the string.
If there are less than k characters left, reverse all of them. If there are less than 2k but greater than or equal to k characters,
then reverse the first k characters and left the other as original.
Example:
Input: s = "abcdefg", k = 2
Output: "bacdfeg"
Restrictions:
The string consists of lower English letters only.
Length of the given string and k will in the range [1, 10000]

 */

package string.easy;

/**
 * Created by poorvank.b on 12/03/17.
 */
public class ReverseString2 {

    public String reverseStr(String s, int k) {

        if(k==0 || null==s || s.length()==0) {
            return s;
        }

        if(s.length()<k) {
            return new StringBuilder(s).reverse().toString();
        }

        int range= (2*k);

        if(s.length()<range) {
            return new String(reverseUtil(s.toCharArray(),0,k-1));
        }

        char[] charArr = s.toCharArray();
        for (int i=0;i<charArr.length;i+=range) {
            if(charArr.length-i>=k) {
                charArr = reverseUtil(charArr,i,i+k-1);
            } else {
                charArr = reverseUtil(charArr,i,charArr.length-1);
            }
        }

        return new String(charArr);

    }

    private char[] reverseUtil(char[] arr,int start,int end) {
        if(end>=arr.length) {
            return arr;
        }
        while (start < end) {
            char t = arr[start];
            arr[start] = arr[end];
            arr[end] = t;
            start++;
            end--;
        }
        return arr;
    }

    public static void main(String[] args) {
        String s = "hyzqyljrnigxvdtneasepfahmtyhlohwxmkqcdfehybknvdmfrfvtbsovjbdhevlfxpdaovjgunjqlimjkfnqcqnajmebeddqsgl";
        int c = 39;
        System.out.println(new ReverseString2().reverseStr(s,c));
    }

}
