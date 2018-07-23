/*

In a row of seats, 1 represents a person sitting in that seat, and 0 represents that the seat is empty.

There is at least one empty seat, and at least one person sitting.

Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized.

Return that maximum distance to closest person.

Example 1:

Input: [1,0,0,0,1,0,1]
Output: 2
Explanation:
If Alex sits in the second open seat (seats[2]), then the closest person has distance 2.
If Alex sits in any other open seat, the closest person has distance 1.
Thus, the maximum distance to the closest person is 2.
Example 2:

Input: [1,0,0,0]
Output: 3
Explanation:
If Alex sits in the last seat, the closest person is 3 seats away.
This is the maximum distance possible, so the answer is 3.

 */
package arrays.easy;

import java.util.TreeSet;

/**
 * Created by poorvank.b on 10/06/18.
 */
public class MaximizeDistanceToClosestPerson {

    public int maxDistToClosest(int[] seats) {

        TreeSet<Integer> onePos = new TreeSet<>();

        for (int i=0;i<seats.length;i++) {
            if(seats[i]==1) {
               onePos.add(i);
            }
        }

        int maxDistance=Integer.MIN_VALUE;

        for (int i=0;i<seats.length;i++) {
            if(seats[i]==0) {
                Integer higher = onePos.higher(i);
                Integer lower = onePos.lower(i);
                if(higher==null && maxDistance<i-lower) {
                    maxDistance = i-lower;
                } else if(lower==null && maxDistance<higher-i) {
                    maxDistance = higher-i;
                } else if(higher!=null && lower!=null) {
                    int value = Math.min(higher-i,i-lower);
                    if(value>maxDistance) {
                        maxDistance = value;
                    }
                }
            }
        }

        return maxDistance;

    }

    public static void main(String[] args) {
        int[] arr = new int[]{0,0,1,0,1,0,0,0,0};
        System.out.println(new MaximizeDistanceToClosestPerson().maxDistToClosest(arr));
    }

}

/*

 O(n) sol:

  public int maxDistToClosest(int[] seats) {
        int res = 0,max = Integer.MAX_VALUE,curr = max;
        int[] left = new int[seats.length];
        int[] right = new int[seats.length];
        for(int i=0;i<seats.length;i++){
            if(seats[i] == 1){
                curr = 0;
            }else{
                if(curr != max) curr++;
                left[i] = curr;
            }
        }
        curr = max;
        for(int i=seats.length - 1;i>=0;i--){
            if(seats[i] == 1){
                curr = 0;
            }else{
                if(curr != max) curr++;
                right[i] = curr;
            }
        }
        for(int i=0;i<seats.length;i++){
            if(seats[i] == 0 && Math.min(left[i],right[i]) > res){
                res = Math.min(left[i],right[i]);
            }
        }
        return res;
    }

 */
