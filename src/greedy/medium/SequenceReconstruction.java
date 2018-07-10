/*

Check whether the original sequence org can be uniquely reconstructed from the sequences in seqs. The org sequence is a
permutation of the integers from 1 to n, with 1 ≤ n ≤ 10^4. Reconstruction means building a shortest common supersequence
of the sequences in seqs (i.e., a shortest sequence so that all sequences in seqs are subsequences of it).
Determine whether there is only one sequence that can be reconstructed from seqs and it is the org sequence.
Example 1:
Input:
org: [1,2,3], seqs: [[1,2],[1,3]]

Output:
false

Explanation:
[1,2,3] is not the only one sequence that can be reconstructed, because [1,3,2] is also a valid sequence that can be reconstructed.
Example 2:
Input:
org: [1,2,3], seqs: [[1,2]]

Output:
false

Explanation:
The reconstructed sequence can only be [1,2].
Example 3:
Input:
org: [1,2,3], seqs: [[1,2],[1,3],[2,3]]

Output:
true

Explanation:
The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].
Example 4:
Input:
org: [4,1,5,2,6,3], seqs: [[5,2,6,3],[4,1,5,2]]

Output:
true

 */
package greedy.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by poorvank.b on 12/03/17.
 */
public class SequenceReconstruction {

    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {

        int[] inDegree = new int[org.length];
        List<List<Integer>> lists = new ArrayList<>(org.length);

        for (int i=0;i<org.length;i++) {
            lists.add(i,new ArrayList<>());
        }

        /*
            For [1,2,3]
            1 is dependent on 2, 2 is dependent on 3, and 1 is also dependent on 3
         */
        for (List<Integer> seq : seqs) {
            for (int i=0;i<seq.size()-1;i++) {
                lists.get(seq.get(i)).add(seq.get(i+1));
                inDegree[seq.get(i+1)]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i=0;i<org.length;i++) {
            if(inDegree[i]==0) {
                queue.add(i);
            }
        }

        int count = 0;
        while (!queue.isEmpty()) {
            //Only one node should have no indegree at any point of time.
            if(queue.size()>1) {
                return false;
            }
            int current = queue.poll();
            //Checking if the current matches the org element
            if(current!=org[count++]) {
                return false;
            }
            for (Integer next : lists.get(current)) {
                if(--inDegree[next]==0) {
                    queue.add(next);
                }
            }

        }

        return count==org.length;

    }

}

/*

The overall time complexity of this basic algorithm is O(n+m). The O(n) comes from the number of times that the while loop (and initial for loop)
is executed, and the O(m) from the nested for loop.
Although there is no way to calculate how many times the inner loop will be executed on any one iteration of the outer loop, it will only
be executed once for each successor of each member, which means that the total number of times that it will be executed is the total number
of successors of all the members -- or the total number of relations.


Space complexity is also O(n+m). The O(n) component comes from the predecessor count information stored for each member, and the maximum length
of the auxiliary queue. The O(m) comes from storing the successors for each member; once again, the total number of successors is the number of
relations, so O(m)

 */