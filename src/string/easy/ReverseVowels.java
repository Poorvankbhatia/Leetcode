/*
Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:
Given s = "hello", return "holle".

Example 2:
Given s = "leetcode", return "leotcede".

Note:
The vowels does not include the letter "y".

 */
package string.easy;

/**
 * Created by poorvank on 17/11/16.
 */
public class ReverseVowels {

    public String reverseVowels(String s) {

        if(s==null || s.length()==0) {
            return "";
        }

        char[] arr = s.toCharArray();
        int i=0,j=arr.length-1;

        while (i<=j) {
            if(isVowel(arr[i]) && isVowel(arr[j])){
                char t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
                i++;j--;
            } else {
                if(!isVowel(arr[i])) {
                    i++;
                }
                if(!isVowel(arr[j])) {
                    j--;
                }
            }
        }

        return new String(arr);

    }

    private boolean isVowel(char c) {
        c = Character.toLowerCase(c);
        return c=='a' || c=='e' || c=='i' || c=='o' || c=='u';
    }



}
