/*

Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k),
where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h.
 Write an algorithm to reconstruct the queue.


Note:
The number of people is less than 1,100.

Example

Input:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

Output:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]

 */
package greedy.medium;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by poorvank on 19/12/16.
 */
public class QueueReconstruction {

    public int[][] reconstructQueue(int[][] people) {

        Arrays.sort(people, (o1, o2) -> o1[0]!=o2[0]?-o1[0]+o2[0]:o1[1]-o2[1]);


        List<int[]> list = new LinkedList<>();
        for (int[] p : people) {
            if(p[1]>=list.size()) {
                list.add(p);
            } else {
                list.add(p[1],p);
            }
        }

        return list.toArray(new int[people.length][]);

    }

}

/*

Pick out tallest group of people and sort them in a subarray (S). Since there's no other groups of people taller than them,
therefore each guy's index will be just as same as his k value.
For 2nd tallest group (and the rest), insert each one of them into (S) by k value. So on and so forth.
E.g.
input: [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
subarray after step 1: [[7,0], [7,1]]
subarray after step 2: [[7,0], [6,1], [7,1]]


 */
