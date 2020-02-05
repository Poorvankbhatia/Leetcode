/*

Given a positive integer n, return the number of all possible attendance records with length n,
which will be regarded as rewardable. The answer may be very large, return it after mod 10^9 + 7.

A student attendance record is a string that only contains the following three characters:

'A' : Absent.
'L' : Late.
'P' : Present.
A record is regarded as rewardable if it doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).

Example 1:
Input: n = 2
Output: 8
Explanation:
There are 8 records with length 2 will be regarded as rewardable:
"PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
Only "AA" won't be regarded as rewardable owing to more than one absent times.

 */
package dyanamicprogramming.hard;

/**
 * Created by poorvank on 28/05/17.
 */
public class StudentAttendanceRecord2 {

    private int mod = 1000000007;
    public int checkRecord(int n) {
        long[] EndingWithP = new long[n+1];
        long[] EndingWithL = new long[n+1];
        EndingWithP[0]=1L;
        EndingWithL[0]=0L;
        EndingWithP[1]=1L;
        EndingWithL[1]=1L;
        for(int i=2;i<=n;i++) {
            EndingWithP[i]=(EndingWithP[i-1]+EndingWithL[i-1])%mod;
            EndingWithL[i]=(EndingWithP[i-1]+EndingWithP[i-2])%mod;
        }
        long ans = (EndingWithP[n]+EndingWithL[n])%mod;
        for(int i=0;i<n;i++) {
            long temp = ((EndingWithP[i]+EndingWithL[i])%mod * (EndingWithP[n-i-1]+EndingWithL[n-i-1])%mod)%mod;
            ans=(ans+temp)%mod;
        }
        return (int)ans;
    }

    public static void main(String[] args) {
        System.out.println(new StudentAttendanceRecord2().checkRecord(0));
    }

}
