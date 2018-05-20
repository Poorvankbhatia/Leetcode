/*

Winter is coming! Your first job during the contest is to design a standard heater with fixed warm radius to warm all the houses.

Now, you are given positions of houses and heaters on a horizontal line, find out minimum radius of heaters so that all houses could be covered by those heaters.

So, your input will be the positions of houses and heaters seperately, and your expected output will be the minimum radius standard of heaters.

Note:
Numbers of houses and heaters you are given are non-negative and will not exceed 25000.
Positions of houses and heaters you are given are non-negative and will not exceed 10^9.
As long as a house is in the heaters' warm radius range, it can be warmed.
All the heaters follow your radius standard and the warm radius will the same.
Example 1:
Input: [1,2,3],[2]
Output: 1
Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.
Example 2:
Input: [1,2,3,4],[1,4]
Output: 1
Explanation: The two heater was placed in the position 1 and 4. We need to use radius 1 standard, then all the houses can be warmed.

 */
package binarysearch.easy;

import java.util.Arrays;

/**
 * Created by poorvank.b on 14/05/18.
 */
public class Heaters {

    public int findRadius(int[] houses, int[] heaters) {

        if(houses==null || houses.length==0) {
            return 0;
        }

        Arrays.sort(heaters);

        int result=0;

        for (int house : houses) {

            int ceil = findCeil(heaters,house,0,heaters.length-1);
            int floor = findFloor(heaters,house,0,heaters.length-1);

            // It means this is a heater
            if(ceil==-1 && floor==-1) {
                continue;
            }

            int d1 = ceil<0?Integer.MAX_VALUE:ceil-house;
            int d2 = floor<0?Integer.MAX_VALUE:house-floor;

            result = Math.max(result,Math.min(d1,d2));

        }

        return result;

    }

    private int findCeil(int[] heaters,int house,int start,int end) {

        if(start<=end) {

            int mid = start+(end-start)/2;
            if(heaters[mid]>house && (mid==start || heaters[mid-1]<house)) {
                return heaters[mid];
            } else if(heaters[mid]<house) {
                return findCeil(heaters,house,mid+1,end);
            }
            return findCeil(heaters,house,start,mid-1);

        }

        return -1;

    }

    private int findFloor(int[] heaters,int house,int start,int end) {

        if(start<=end) {

            int mid = start+(end-start)/2;
            if(heaters[mid]<house && (mid==end || heaters[mid+1]>house)) {
                return heaters[mid];
            } else if(heaters[mid]>house) {
                return findFloor(heaters,house,start,mid-1);
            }
            return findFloor(heaters,house,mid+1,end);

        }

        return -1;

    }

}

/*

For each house, find its position between those heaters (thus we need the heaters array to be sorted).
Calculate the distances between this house and left heater and right heater, get a MIN value of those two values. Corner cases are there is no left or right heater.
Get MAX value among distances in step 2. It's the answer.

Time complexity: max(O(nlogn), O(mlogn)) - m is the length of houses, n is the length of heaters.

 */