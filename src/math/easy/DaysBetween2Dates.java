/*
Write a program to count the number of days between two dates.

The two dates are given as strings, their format is YYYY-MM-DD as shown in the examples.



Example 1:

Input: date1 = "2019-06-29", date2 = "2019-06-30"
Output: 1
Example 2:

Input: date1 = "2020-01-15", date2 = "2019-12-31"
Output: 15


Constraints:

The given dates are valid dates between the years 1971 and 2100.
 */
package math.easy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DaysBetween2Dates {

    public int daysBetweenDates(String date1, String date2) {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date d1 = myFormat.parse(date1);
            Date d2 = myFormat.parse(date2);
            long diff = d2.getTime() - d1.getTime();
            return Math.abs((int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }
    }

}
