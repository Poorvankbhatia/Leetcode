/*

Given n orders, each order consist in pickup and delivery services.

Count all valid pickup/delivery possible sequences such that delivery(i) is always after of pickup(i).

Since the answer may be too large, return it modulo 10^9 + 7.



Example 1:

Input: n = 1
Output: 1
Explanation: Unique order (P1, D1), Delivery 1 always is after of Pickup 1.
Example 2:

Input: n = 2
Output: 6
Explanation: All possible orders:
(P1,P2,D1,D2), (P1,P2,D2,D1), (P1,D1,P2,D2), (P2,P1,D1,D2), (P2,P1,D2,D1) and (P2,D2,P1,D1).
This is an invalid order (P1,D2,P2,D1) because Pickup 2 is after of Delivery 2.
Example 3:

Input: n = 3
Output: 90


Constraints:

1 <= n <= 500

 */
package dyanamicprogramming.hard;

public class ValidPickupsAndDelivery {

    private int mod = (int) Math.pow(10,9) + 7;
    long[] dp = new long[501];
    public int countOrders(int n) {
        dp[1]=2L;
        dp[2]=6L;
        for (int i=3;i<=n;i++) {
            int spaceCount = (i-1)*2 + 1;
            long val = (spaceCount)*(spaceCount+1)/2;
            dp[i] = (dp[i-1]*val)%mod;
        }
        return (int) dp[n];
    }

    public static void main(String[] args) {
        System.out.println(new ValidPickupsAndDelivery().countOrders(3));
    }

}


/*

Pickup needs to be before Delivery.
Consider one of the valid cases for 2 pickups and 2 deliveries:

P1 P2 D1 D2.

Now lets Try to place P3 D3 within the above sequence:

Since we have 4 parts to this ( 2 pickups & 2 deliveries) we can insert (P3D3) at any of the below 5 spaces:

__ P1 __ P2 __ D1 __ D2 __
1     2     3     4     5

If we place (P3 D3) at space 1 :  we can have five combinations: 5
If we place (P3 D3) at space 2 :  we can have four combinations: 4
If we place (P3 D3) at space 3 :  we can have three combinations: 3
If we place (P3 D3) at space 4 :  we can have two combinations: 2
If we place (P3 D3) at space 5 :  we can have two combinations: 1

Total (5+4+3+2+1) = 15 => (5*(5+1)/2).. sum from 1 to n is (n*(n+1)/2);

Since with 2 pickups and 2 deliveries we can have 6 combinations, hence adding (P3D3) will result in :
(15*6) = 90 combinations.

Hence we first count the spaces, the calculate the number of times we can insert the new (Pi,Di) and finally multiply it with the existing combinations
to get our answer.



 */