/*

Given a string S and a character C, return an array of integers representing the shortest distance from the character C in the string.

Example 1:

Input: S = "loveleetcode", C = 'e'
Output: [3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0]


 */
package hashing.easy;

import java.util.Arrays;

/**
 * Created by poorvank.b on 22/04/18.
 */
public class ShortestDistanceToCharacter {

    public int[] shortestToChar(String S, char C) {

        int[] result = new int[S.length()];

        for (int i=0;i<S.length();i++) {
            if(S.charAt(i)==C) {
                continue;
            }
            int rightPos=Integer.MAX_VALUE,leftPos=Integer.MAX_VALUE;
            if(i!=S.length()-1) {
                rightPos = S.substring(i+1).indexOf(C)!=-1?S.substring(i+1).indexOf(C)+i+1:Integer.MAX_VALUE;
            }
            if(i!=0) {
                leftPos = S.substring(0,i).lastIndexOf(C)!=-1?S.substring(0,i).lastIndexOf(C):Integer.MAX_VALUE;
            }

            result[i] = Math.min(Math.abs(leftPos-i),Math.abs(rightPos-i));
        }

        return result;

    }

    public static void main(String[] args) {
        String s = "abaa";
        System.out.println(Arrays.toString(new ShortestDistanceToCharacter().shortestToChar(s,'b')));
    }

}
