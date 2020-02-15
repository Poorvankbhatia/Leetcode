/*

Given two numbers, hour and minutes. Return the smaller angle (in sexagesimal units) formed between the hour and the minute hand.

Input: hour = 12, minutes = 30
Output: 165

Input: hour = 3, minutes = 30
Output: 75

Input: hour = 3, minutes = 15
Output: 7.5

Input: hour = 4, minutes = 50
Output: 155

Input: hour = 12, minutes = 0
Output: 0


Constraints:

1 <= hour <= 12
0 <= minutes <= 59
Answers within 10^-5 of the actual value will be accepted as correct.

 */
package math.medium;

public class HandsOfClock {

    public double angleClock(int hour, int minutes) {

        // Covers 360 deg in 60 min. So 6 deg in one min.
        double minDegree = (6*(double)(minutes));

        // Covers 360 deg in 12 hours i.e 720 min, so covers 1/2 deg in 1 min.
        double hourDegree = (60*(double)hour)/2 + ((double)minutes / 2);

        double val = Math.abs(hourDegree-minDegree);

        return (Math.min(val,(360-val)));

    }

    public static void main(String[] args) {
        System.out.println(new HandsOfClock().angleClock(1,57));
    }

}
