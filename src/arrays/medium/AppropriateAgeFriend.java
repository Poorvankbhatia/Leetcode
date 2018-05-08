/*

Some people will make friend requests. The list of their ages is given and ages[i] is the age of the ith person.

Person A will NOT friend request person B (B != A) if any of the following conditions are true:

age[B] <= 0.5 * age[A] + 7
age[B] > age[A]
age[B] > 100 && age[A] < 100
Otherwise, A will friend request B.

Note that if A requests B, B does not necessarily request A.  Also, people will not friend request themselves.

How many total friend requests are made?

Example 1:

Input: [16,16]
Output: 2
Explanation: 2 people friend request each other.
Example 2:

Input: [16,17,18]
Output: 2
Explanation: Friend requests are made 17 -> 16, 18 -> 17.
Example 3:

Input: [20,30,100,110,120]
Output:
Explanation: Friend requests are made 110 -> 100, 120 -> 110, 120 -> 100.


Notes:

1 <= ages.length <= 20000.
1 <= ages[i] <= 120.


 */
package arrays.medium;

/**
 * Created by poorvank.b on 29/04/18.
 */
public class AppropriateAgeFriend {

    public int numFriendRequests(int[] ages) {

        int[] count = new int[121];
        for (int age: ages){
            count[age]++;
        }

        int ans = 0;
        for (int ageA = 0; ageA <= 120; ageA++) {
            int countA = count[ageA];
            for (int ageB = 0; ageB <= 120; ageB++) {
                int countB = count[ageB];
                if (ageA * 0.5 + 7 >= ageB || (ageA < ageB) || (ageA < 100 && 100 < ageB)) {
                    continue;
                }
                ans += countA * countB;
                if (ageA == ageB) {
                    ans -= countA;
                }
            }
        }

        return ans;

    }

    public static void main(String[] args) {
        int[] arr = new int[]{16,16,16};
        System.out.println(new AppropriateAgeFriend().numFriendRequests(arr));
    }

}

/*

Intuition

Instead of processing all 20000 people, we can process pairs of (age, count) representing how many people are that age.
Since there are only 120 possible ages, this is a much faster loop.

Algorithm

For each pair (ageA, countA), (ageB, countB), if the conditions are satisfied with respect to age, then countA * countB pairs of
people made friend requests.

If ageA == ageB, then we overcounted: we should have countA * (countA - 1) pairs of people making friend requests instead, as you
cannot friend request yourself.

Time Complexity: O(A​2+N), where NN is the number of people, and A is the number of ages.

Space Complexity: O(A), the space used to store count.



 */