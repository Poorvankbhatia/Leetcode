/*

Given strings A and B of the same length, we say A[i] and B[i] are equivalent characters. For example, if A = "abc" and B = "cde",
then we have 'a' == 'c', 'b' == 'd', 'c' == 'e'.

Equivalent characters follow the usual rules of any equivalence relation:

Reflexivity: 'a' == 'a'
Symmetry: 'a' == 'b' implies 'b' == 'a'
Transitivity: 'a' == 'b' and 'b' == 'c' implies 'a' == 'c'
For example, given the equivalency information from A and B above, S = "eed", "acd", and "aab" are equivalent strings, and "aab"
is the lexicographically smallest equivalent string of S.

Return the lexicographically smallest equivalent string of S by using the equivalency information from A and B.



Example 1:

Input: A = "parker", B = "morris", S = "parser"
Output: "makkek"
Explanation: Based on the equivalency information in A and B, we can group their characters as [m,p], [a,o], [k,r,s], [e,i].
The characters in each group are equivalent and sorted in lexicographical order. So the answer is "makkek".
Example 2:

Input: A = "hello", B = "world", S = "hold"
Output: "hdld"
Explanation:  Based on the equivalency information in A and B, we can group their characters as [h,w], [d,e,o], [l,r].
So only the second letter 'o' in S is changed to 'd', the answer is "hdld".
Example 3:

Input: A = "leetcode", B = "programs", S = "sourcecode"
Output: "aauaaaaada"
Explanation:  We group the equivalent characters in A and B as [a,o,e,r,s,c], [l,p], [g,t] and [d,m], thus all letters in S except
'u' and 'd' are transformed to 'a', the answer is "aauaaaaada".


Note:

String A, B and S consist of only lowercase English letters from 'a' - 'z'.
The lengths of string A, B and S are between 1 and 1000.
String A and B are of the same length.

 */
package bfsdfs.medium;
import bfsdfs.UnionFind;
import java.util.HashMap;
import java.util.Map;

public class LexicographicSmallestEquivalentString {

    public String smallestEquivalentString(String A, String B, String S) {

        UnionFind unionFind = new UnionFind(26);

        for (int i=0;i<A.length();i++) {
            int a = A.charAt(i)-'a';
            int b = B.charAt(i)-'a';
            if(!unionFind.isConnected(a,b)) {
                unionFind.union(a,b);
            }
        }

        // get the smallest element of a connected component.
        Map<Integer,Integer> smallestElement = new HashMap<>();
        for (int i=0;i<26;i++) {
            smallestElement.putIfAbsent(unionFind.find(i),i);
        }

        StringBuilder result = new StringBuilder();
        for (char c : S.toCharArray()) {
            int val = c-'a';
            int parent = smallestElement.get(unionFind.find(val));
            result.append((char) (parent+'a'));
        }

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(new LexicographicSmallestEquivalentString().smallestEquivalentString("sdqldcfrjsmdgdbfbnbmtqotjpkslbtenpdkqnqmipkgloldhu",
                "ngmhdmanopnasmqslijqkmeffismuhstnggrfrkujnpgfaoqtb", "hzczmpdghfcciknjnerrohwcrunovgvebhuexezwyziqtsvifd"));
    }

}

/*

We can also change UF to union on smaller elements and not on size.

 */
