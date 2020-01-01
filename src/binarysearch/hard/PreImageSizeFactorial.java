/*

Let f(x) be the number of zeroes at the end of x!. (Recall that x! = 1 * 2 * 3 * ... * x, and by convention, 0! = 1.)

For example, f(3) = 0 because 3! = 6 has no zeroes at the end, while f(11) = 2 because 11! = 39916800 has 2 zeroes at the end.
Given K, find how many non-negative integers x have the property that f(x) = K.

Example 1:
Input: K = 0
Output: 5
Explanation: 0!, 1!, 2!, 3!, and 4! end with K = 0 zeroes.

Example 2:
Input: K = 5
Output: 0
Explanation: There is no x such that x! ends in K = 5 zeroes.
Note:

K will be an integer in the range [0, 10^9].

 */
package binarysearch.hard;

/**
 * Created by poorvank.b on 20/05/18.
 */
public class PreImageSizeFactorial {

    public int preimageSizeFZF(int K) {
        /*
        findRange(K)- All elements factorial <= Kzeroes

        findRange(K-1) -All elements factorial <= K-1 zeroes

        */
        return util(K)-util(K-1);
    }

    private int util(int K) {
        if(K<0) {
            return 0;
        }
        long small = 0;
        long high = Long.MAX_VALUE;
        long res=0;
        while(small<=high) {
            long mid = small+(high-small)/2;
            if(countZeroes(mid)<=K) {
                res=mid;
                small=mid+1;
            } else {
                high = mid-1;
            }
        }
        return (int)res+1; // For 0!
    }

    private long countZeroes(long x) {
        long res = 0;

        while (x>0) {
            res+=(x/5);
            x = x/5;
        }

        return res;
    }

}



/*

Given N - No. of zeroes in N! can be found by N/5, N/25, N/125 so on
So Here use Binary search, from 0 to 10^9 - take mid element and check for No. of Zeroes

Find lower range and Higher Range : in between that is the numbers

Lower Range : Strictly <K zeroes -
Higher Range : Strictly >=K zeroes


No. of zeroes Range once it starts - it will never decrease
	Example
	0! - 4! - 0 zeroes
5! - 9! - 1 zero
10! onwards 2 zeroes, so on


Some basic observations:

The number of trailing zeros of the factorial x! is given by the minimum of num_2 and num_5, where the former is the total number of factor 2
of integers in the range [1, x], while the latter is the total number of factor 5 of these integers. Since we always have more factors of 2 than 5,
the number of trailing zeros of x! will always be num_5.

num_5 of x! can be computed by summing up the count of integers in the range [1, x] that are integer multiples of 5, 25, 125, ..., 5^k, ..., etc.
A simple implementation is as follows:

private long numOfTrailingZeros(long x) {
    long res = 0;

    for (; x > 0; x /= 5) {
        res += x/5;
    }

    return res;
}
Given two non-negative integers x and y, if x <= y, then numOfTrailingZeros(x) <= numOfTrailingZeros(y),
which implies numOfTrailingZeros is a non-decreasing function of x.

Our first binary solution is based on the third observation where we search the range of integers such that the numOfTrailingZeros
function is evaluated to K, as shown below.

Binary search solution ONE -- search for x range:

This solution takes advantage of the last observation mentioned above, where we use binary search to find the largest integers x and y
such that numOfTrailingZeros(x) <= K and numOfTrailingZeros(y) <= K-1, respectively. Then the factorial of all integers in the range (y, x]
(left exclusive, right inclusive) will have K trailing zeros. Therefore the total number of non-negative integers whose factorials have
K trailing zeros will be given by x - y.

The following is the Java code for this solution, where the time complexity is O((logK)^2) and space complexity is O(1). Note that x! will
always have at least x/5 trailing zeros, therefore, if x is the largest integer such that x! has no more than K trailing zeros,
then we have x <= 5 * (K + 1), which can serve as the upper bound of our binary search.

 */