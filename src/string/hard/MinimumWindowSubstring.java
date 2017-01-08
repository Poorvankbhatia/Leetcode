/*

Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the empty string "".

If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.

 */
package string.hard;

/**
 * Created by poorvank on 02/01/17.
 */
public class MinimumWindowSubstring {

    public String minWindow(String s, String t) {

        int sLen = s.length();
        int tLen = t.length();

        if(tLen>sLen) {
            return "";
        }

        int[] needToFind = new int[256];

        for (int i=0;i<tLen;i++) {
            needToFind[t.charAt(i)]++;
        }

        int minWindowLength=Integer.MAX_VALUE,finalStart=-1,finalEnd=0,count=0;

        int[] hasFound = new int[256];

        for (int start=0,end=0;end<sLen;end++) {

            if(needToFind[s.charAt(end)]==0) {
                continue;
            }

            hasFound[s.charAt(end)]++;

            if (hasFound[s.charAt(end)] <= needToFind[s.charAt(end)]) {
                count++;
            }

            if(count==tLen) {

                while (needToFind[s.charAt(start)]==0 || hasFound[s.charAt(start)]>needToFind[s.charAt(start)]) {

                    if(hasFound[s.charAt(start)]>needToFind[s.charAt(start)]) {
                        hasFound[s.charAt(start)]--;
                    }

                    start++;

                }

                int windowLength = end - start + 1;

                if (minWindowLength > windowLength) {
                    minWindowLength = windowLength;
                    finalStart = start;
                    finalEnd = end;
                }

            }

        }

        return (count!=0 && finalStart!=-1)?s.substring(finalStart, finalEnd + 1):"";

    }


    public static void main(String[] args) {
        System.out.print(new MinimumWindowSubstring().minWindow("babb","baba"));
    }

}
