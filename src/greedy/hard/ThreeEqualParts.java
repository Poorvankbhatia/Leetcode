/*

Given an array A of 0s and 1s, divide the array into 3 non-empty parts such that all of these parts represent the same binary value.

If it is possible, return any [i, j] with i+1 < j, such that:

A[0], A[1], ..., A[i] is the first part;
A[i+1], A[i+2], ..., A[j-1] is the second part, and
A[j], A[j+1], ..., A[A.length - 1] is the third part.
All three parts have equal binary value.
If it is not possible, return [-1, -1].

Note that the entire part is used when considering what binary value it represents.  For example, [1,1,0] represents 6 in decimal, not 3.
Also, leading zeros are allowed, so [0,1,1] and [1,1] represent the same value.



Example 1:

Input: [1,0,1,0,1]
Output: [0,3]
Example 2:

Input: [1,1,0,1,1]
Output: [-1,-1]


Note:

3 <= A.length <= 30000
A[i] == 0 or A[i] == 1

 */
package greedy.hard;

public class ThreeEqualParts {

    public int[] threeEqualParts(int[] A) {
        int n = A.length;
        int oneCount = 0;
        for(int a: A) {
            if(a==1) {
                oneCount++;
            }
        }
        if(oneCount==0) {
            return new int[]{0,n-1};
        }
        if(oneCount%3!=0) {
            return new int[]{-1,-1};
        }
        int k = oneCount/3;
        int i=0;
        while(i<n) {
            if(A[i]==1) {
                break;
            }
            i++;
        }
        int start=i;
        i=0;int sum=0;
        while(i<n) {
            sum+=A[i];
            if(sum==(k+1)) {
                break;
            }
            i++;
        }
        int mid = i;
        i=0;sum=0;
        while(i<n) {
            sum+=A[i];
            if(sum==(2*k+1)) {
                break;
            }
            i++;
        }
        int end = i;

        while(end<n && A[start]==A[mid] && A[mid]==A[end]) {
            start++;
            mid++;
            end++;
        }
        if(end==n) {
            return new int[]{start-1,mid};
        }
        return new int[]{-1,-1};
    }

}

/*

Algorithm:
1) Count no. of 1's in the given array, say countNumberOfOnes.
2) If no 1 is found ie. countNumberOfOnes == 0, just return {0,size-1}
3) If countNumberOfOnes % 3 != 0 , then we cannot partition the given array for sure.
This is because, there is no way to put equal no. of 1's in any partition and hence, we will get different binary representations.
4) Let's try to find if there is a valid partition possible now.
 We find the first 1 in the given array and represent it's position by start.
5) Also, we know that each partition must have countNumberOfOnes/3
(for same reason as given in step 3). Therefore, after finding the first 1, leave k = countNumberOfOnes/3 1's for the first partition.
6) Assign this position as mid that denotes the beginning of a possible second partition.
7) Further leave k = countNumberOfOnes/3 1's for this partition and assign the beginning of last partition as end
8) Now, all we need to do is verify whether all the partitions have same values in them.
This can be done by iterating through to the end of the array.
9) If end doesn't reach the end of the array, we find a mismatch and hence, we need to return {-1, -1}
10) Otherwise, we have found our partition, return {start-1,mid}

 */