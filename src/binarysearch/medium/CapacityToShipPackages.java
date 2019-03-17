/*

A conveyor belt has packages that must be shipped from one port to another within D days.

The i-th package on the conveyor belt has a weight of weights[i].  Each day, we load the ship with packages on the conveyor belt
(in the order given by weights). We may not load more weight than the maximum weight capacity of the ship.

Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within D days.



Example 1:

Input: weights = [1,2,3,4,5,6,7,8,9,10], D = 5
Output: 15
Explanation:
A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
1st day: 1, 2, 3, 4, 5
2nd day: 6, 7
3rd day: 8
4th day: 9
5th day: 10

Note that the cargo must be shipped in the order given, so using a ship of capacity 14 and splitting the packages into parts like
(2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed.
Example 2:

Input: weights = [3,2,2,4,1,4], D = 3
Output: 6
Explanation:
A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:
1st day: 3, 2
2nd day: 2, 4
3rd day: 1, 4
Example 3:

Input: weights = [1,2,3,1,1], D = 4
Output: 3
Explanation:
1st day: 1
2nd day: 2
3rd day: 3
4th day: 1, 1


Note:

1 <= D <= weights.length <= 50000
1 <= weights[i] <= 500

 */
package binarysearch.medium;

public class CapacityToShipPackages {

    public int shipWithinDays(int[] weights, int D) {
        if(null == weights || weights.length==0) {
            return 0;
        }

        int lo = getMax(weights);
        int hi = getSum(weights);

        while (lo<hi) {
            int mid = lo + (hi-lo)/2;
            int currentPartitions  = getPartitionCount(weights,mid);
            if(currentPartitions<=D) {
                hi = mid;
            } else {
                lo = mid+1;
            }
        }

        return lo;
    }

    private int getPartitionCount(int[] weights,int val) {
        int total = 0,count = 1;
        for (Integer element : weights) {
            total += element;
            if(total>val) {
                total = element;
                count++;
            }
        }
        return count;
    }


    private int getMax(int[] weights) {
        int max=Integer.MIN_VALUE;
        for (Integer element: weights) {
            if(max<element) {
                max = element;
            }
        }

        return max;
    }


    private int getSum(int[] weights) {
        int sum = 0;
        for (Integer element : weights) {
            sum += element;
        }
        return sum;
    }

}

/*

410. Split Array Largest Sum

Another solution :

class Solution {

    public int shipWithinDays(int[] weights, int D) {
       int max = weights[0];
	    // Find max weight
        for (int x : weights) max = Math.max(max, x);

        int[] ship;
		// Start from minimum possible size to maximum possible
        for (int i = max; i <= weights.length * max; i++) {
            ship = new int[D];
            int index = 0;
			// Fill all the ships
            for (int j = 0; j < ship.length; j++) {
				// Try to fit as many items as possible
                while (index < weights.length && ship[j] + weights[index] < i) {
                    ship[j] += weights[index];
                    index++;
                }
            }
            if (index == weights.length) return i-1;
        }
        return 0;
    }
}

Step1: Find the minimum possible weight i.e. the largest value in the array.
Step2: Now use it as max limit for a ship and try to fit items.
Step3: If all the items are filled. You have your answer. if not increment the current weight and goto Step2.

 */