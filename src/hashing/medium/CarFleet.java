/*

N cars are going to the same destination along a one lane road.  The destination is target miles away.

Each car i has a constant speed speed[i] (in miles per hour), and initial position position[i] miles towards the target along the road.

A car can never pass another car ahead of it, but it can catch up to it, and drive bumper to bumper at the same speed.

The distance between these two cars is ignored - they are assumed to have the same position.

A car fleet is some non-empty set of cars driving at the same position and same speed.  Note that a single car is also a car fleet.

If a car catches up to a car fleet right at the destination point, it will still be considered as one car fleet.


How many car fleets will arrive at the destination?



Example 1:

Input: target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]
Output: 3
Explanation:
The cars starting at 10 and 8 become a fleet, meeting each other at 12.
The car starting at 0 doesn't catch up to any other car, so it is a fleet by itself.
The cars starting at 5 and 3 become a fleet, meeting each other at 6.
Note that no other cars meet these fleets before the destination, so the answer is 3.

Note:

0 <= N <= 10 ^ 4
0 < target <= 10 ^ 6
0 < speed[i] <= 10 ^ 6
0 <= position[i] < target
All initial positions are different.

 */
package hashing.medium;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by poorvank.b on 20/08/18.
 */
public class CarFleet {

    public int carFleet(int target, int[] position, int[] speed) {

        Map<Integer,Integer> map = new TreeMap<>();

        for (int i=0;i<position.length;i++) {
            map.put(target-position[i],speed[i]);
        }

        double max = -1.0;
        int fleetCount = 0;

        for (Map.Entry<Integer,Integer> entry : map.entrySet()) {
            int distance = entry.getKey();
            int s = entry.getValue();
            double val = (1.0)*distance/s;
            if(val>max) {
                max = val;
                fleetCount++;
            }
        }

        return fleetCount;
    }

}

/*

O(N) solution without sorting.

Calculate the number of steps for each car required to reach the destination. Number of steps should be double because of
the INCORRECT requirements (look at the problem's rating). Caculated values inserted into 0-based array to avoid sorting.
Loop through array from end to start and check if the next value is bigger than previous.
If the value is bigger it means it is the next slower car.

class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        int fleets = 0;
        double max = -1;

        double[] distribution = new double[target + 1];

        Arrays.fill(distribution, -1);

        for (int i = 0; i < position.length; i++) {
            distribution[position[i]] = (double) (target - position[i]) / speed[i];
        }

        for (int i = distribution.length -1; i >= 0; i--) {
            if (distribution[i] > max) {
                max = distribution[i];
                fleets++;
            }
        }

        return fleets;
    }
}

 */
