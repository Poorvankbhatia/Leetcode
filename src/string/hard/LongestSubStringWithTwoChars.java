/*

 Given a string, find the length of the longest substring T
 that contains at most 2 distinct characters.

 For example, Given s = "eceba",

 T is "ece" which its length is 3.

 */

package string.hard;

import java.util.HashMap;

/**
 * Created by poorvank.b on 30/12/17.
 */
public class LongestSubStringWithTwoChars {

    public int lengthOfLongestSubstringTwoDistinct(String s) {

        if(s==null || s.length()==0) {
            return 0;
        }

        HashMap<Character,Integer> map = new HashMap<>();

        int start=0;
        int end=0;
        int begin=0;
        int maxLength = 0;

        for (int i=0;i<s.length();i++) {

            char c = s.charAt(i);
            if(map.containsKey(c)) {
                map.put(c,map.get(c)+1);
            } else {
                map.put(c,1);
            }

            if(map.size()>2) {

                if(maxLength<i-start) {
                    maxLength = i-start;
                    begin = start;
                    end = i;
                }

                while (map.size()>2) {
                    char leftMostChar = s.charAt(start);
                    if(map.get(leftMostChar)==1) {
                        map.remove(leftMostChar);
                    } else {
                        map.put(leftMostChar,map.get(leftMostChar)-1);
                    }

                    start++;
                }

            }

        }

        if(s.length()-start>maxLength) {
            maxLength = s.length()-start;
            begin = start;
            end = s.length();
        }

        System.out.println("Max String - " + s.substring(begin,end));
        return maxLength;

    }

    public static void main(String[] args) {
        System.out.println(new LongestSubStringWithTwoChars().lengthOfLongestSubstringTwoDistinct("eceba"));
    }

}
