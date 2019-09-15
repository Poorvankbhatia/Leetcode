/*

A bus has n stops numbered from 0 to n - 1 that form a circle. We know the distance between all pairs of
neighboring stops where distance[i] is the distance between the stops number i and (i + 1) % n.

The bus goes along both directions i.e. clockwise and counterclockwise.

Return the shortest distance between the given start and destination stops.


Input: distance = [1,2,3,4], start = 0, destination = 1
Output: 1
Explanation: Distance between 0 and 1 is 1 or 9, minimum is 1.

Input: distance = [1,2,3,4], start = 0, destination = 2
Output: 3
Explanation: Distance between 0 and 2 is 3 or 7, minimum is 3.


Input: distance = [1,2,3,4], start = 0, destination = 3
Output: 4
Explanation: Distance between 0 and 3 is 6 or 4, minimum is 4.

 */
package arrays.easy;

public class DistanceBetweenBusStops {

    public int distanceBetweenBusStops(int[] distance, int start, int destination) {

        int n = distance.length;
        int[] left = new int[n];
        int[] right = new int[n];

        left[0]=right[0]=0;
        int sum = distance[0];
        for (int i=1;i<n;i++) {
            left[i] = distance[i-1]+left[i-1];
            sum+=distance[i];
        }

        right[n-1]=distance[n-1];
        for (int i=n-2;i>=1;i--) {
            right[i]=right[i+1]+distance[i];
        }

        if(start == 0) {
            return Math.min(left[destination],right[destination]);
        } else {
            int min = Math.min(start,destination);
            int max = Math.max(start,destination);
            return Math.min((left[min]+right[max]),(sum-(left[min]+right[max])));
        }

    }

}

/*

Another Sol:

public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        if (start > destination) {
            int temp = start;
            start = destination;
            destination = temp;;
        }
        int res = 0, total = 0;
        for (int i = 0; i < distance.length; i++) {
            if (i >= start && i < destination) {
                res += distance[i];
            }
            total += distance[i];
        }
        return Math.min(res, total - res);
    }

 */