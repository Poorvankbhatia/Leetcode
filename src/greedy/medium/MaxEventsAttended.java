/*

You are given an array of events where events[i] = [startDayi, endDayi]. Every event i starts at startDayi
and ends at endDayi.

You can attend an event i at any day d where startTimei <= d <= endTimei. You can only attend one event at
any time d.

Return the maximum number of events you can attend.

Input: events = [[1,2],[2,3],[3,4]]
Output: 3
Explanation: You can attend all the three events.
One way to attend them all is as shown.
Attend the first event on day 1.
Attend the second event on day 2.
Attend the third event on day 3.
Example 2:

Input: events= [[1,2],[2,3],[3,4],[1,2]]
Output: 4


Constraints:

1 <= events.length <= 105
events[i].length == 2
1 <= startDayi <= endDayi <= 105

 */
package greedy.medium;

import java.util.*;

public class MaxEventsAttended {

    public int maxEvents(int[][] events) {
        Arrays.sort(events, Comparator.comparingInt(a -> a[0])); // sort events increasing by start time\
        PriorityQueue<Integer> queue =new PriorityQueue<>();
        int i = 0;
        int ans = 0;
        int d = 1;
        while(!queue.isEmpty() || i < events.length){
            while( i < events.length && events[i][0] == d){
                queue.add(events[i++][1]); // add end date
            }
            while(!queue.isEmpty() && queue.peek() < d){
                queue.poll();
            }
            if(!queue.isEmpty()){
                ans++;
                queue.poll();
            }
            ++d;
        }
        return ans;
    }

}

/*
#1. Sort the events based on starting day of the event
#2. Now once you have this sorted events, every day check what are the events that can start today
#3. for all the events that can be started today, keep their ending time in heap.

- Wait why we only need ending times ?
i) from today onwards, we already know this event started in the past and all we need to know is when this event will finish
ii) Also, another key to this algorithm is being greedy, meaning I want to pick the event which is going to end the soonest.
- So how do we find the event which is going to end the soonest?
i) brute force way would be to look at all the event's ending time and find the minimum, this is probably ok for
1 day but as we can only attend 1 event a day,
we will end up repeating this for every day and that's why we can utilize heap(min heap to be precise)
to solve the problem of finding the event with earliest ending time
#4. There is one more house cleaning step, the event whose ending time is in the past, we no longer can attend
those events
#5. Last but very important step, Let's attend the event if any event to attend in the heap.
 */