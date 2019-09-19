/*

Given a date, return the corresponding day of the week for that date.

The input is given as three integers representing the day, month and year respectively.

Return the answer as one of the following values {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}.



Example 1:

Input: day = 31, month = 8, year = 2019
Output: "Saturday"
Example 2:

Input: day = 18, month = 7, year = 1999
Output: "Sunday"
Example 3:

Input: day = 15, month = 8, year = 1993
Output: "Sunday"


Constraints:

The given dates are valid dates between the years 1971 and 2100.

 */
package math.hard;

public class DayOfTheWeek {

    public String dayOfTheWeek(int day, int month, int year) {
        String[] week = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        int[] daysOfMonth = {31,28,31,30,31,30,31,31,30,31,30,31};
        int num = 0;
        for(int i = 1971;i<year;i++){
            if(i % 4 == 0) num += 366;
            else num += 365;
        }
        if(year % 4 == 0) daysOfMonth[1] = 29;
        for(int i = 0;i<month-1;i++){
            num += daysOfMonth[i];
        }
        num += day - 1;
        return week[(num + 5)%7];
    }

}
