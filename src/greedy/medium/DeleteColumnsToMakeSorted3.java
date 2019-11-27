/*

We are given an array A of N lowercase letter strings, all of the same length.

Now, we may choose any set of deletion indices, and for each string, we delete all the characters in those indices.

For example, if we have an array A = ["abcdef","uvwxyz"] and deletion indices {0, 2, 3}, then the final array after deletions is ["bef","vyz"].

Suppose we chose a set of deletion indices D such that after deletions, the final array has its elements in lexicographic order (A[0] <= A[1] <= A[2] ... <= A[A.length - 1]).

Return the minimum possible value of D.length.



Example 1:

Input: ["ca","bb","ac"]
Output: 1
Explanation:
After deleting the first column, A = ["a", "b", "c"].
Now A is in lexicographic order (ie. A[0] <= A[1] <= A[2]).
We require at least 1 deletion since initially A was not in lexicographic order, so the answer is 1.
Example 2:

Input: ["xc","yb","za"]
Output: 0
Explanation:
A is already in lexicographic order, so we don't need to delete anything.
Note that the rows of A are not necessarily in lexicographic order:
ie. it is NOT necessarily true that (A[0][0] <= A[0][1] <= ...)
Example 3:

Input: ["zyx","wvu","tsr"]
Output: 3
Explanation:
We have to delete every column.


Note:

1 <= A.length <= 100
1 <= A[i].length <= 100

 */
package greedy.medium;

import java.util.HashSet;
import java.util.Set;

public class DeleteColumnsToMakeSorted3 {

    public int minDeletionSize(String[] A) {
        Set<Integer> deletedColums = new HashSet<>();
        for(int i=0;i<A.length-1;i++) {
            String s1 = A[i];
            String s2 = A[i+1];
            for(int j=0;j<A[0].length();j++) {
                if(!deletedColums.contains(j)) {
                    if (s1.charAt(j) > s2.charAt(j)) {
                        deletedColums.add(j);
                        i = -1;
                    } else if (s1.charAt(j) < s2.charAt(j)) {
                        break;
                    }
                }
            }
        }
        return deletedColums.size();
    }

}


/*
The key point is whenever we removed any index, we need to start the whole process from the start.
Why? the words compared prior may be relying on indices that are removed right now to maintain their lex order.
So run the logic again to find new indices that are to be removed to restore their lex order.

// ["doeeqiy","yabhbqe","twckqte"]

 */