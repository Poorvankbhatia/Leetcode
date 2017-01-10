/*
Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

For example, given s = "aab",
Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.

 */
package dyanamicprogramming.hard;

/**
 * Created by poorvank on 10/01/17.
 */
public class PalindromePartitioning2 {

    public int minCut(String s) {

        if(null==s || s.length()==0) {
            return 0;
        }

        int n = s.length();

        int[] cut = new int[n];

        boolean[][] isPalindrome = new boolean[n][n];

        for (int i=0;i<n;i++) {
            isPalindrome[i][i] = true;
        }

        for (int gap=2;gap<=n;gap++) {

            for (int i=0;i<n-gap+1;i++) {

                int j = i+gap-1;

                if(s.charAt(i)==s.charAt(j) && gap==2) {
                    isPalindrome[i][j] = true;
                } else if(s.charAt(i)==s.charAt(j)) {
                    isPalindrome[i][j] = isPalindrome[i+1][j-1];
                }

            }

        }

        for (int i=0;i<n;i++) {

            if(isPalindrome[0][i]) {
                cut[i] = 0;
            } else {
                cut[i] = Integer.MAX_VALUE;
                for (int j=0;j<i;j++) {
                    if(isPalindrome[j+1][i] && cut[j]+1<cut[i]) {
                        cut[i] = cut[j]+1;
                    }
                }
            }

        }



        return cut[n-1];

    }

    public static void main(String[] args) {
        String s = "ababbbabbababa";
        System.out.println(new PalindromePartitioning2().minCut(s));
    }

}

/*

Let the given string be str and minPalPartion() be the function that returns the fewest cuts needed for palindrome partitioning.
following is the optimal substructure property.

// i is the starting index and j is the ending index. i must be passed as 0 and j as n-1
minPalPartion(str, i, j) = 0 if i == j. // When string is of length 1.
minPalPartion(str, i, j) = 0 if str[i..j] is palindrome.

// If none of the above conditions is true, then minPalPartion(str, i, j) can be
// calculated recursively using the following formula.
minPalPartion(str, i, j) = Min { minPalPartion(str, i, k) + 1 +
                                 minPalPartion(str, k+1, j) }
                           where k varies from i to j-1

 */