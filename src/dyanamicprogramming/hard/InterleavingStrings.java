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
        System.out.println(new InterleavingStrings().isInterleave("a","","a"));
    }

}

/*

To solve this problem, let's look at if s1[0 ~ i] s2[0 ~ j] can be interleaved to s3[0 ~ k].

Start from indices0, 0, 0 and compare s1[i] == s3[k] or s2[j] == s3[k]
Return valid only if either i or j match k and the remaining is also valid
Caching is the key to performance. This is very similar to top down dp
Only need to cache invalid[i][j] since most of the case s1[0 ~ i] and s2[0 ~ j] does not form s3[0 ~ k]. Also tested caching valid[i][j] the run time is also 1ms


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

BFS Sol:

Imagine a grid, which x-axis and y-axis are s1 and s2, matching s3 is the same as
finding a path from (0,0) to (len1, len2). It actually becomes a
BFS on grid. Since we don't need exact paths, a HashSet of
coordinates is used to eliminate duplicated paths.



 public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length(),
            len2 = s2.length(),
            len3 = s3.length();
        if (len1 + len2 != len3) return false;
        Deque<Integer> queue = new LinkedList<>();
        int matched = 0;
        queue.offer(0);
        Set<Integer> set = new HashSet<>();
        while (queue.size() > 0 && matched < len3) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int p1 = queue.peek() / len3,
                    p2 = queue.peek() % len3;
                queue.poll();
                if (p1 < len1 && s1.charAt(p1) == s3.charAt(matched)) {
                    int key = (p1 + 1) * len3 + p2;
                    if (!set.contains(key)) {
                        set.add(key);
                        queue.offer(key);
                    }
                }
                if (p2 < len2 && s2.charAt(p2) == s3.charAt(matched)) {
                    int key = p1 * len3 + (p2 + 1);
                    if (!set.contains(key)) {
                        set.add(key);
                        queue.offer(key);
                    }
                }
            }
            matched++;
        }
        return queue.size() > 0 && matched == len3;
    }


 */