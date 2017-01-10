/*

Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return

[
  ["aa","b"],
  ["a","a","b"]
]

 */
package bfsdfs.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 10/01/17.
 */
public class PalindromePartitioning {

    public List<List<String>> partition(String s) {

        List<List<String>> result = new ArrayList<>();

        if(s==null || s.length()==0) {
            return result;
        }


        partitionUtil(0,s,result,new ArrayList<>());
        return result;

    }


    private void partitionUtil(int start,String s,List<List<String>> result,List<String> list) {

        if(start==s.length()) {
            result.add(new ArrayList<>(list));
            return;
        }

        /* <= is used because the entire string can also be a palindrome*/
        for (int i=start+1;i<=s.length();i++) {
            String subStr = s.substring(start,i);
            if(isPalindrome(subStr)) {
                list.add(subStr);
                partitionUtil(i,s,result,list);
                list.remove(list.size()-1);
            }
        }

    }

    private boolean isPalindrome(String s) {
        char[] arr = s.toCharArray();
        int start=0,end =arr.length-1;

        while (start<end) {
            if(s.charAt(start)==s.charAt(end)) {
                start++;
                end--;
            } else {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String s = "aba";
        System.out.println(new PalindromePartitioning().partition(s));
    }
}
