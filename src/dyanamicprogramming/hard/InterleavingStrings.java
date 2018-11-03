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

        int m = s1.length();
        int n = s2.length();

        boolean[][] invalid = new boolean[m+1][n+1];
        return util(s1,s2,s3,0,0,0,invalid);


    }

    private boolean util(String s1,String s2,String s3,int i,int j,
                         int k,boolean[][] invalid){

        if(invalid[i][j]) {
            return false;
        }
        if(k==s3.length()) {
            return true;
        }
        boolean valid =  (i<s1.length() &&s1.charAt(i)==s3.charAt(k) && util(s1,s2,s3,i+1,j,k+1,invalid)) ||
                (j<s2.length() && s2.charAt(j)==s3.charAt(k) && util(s1,s2,s3,i,j+1,k+1,invalid));

        if(!valid) {
            invalid[i][j]=true;
        }

        return valid;

    }

    public static void main(String[] args) {
        System.out.println(new InterleavingStrings().isInterleave("aa","ab","abaa"));
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