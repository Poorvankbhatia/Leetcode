/*
Given an integer n, return the number of strings of length n that consist only of vowels (a, e, i, o, u)
and are lexicographically sorted.

A string s is lexicographically sorted if for all valid i, s[i] is the same as or comes before s[i+1] in the alphabet.



Example 1:

Input: n = 1
Output: 5
Explanation: The 5 sorted strings that consist of vowels only are ["a","e","i","o","u"].
Example 2:

Input: n = 2
Output: 15
Explanation: The 15 sorted strings that consist of vowels only are
["aa","ae","ai","ao","au","ee","ei","eo","eu","ii","io","iu","oo","ou","uu"].
Note that "ea" is not a valid string since 'e' comes after 'a' in the alphabet.
Example 3:

Input: n = 33
Output: 66045


Constraints:

1 <= n <= 50
 */
package dyanamicprogramming.medium;

public class CountVowelString {

    public int countVowelStrings(int n) {
        int[] a = new int[n+1];
        int[] e = new int[n+1];
        int[] i = new int[n+1];
        int[] o = new int[n+1];
        int[] u = new int[n+1];


        a[1]=1;e[1]=1;i[1]=1;o[1]=1;u[1]=1;

        for(int k=2;k<=n;k++) {
            a[k] = a[k-1]+e[k-1]+i[k-1]+o[k-1]+u[k-1];
            e[k] = e[k-1]+i[k-1]+o[k-1]+u[k-1];
            i[k] = i[k-1]+o[k-1]+u[k-1];
            o[k] = o[k-1]+u[k-1];
            u[k] = u[k-1];
        }

        return a[n]+e[n]+i[n]+o[n]+u[n];

    }

    public static void main(String[] args) {
        System.out.println(new CountVowelString().countVowelStrings(3));
    }

}

/*
For sorted strings of length x starting with "a", append "a" to all strings of length x-1;
For sorted strings of length x starting with "e", append "e" to all strings of length x-1 starting with "e","i,"o",u";
For sorted strings of length x starting with "o", append "o" to all strings of length x-1 starting with "o",u";
.. and so on..


 */