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

        if(s==null || (s.length()<t.length())) {
            return "";
        }
        int n = s.length();
        int[] needToFind = new int[256];
        for(char c : t.toCharArray()) {
            needToFind[c]++;
        }

        int[] hasFound = new int[256];

        int start=0;
        int tLen=0;
        int minLen = Integer.MAX_VALUE;
        int fStart=0;
        int fEnd=0;
        for(int end=0;end<n;end++) {
            char c = s.charAt(end);
            if(needToFind[c]==0) {
                continue;
            }
            if(needToFind[c]>hasFound[c]) {
                tLen++;
            }
            hasFound[c]++;
            if(tLen==t.length()) {
                char x = s.charAt(start);
                while(hasFound[x]>needToFind[x] || needToFind[x]==0) {
                    if(hasFound[x]>needToFind[x]) {
                        hasFound[x]--;
                    }
                    start++;
                    x = s.charAt(start);
                }
                if(minLen>end-start+1) {
                    minLen = end-start+1;
                    fStart =start;
                    fEnd = end;
                }
            }
        }

        String result = s.substring(fStart,fEnd+1);

        return minLen==Integer.MAX_VALUE?"":result;

    }


    public static void main(String[] args) {
        System.out.print(new MinimumWindowSubstring().minWindow("cabwefgewcwaefgcf","cae"));
    }

}

/*

public class Solution {
public String minWindow(String s, String t) {
    if(s == null || s.length() < t.length() || s.length() == 0){
        return "";
    }
    HashMap<Character,Integer> map = new HashMap<Character,Integer>();
    for(char c : t.toCharArray()){
        if(map.containsKey(c)){
            map.put(c,map.get(c)+1);
        }else{
            map.put(c,1);
        }
    }
    int left = 0;
    int minLeft = 0;
    int minLen = s.length()+1;
    int count = 0;
    for(int right = 0; right < s.length(); right++){
        if(map.containsKey(s.charAt(right))){
            map.put(s.charAt(right),map.get(s.charAt(right))-1);
            if(map.get(s.charAt(right)) >= 0){
                count ++;
            }
            while(count == t.length()){
                if(right-left+1 < minLen){
                    minLeft = left;
                    minLen = right-left+1;
                }
                if(map.containsKey(s.charAt(left))){
                    map.put(s.charAt(left),map.get(s.charAt(left))+1);
                    if(map.get(s.charAt(left)) > 0){
                        count --;
                    }
                }
                left ++ ;
            }
        }
    }
    if(minLen>s.length())
    {
        return "";
    }

    return s.substring(minLeft,minLeft+minLen);
}

 */