/*

Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

For example,
Given:
s1 = "aabcc",
s2 = "dbbca",

When s3 = "aadbbcbcac", return true.
When s3 = "aadbbbaccc", return false.

 */
package dyanamicprogramming.hard;

/**
 * Created by poorvank on 19/01/17.
 */
public class InterleavingStrings {

    public boolean isInterleave(String s1, String s2, String s3) {

        if(s3.length()!=s1.length()+s2.length()) {
            return false;
        }

        boolean[][] dpTable = new boolean[s1.length()+1][s2.length()+1];

        dpTable[0][0] = true;

        for (int j=1;j<=s2.length();j++) {
            if(s2.charAt(j-1)==s3.charAt(j-1)) {
                dpTable[0][j] = dpTable[0][j-1];
            }
        }


        for (int i=1;i<=s1.length();i++) {
            if(s1.charAt(i-1)==s3.charAt(i-1)) {
                dpTable[i][0] = dpTable[i-1][0];
            }
        }

        for (int i=1;i<=s1.length();i++) {
            for (int j=1;j<=s2.length();j++) {

                char s1Char = s1.charAt(i-1);
                char s2Char = s2.charAt(j-1);
                char s3Char = s3.charAt(i+j-1);

                if(s3Char!=s1Char && s3Char==s2Char) {
                    dpTable[i][j] = dpTable[i][j-1];
                } else if(s3Char==s1Char && s3Char!=s2Char) {
                    dpTable[i][j] = dpTable[i-1][j];
                } else if(s3Char==s1Char && s3Char==s2Char) {
                    dpTable[i][j] = dpTable[i-1][j] || dpTable[i][j-1];
                }

            }
        }


        return dpTable[s1.length()][s2.length()];

    }

    public static void main(String[] args) {
        System.out.println(new InterleavingStrings().isInterleave("aa","ab","aaba"));
    }

}

/*
"aa"
"ab"
"aaba"

a) If first character of C matches with first character of A, we move one character ahead in A and C and recursively check.

b) If first character of C matches with first character of B, we move one character ahead in B and C and recursively check.

If any of the above two cases is true, we return true, else false. Following is simple recursive implementation of this approach:

// A simple recursive function to check whether C is an interleaving of A and B
The worst case time complexity of recursive solution is O(2^n)
bool isInterleaved(char *A, char *B, char *C)
{
    // Base Case: If all strings are empty
    if (!(*A || *B || *C))
        return true;

    // If C is empty and any of the two strings is not empty
    if (*C == '\0')
        return false;

    // If any of the above mentioned two possibilities is true,
    // then return true, otherwise false
    return ( (*C == *A) && isInterleaved(A+1, B, C+1))
           || ((*C == *B) && isInterleaved(A, B+1, C+1));
}


Time Complexity: O(MN)
Auxiliary Space: O(MN)

 */