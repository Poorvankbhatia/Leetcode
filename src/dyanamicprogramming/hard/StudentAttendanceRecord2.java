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

        int[] endsWithA = new int[n+1]; // total number of all possible attendance records ended with 'A' with length n.
        int[] endsWithP = new int[n+1]; //  total number of all possible attendance records ended with 'P' with length n.
        int[] endsWithL = new int[n+1]; //  total number of all possible attendance records ended with 'L' with length n.
        int[] endsWithPNoA = new int[n+1]; // total number of all possible attendance records ended with 'P' with length n and with no 'A'.
        int[] endsWithLNoA = new int[n+1]; // total number of all possible attendance records ended with 'L' with length n and with no 'A'.

        if(n==1) {
            return 3;
        }

        if(n==0) {
            return 0;
        }

        endsWithA[0] = 0;
        endsWithA[1] = 1;
        endsWithA[2] = 2;

        endsWithP[0] = 0;
        endsWithP[1] = 1;
        endsWithP[2] = 3;

        endsWithL[0] = 0;
        endsWithL[1] = 1;
        endsWithL[2] = 3;

        endsWithPNoA[0] = 0;
        endsWithPNoA[1] = 1;
        endsWithPNoA[2] = 2;

        endsWithLNoA[0] = 0;
        endsWithLNoA[1] = 1;
        endsWithLNoA[2] = 2;



        for (int i=3;i<=n;i++) {

            endsWithA[i-1] %=mod;
            endsWithA[i-2] %=mod;
            endsWithP[i-1] %=mod;
            endsWithL[i-1] %=mod;
            endsWithPNoA[i-2] %=mod;
            endsWithLNoA[i-2] %=mod;
            endsWithLNoA[i-1] %=mod;


            //endsWithA(n) = endsWithPNoA(n - 1) + endsWithLNoA(n - 1)
            endsWithA[i] = (endsWithPNoA[i-1]+endsWithLNoA[i-1])%mod;

            /*
            For an attendance record with length n - 1,

            If its (n - 1)th character is 'P' ---- CAN add 'P'. ("PP")
            If its (n - 1)th character is 'A' ---- CAN add 'P'. ("AP")
            If its (n - 1)th character is 'L' ---- CAN add 'P'. ("LP")
            which means
            endsWithP(n) = endsWithP(n - 1) + endsWithA(n - 1) + endsWithL(n - 1), n ≥ 2.
             */

            endsWithP[i] = ((endsWithA[i-1]+endsWithP[i-1])%mod+endsWithL[i-1])%mod;

            /*
                If its (n - 1)th character is 'P' ---- CAN add 'L'. ("PL")
                If its (n - 1)th character is 'A' ---- CAN add 'L'. ("AL")

                If its (n - 1)th character is 'L':
                If its (n - 2)th character is 'A' ---- CAN add 'L'. ("ALL")
                If its (n - 2)th character is 'P' ---- CAN add 'L'. ("PLL")
                If its (n - 2)th character is 'L' ---- CAN NOT add 'L'. ("LLL" breaks the rule)

                endsWithL(n) = endsWithA(n - 1) + endsWithP(n - 1) + endsWithA(n - 2) + endsWithP(n - 2), n ≥ 3
             */

            endsWithL[i] = ((endsWithA[i-1]+endsWithA[i-2])%mod+(endsWithP[i-1]+endsWithP[i-2])%mod)%mod;
            endsWithPNoA[i] = (endsWithLNoA[i-1]+endsWithPNoA[i-1])%mod;

            /*
            For an attendance record with length n - 1, we can get

            If its (n - 1)th character is 'P' and has no 'A' ---- CAN add 'L'.("PL")
            If its (n - 1)th character is 'L' and has no 'A'.
            If its (n - 2)th character is 'P' and has no 'A' ---- CAN add 'L'.("PLL")
            If its (n - 2)th character is 'L' and has no 'A' ---- CAN NOT add 'L'.("LLL" breaks the rule.)

            endsWithLNoA(n) = endsWithPNoA(n - 1) + endsWithPNoA(n - 2), n ≥ 3.
             */
            endsWithLNoA[i] = (endsWithPNoA[i-1]+endsWithPNoA[i-2])%mod;

        }


        return ((endsWithA[n]+endsWithP[n])%mod+endsWithL[n])%mod;

    }

    public static void main(String[] args) {
        System.out.println(new StudentAttendanceRecord2().checkRecord(0));
    }

}
