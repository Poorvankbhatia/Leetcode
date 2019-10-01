package greedy.hard;

import java.util.Arrays;
import java.util.PriorityQueue;

public class CourseSchedule3 {

    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses,(a,b)->a[1]-b[1]); //Sort the courses by their deadlines (Greedy! We have to deal with courses with early deadlines first)
        PriorityQueue<Integer> pq=new PriorityQueue<>((a,b)->b-a);
        int time=0;
        for (int[] c:courses)
        {
            time+=c[0]; // add current course to a priority queue
            pq.add(c[0]);
            if (time>c[1]) time-=pq.poll(); //If time exceeds, drop the previous course which costs the most time. (That must be the best choice!)
        }
        return pq.size();
    }

}

/*

Approach:
Sort courses by the end date, this way, when we're iterating through the courses, we can switch out any previous course with the current one without worrying about end date.

Next, we iterate through each course, if we have enough days, we'll add it to our priority queue. If we don't have enough days, then we can either
2.1 ignore this course OR
2.2 We can replace this course with the longest course we added earlier.


1. Why we just pop out the last longest course that is screwing total day time?

Because all the courses that we have saw till now all complete before the d day. Hence we can just simply remove any of the course from it.
Ideally we should remove the course which will give the maximum savings(d - t). But since the courses are sorted by d , Just the largest t course
removal will suffice. Hence we can save some time for the next incoming courses

2. When we replace a longer course with a much shorter one, does that mean we'll have enough room to take some courses previously ignored for being too long?

The answer is NO, because any courses we missed would be longer than what's in priority queue. So the increase in number of days cannot be larger than the
largest element in queue, and certainly will be less than a previously ignored course which has to be even longer.

Example : [[10,100],[95,100],[100,5000]]
Here we could take the 10 day course, then the 100 day course & now since more days are available we can take 95 day course
BUT THAT IS WRONG
We need to complete the 95 day course by the 100TH day which will never be possible
So either we can take 10 day one or 95 day one. But it will be only one course possible
(Better take smaller length course so the next courses get time. Thats why Priority queues are used)

 */