/*

Implement a MyCalendarTwo class to store your events. A new event can be added if adding the event will not cause a triple booking.

Your class will have one method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end),
 the range of real numbers x such that start <= x < end.

A triple booking happens when three events have some non-empty intersection (ie., there is some time that is common to all 3 events.)

For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully without causing a triple booking.
Otherwise, return false and do not add the event to the calendar.

Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
Example 1:
MyCalendar();
MyCalendar.book(10, 20); // returns true
MyCalendar.book(50, 60); // returns true
MyCalendar.book(10, 40); // returns true
MyCalendar.book(5, 15); // returns false
MyCalendar.book(5, 10); // returns true
MyCalendar.book(25, 55); // returns true
Explanation:
The first two events can be booked.  The third event can be double booked.
The fourth event (5, 15) can't be booked, because it would result in a triple booking.
The fifth event (5, 10) can be booked, as it does not use time 10 which is already double booked.
The sixth event (25, 55) can be booked, as the time in [25, 40) will be double booked with the third event;
the time [40, 50) will be single booked, and the time [50, 55) will be double booked with the second event.
Note:

The number of calls to MyCalendar.book per test case will be at most 1000.
In calls to MyCalendar.book(start, end), start and end are integers in the range [0, 10^9].

 */
package tree.medium;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by poorvank.b on 20/11/17.
 */
public class MyCalender2 {

    TreeMap<Integer,Integer> map;
    public MyCalender2() {
        map = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        map.put(start, map.getOrDefault(start, 0) + 1);
        map.put(end, map.getOrDefault(end, 0) - 1);
        int booked = 0;
        for (Map.Entry<Integer,Integer> entry : map.entrySet()) {
            booked += entry.getValue();
            if (booked == 3) {
                map.put(start, map.get(start) - 1);
                map.put(end, map.get(end) + 1);
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        MyCalender2 myCalender = new MyCalender2();
        myCalender.book(10,20);
        myCalender.book(50,60);
        myCalender.book(10,15);
        myCalender.book(5,15);
    }

}
/*

For each time point, store how the number of booked events changes. For each booking attempt, book it and undo the booking if it
causes a triple booking (as determined by going through the time line, keeping track of the number of booked events).

Time Complexity: O(N^2), where N is the number of events booked.



This is more efficient:

for (Map.Entry entry : map.entrySet()) {
    Object key = entry.getKey();
    Object value = entry.getValue();
}
than:

for (Object key : map.keySet()) {
    Object value = map.get(key);
}
Because in the second case, for every key in the keySet the map.get() method is called,
which - in the case of a HashMap - requires that the hashCode() and equals() methods of the key object be evaluated in order to find the associated value.
In the first case that extra work is eliminated.

Edit: This is even worse if you consider a TreeMap, where a call to get is O(log2(n)), i.e.
the comparator for will may need to run log2(n) times (n = size of the Map) before finding the associated value.

 */