/*

Return the largest possible k such that there exists a_1, a_2, ..., a_k such that:

Each a_i is a non-empty string;
Their concatenation a_1 + a_2 + ... + a_k is equal to text;
For all 1 <= i <= k,  a_i = a_{k+1 - i}.


Example 1:

Input: text = "ghiabcdefhelloadamhelloabcdefghi"
Output: 7
Explanation: We can split the string on "(ghi)(abcdef)(hello)(adam)(hello)(abcdef)(ghi)".
Example 2:

Input: text = "merchant"
Output: 1
Explanation: We can split the string on "(merchant)".
Example 3:

Input: text = "antaprezatepzapreanta"
Output: 11
Explanation: We can split the string on "(a)(nt)(a)(pre)(za)(tpe)(za)(pre)(a)(nt)(a)".
Example 4:

Input: text = "aaa"
Output: 3
Explanation: We can split the string on "(a)(a)(a)".


Constraints:

text consists only of lowercase English characters.
1 <= text.length <= 1000

 */
package string.hard;

public class LongestPalindromeChunkDecomposition {

    public int longestDecomposition(String s) {
        int chunkCount = 0;
        String left = "", right = "";
        int i = 0, j = s.length() - 1;
        while (i < j) {
            left = left + s.substring(i, i+1);
            right = right + s.substring(j, j+1);
            if (left.equals(new StringBuilder(right).reverse().toString())) {
                System.out.println(left +" " + right);
                chunkCount += 2;
                left = "";
                right = "";
            }
            ++i;
            --j;
        }
        if ( (!left.equals("") && !right.equals("")) || i == j) // middle chunk left over
            ++chunkCount;
        return chunkCount;
    }

}

/*
Another sol:
public int longestDecomposition(String text) {
        for(int i = 1; i<=text.length()/2; i++)
            if(text.substring(0, i).equals(text.substring(text.length()-i, text.length())))
                return 2 + longestDecomposition(text.substring(i, text.length()-i));
        return text.length() == 0 ? 0 : 1;
    }

If we have long prefix matched and a shorter prefix matched at the same time.
The longer prefix can always be divided in to smaller part.

Assume we have a longer blue matched and a shorter red matched.
As definition of the statement, we have B1 = B2, R1 = R4.

Because B1 = B2,
the end part of B1 = the end part of B2,
equal to R2 = R4,
So we have R1 = R4 = R2.

B is in a pattern of R + middle part + R.
Instead take a longer B with 1 point,
we can cut it in to 3 parts to gain more points.

This proves that greedily take shorter matched it right.
Note that the above diagram shows cases when shorter length <= longer length/ 2
When shorter length > longer length/ 2, this conclusion is still correct.


To be more general,
the longer prefix and shorter prefix will alway be in these patter:

longer = a + ab * N
shorter = a + ab * (N - 1)

for example:
longer = "abc" + "def" + "abc"
shorter = "abc"

for example:
longer = "abc" * M
shorter = "abc" * N
where M > N


Solution 1, very brute force
When we know the greedy solution is right,
the coding is easier.
Just take letters from the left and right side,
Whenever they match, res++.



Time O(N) * O(string)
Space O(N)


More reading :  https://web.archive.org/web/20180219061925/http://cs.au.dk/~cstorm/courses/StrAlg_f12/slides/borders.pdf
 */