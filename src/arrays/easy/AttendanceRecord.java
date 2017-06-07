/*

You are given a string representing an attendance record for a student. The record only contains the following three characters:

'A' : Absent.
'L' : Late.
'P' : Present.
A student could be rewarded if his attendance record doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).

You need to return whether the student could be rewarded according to his attendance record.

Example 1:
Input: "PPALLP"
Output: True
Example 2:
Input: "PPALLL"
Output: False

 */
package arrays.easy;

/**
 * Created by poorvank on 26/05/17.
 */
public class AttendanceRecord {

    public boolean checkRecord(String s) {

        int countA = 0;
        int n = s.length();

        for (int i=0;i<n;i++) {
            if(s.charAt(i)=='A') {
                countA++;
                if(countA>1) {
                    return false;
                }
            }

            if(i>=2 && s.charAt(i)=='L' && s.charAt(i-1)=='L' && s.charAt(i-2)=='L') {
                return false;
            }
        }

        return true;

    }

}
