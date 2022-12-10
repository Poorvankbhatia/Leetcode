/*

You are given an integer finalSum. Split it into a sum of a maximum number of unique positive even integers.

For example, given finalSum = 12, the following splits are valid (unique positive even integers summing up to finalSum):
 (12), (2 + 10), (2 + 4 + 6), and (4 + 8). Among them, (2 + 4 + 6) contains the maximum number of integers.
 Note that finalSum cannot be split into (2 + 2 + 4 + 4) as all the numbers should be unique.
Return a list of integers that represent a valid split containing a maximum number of integers. If no valid
split exists for finalSum, return an empty list. You may return the integers in any order.



Example 1:

Input: finalSum = 12
Output: [2,4,6]
Explanation: The following are valid splits: (12), (2 + 10), (2 + 4 + 6), and (4 + 8).
(2 + 4 + 6) has the maximum number of integers, which is 3. Thus, we return [2,4,6].
Note that [2,6,4], [6,2,4], etc. are also accepted.
Example 2:

Input: finalSum = 7
Output: []
Explanation: There are no valid splits for the given finalSum.
Thus, we return an empty array.
Example 3:

Input: finalSum = 28
Output: [6,8,2,12]
Explanation: The following are valid splits: (2 + 26), (6 + 8 + 2 + 12), and (4 + 24).
(6 + 8 + 2 + 12) has the maximum number of integers, which is 4. Thus, we return [6,8,2,12].
Note that [10,2,4,12], [6,2,4,16], etc. are also accepted.


Constraints:

1 <= finalSum <= 1010

 */
package backtracking.medium;

import java.util.ArrayList;
import java.util.List;

public class MaximumSplitPositiveEvenIntegers {

    public List<Long> maximumEvenSplit(long finalSum) {
        List<Long> res = new ArrayList<Long>();
        //odd sum cannot be divided into even numbers
        if(finalSum % 2 != 0) {
            return res;
        }
        //Greedy approach, try to build the total sum using minimum unique even nos
        long currNum = 2;
        long remainingSum = finalSum;
        //as long as we can add subtract this number from remaining sum
        while(currNum <= remainingSum) {
            res.add(currNum);
            remainingSum -= currNum;//reducing remaining sum
            currNum += 2;//next even number
        }
        //now, remaining sum cannot be fulfilled by any larger even number
        //so extract the largest even number we added to the last index of res, and make it even larger
        // by adding this current remaining sum
        //add remaining sum to the last element
        long last = res.remove(res.size()-1);
        res.add(last+remainingSum);
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new MaximumSplitPositiveEvenIntegers().maximumEvenSplit(691401768));
    }

}
/*

Assume there are totally i iterations, so 2 + 4 + ... + 2 * i = (2 + 2 * i) * i / 2 = i * (i + 1) = i + i2 ~
finalSum, get rid of the minor term i, we have i2 ~ finalSum, which means i ~ finalSum0.5; therefore

Time: O(n0.5), where n = finalSum, space: O(1)- excluding return space.

To elaborate on the analysis done above: the sum of i consecutive integers is i*(i+1)/2.
So the sum of consecutive EVEN integers requires us to multiply this expression by two giving us i*(i+1).

 */