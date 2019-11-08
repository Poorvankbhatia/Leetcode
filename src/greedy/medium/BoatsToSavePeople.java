/*

The i-th person has weight people[i], and each boat can carry a maximum weight of limit.

Each boat carries at most 2 people at the same time, provided the sum of the weight of those people is at most limit.

Return the minimum number of boats to carry every given person.  (It is guaranteed each person can be carried by a boat.)



Example 1:

Input: people = [1,2], limit = 3
Output: 1
Explanation: 1 boat (1, 2)
Example 2:

Input: people = [3,2,2,1], limit = 3
Output: 3
Explanation: 3 boats (1, 2), (2) and (3)
Example 3:

Input: people = [3,5,3,4], limit = 5
Output: 4
Explanation: 4 boats (3), (3), (4), (5)
Note:

1 <= people.length <= 50000
1 <= people[i] <= limit <= 30000

 */
package greedy.medium;

import java.util.Arrays;

/**
 * Created by poorvank.b on 14/08/18.
 */
public class BoatsToSavePeople {

    public int numRescueBoats(int[] people, int limit) {

        int n = people.length;
        Arrays.sort(people);
        int start = 0;
        int end = n-1;
        int c = 0;
        while(start<=end) {
            int sum = people[start]+people[end];
            c++;
            if(sum<=limit) {
                start++;
                end--;
            } else {
                end--;
            }
        }
        return c;

    }

}

/*

Test case : [5,1,4,2] 6

We sorted the people by their weight......then there are two cases:

CASE-I : "minimum weight" man can't sit with "maximum weight" man
it implies that "maximum weight" man can't sit with any other man
hence "maximum weight" man sits alone so decrement only high end pointer.

CASE-II : "minimum weight" man can sit with "maximum weight" man
it implies that "minimum weight" man can sit with any other man
since we want to maximize the amount of weight carried by a single boat we sit "minimum weight" man and "maximum weight" man together.
so increment low end pointer and decrement high end pointer.


O(n) Sol:

public int numRescueBoats(int[] people, int limit) {
        int[] buckets = new int[limit+1];
        for(int p: people) buckets[p]++;
        int start = 0;
        int end = buckets.length - 1;
        int solution = 0;
        while(start <= end) {
			//make sure the start always point to a valid number
            while(start <= end && buckets[start] <= 0) start++;
			//make sure end always point to valid number
            while(start <= end && buckets[end] <= 0) end--;
			//no one else left on the ship, hence break.
            if(buckets[start] <= 0 && buckets[end] <= 0) break;
            solution++;
            if(start + end <= limit) buckets[start]--; // both start and end can carry on the boat
            buckets[end]--;
        }
        return solution;
    }

    "It is guaranteed each person can be carried by a boat",
    we know that the number in the array will not be larger than limit;
    Hence, it is suitable for bucket sort. Once we sort the array, the remain logic is the same.
    The only difference is that we need make sure start and end are pointing to a valid person, since the bucket might be 0;

    Time: O(n)
Space: O(limit)

 */