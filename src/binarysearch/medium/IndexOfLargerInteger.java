/*
We have an integer array arr, where all the integers in arr are equal except for one integer which is larger than the
rest of the integers. You will not be given direct access to the array, instead, you will have an API ArrayReader
which have the following functions:

int compareSub(int l, int r, int x, int y): where 0 <= l, r, x, y < ArrayReader.length(), l <= r and x <= y.
The function compares the sum of sub-array arr[l..r] with the sum of the sub-array arr[x..y] and returns:
1 if arr[l]+arr[l+1]+...+arr[r] > arr[x]+arr[x+1]+...+arr[y].
0 if arr[l]+arr[l+1]+...+arr[r] == arr[x]+arr[x+1]+...+arr[y].
-1 if arr[l]+arr[l+1]+...+arr[r] < arr[x]+arr[x+1]+...+arr[y].
int length(): Returns the size of the array.
You are allowed to call compareSub() 20 times at most. You can assume both functions work in O(1) time.

Return the index of the array arr which has the largest integer.



Example 1:

Input: arr = [7,7,7,7,10,7,7,7]
Output: 4
Explanation: The following calls to the API
reader.compareSub(0, 0, 1, 1) // returns 0 this is a query comparing the sub-array (0, 0) with the sub array (1, 1),
(i.e. compares arr[0] with arr[1]).
Thus we know that arr[0] and arr[1] doesn't contain the largest element.
reader.compareSub(2, 2, 3, 3) // returns 0, we can exclude arr[2] and arr[3].
reader.compareSub(4, 4, 5, 5) // returns 1, thus for sure arr[4] is the largest element in the array.
Notice that we made only 3 calls, so the answer is valid.
Example 2:

Input: nums = [6,6,12]
Output: 2


Constraints:

2 <= arr.length <= 5 * 105
1 <= arr[i] <= 100
All elements of arr are equal except for one element which is larger than all other elements.

Follow up:
What if there are two numbers in arr that are bigger than all other numbers?
What if there is one number that is bigger than other numbers and one number that is smaller than other numbers?
 */
package binarysearch.medium;

public class IndexOfLargerInteger {

    private interface ArrayReader {
        public int compareSub(int l, int r, int x, int y);
        public int length();
    }

    public int getIndex(ArrayReader reader) {
        int length = reader.length();
        int lo = 0;
        int hi = length-1;
        while(hi-lo>1) {
            int mid = lo+(hi-lo)/2;
            int leftCount = mid-lo;
            int rightCount = hi-mid;
            int compare = 0;
            // if number of elements of the right == number of elements on the left.
            if(leftCount==rightCount) {
                compare = reader.compareSub(lo,mid-1,mid+1,hi);
                if(compare==0) {
                    return mid;
                } else if(compare==1) {
                    hi=mid-1;
                } else {
                    lo=mid+1;
                }
            } else if(leftCount<rightCount) { // left<right check from include mid in left.
                compare = reader.compareSub(lo,mid,mid+1,hi);
                if(compare==1) {
                    hi=mid;
                } else {
                    lo=mid+1;
                }
            } else { // left>right check from include mid in right.
                compare = reader.compareSub(lo,mid-1,mid,hi);
                if(compare==1) {
                    hi=mid-1;
                } else {
                    lo=mid;
                }
            }
        }
        return reader.compareSub(lo,lo,hi,hi)==1?lo:hi;
    }

}

/*

Follow up:

Most of it is standard binary search. If the search space has an even length,
we split by mid and compare sums of [left, mid] and [mid+1, right] (both equal lengths).
If the bigger element is in one of these that sum will be greater so we make our search space the bigger sum.
When it's odd we exclude the middle element and again compare two of equal lengths.
If k==0, it means the bigger element is the one we excluded, other wise as before we
reduce our search space to the range with the smaller sum.

What if there are two numbers in arr that are bigger than all other numbers?

Exact same algorithm really. Except we will find the larger value first.
Only real difference is we include middle in the reduced search space in odd case because we can't be
sure if it isn't the larger value. In case of equality of two ranges, pick either because that means the two
larger values are equal.
After that we can create a modified compareSub that subtracts the difference the larger value adds and reapply the first algorithm.

What if there is one number that is bigger than other numbers and one number that is smaller than other numbers?

When small+large > 2*other, the above algorithm will find larger first and the rest of the process is
more or less the same.
When small+large< 2*other you can just repeat the above algorithm but instead find the smaller one first.
Do both ways and proceed with finding second number normally.
Return the one with the more sensible results.
You can validate them in O(1) by checking some other index and seeing which tuple of small,
other, large is increasing and return that one.

Shorter version:

public int getIndex(ArrayReader reader) {
    int l = 0, r = reader.length() - 1;

    while (l < r) {
        int m = l + (r - l) / 2;
        int val;
        if ((r - l) % 2 == 0) {
            val = reader.compareSub(l, m, m, r);
        } else {
            val = reader.compareSub(l, m, m + 1, r);
        }

        if (val == 1) {
            r = m;
        } else if (val == -1) {
            l = m + 1;
        } else if (val == 0) {
            return m;
        }
    }

    return l;
}

 */