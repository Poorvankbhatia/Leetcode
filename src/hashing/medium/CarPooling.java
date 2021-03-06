/*

You are driving a vehicle that has capacity empty seats initially available for passengers.
The vehicle only drives east (ie. it cannot turn around and drive west.)

Given a list of trips, trip[i] = [num_passengers, start_location, end_location] contains information about the i-th trip:
the number of passengers that must be picked up, and the locations to pick them up and drop them off.
The locations are given as the number of kilometers due east from your vehicle's initial location.

Return true if and only if it is possible to pick up and drop off all passengers for all the given trips.



Example 1:

Input: trips = [[2,1,5],[3,3,7]], capacity = 4
Output: false
Example 2:

Input: trips = [[2,1,5],[3,3,7]], capacity = 5
Output: true
Example 3:

Input: trips = [[2,1,5],[3,5,7]], capacity = 3
Output: true
Example 4:

Input: trips = [[3,2,7],[3,7,9],[8,3,9]], capacity = 11
Output: true



Constraints:

trips.length <= 1000
trips[i].length == 3
1 <= trips[i][0] <= 100
0 <= trips[i][1] < trips[i][2] <= 1000
1 <= capacity <= 100000

 */
package hashing.medium;

import java.util.Map;
import java.util.TreeMap;

public class CarPooling {

    public boolean carPooling(int[][] trips, int capacity) {
        Map<Integer, Integer> m = new TreeMap<>();
        for (int[] t : trips) {
            m.put(t[1], m.getOrDefault(t[1], 0) + t[0]);
            m.put(t[2], m.getOrDefault(t[2], 0) - t[0]);
        }
        for (int v : m.values()) {
            capacity -= v;
            if (capacity < 0) {
                return false;
            }
        }
        return true;
    }

}
/*

SIMILAR TO MY CALENDER.

Save all time points and the change on current capacity
Sort all the changes on the key of time points.
Track the current capacity and return false if negative
Complexity
Time O(NlogN)
Space O(N)

O(N) sol:

public boolean carPooling(int[][] trips, int capacity) {
        int[] arr = new int[10001];
        for(int[] trip : trips){
            arr[trip[1]]+=trip[0];
            arr[trip[2]]-=trip[0];
        }
        int sum=0;
        for(int i=0;i<10001;i++) {
            sum+=arr[i];
            if(sum>capacity) {
                return false;
            }
        }
        return true;
    }

Since we only have 1,001 stops, we can just figure out how many people get it and out in each location.

Solution
Process all trips, adding passenger count to the start location, and removing it from the end location.
After processing all trips, a positive value for the specific location tells that we are getting more passengers; negative - more empty seats.

Finally, scan all stops and check if we ever exceed our vehicle capacity.

 */
