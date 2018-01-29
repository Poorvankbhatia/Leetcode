/*

We have some permutation A of [0, 1, ..., N - 1], where N is the length of A.

The number of (global) inversions is the number of i < j with 0 <= i < j < N and A[i] > A[j].

The number of local inversions is the number of i with 0 <= i < N and A[i] > A[i+1].

Return true if and only if the number of global inversions is equal to the number of local inversions.

Example 1:

Input: A = [1,0,2]
Output: true
Explanation: There is 1 global inversion, and 1 local inversion.
Example 2:

Input: A = [1,2,0]
Output: false
Explanation: There are 2 global inversions, and 1 local inversion.
Note:

A will be a permutation of [0, 1, ..., A.length - 1].
A will have length in range [1, 5000].


 */
package arrays.medium;

/**
 * Created by poorvank.b on 28/01/18.
 */
public class LocalAndGlobalAversions {

    public boolean isIdealPermutation(int[] A) {

        if(A==null || A.length==0) {
            return true;
        }

        int localInversions = 0,globalInversions=0,incorrectIndex=0;

        for (int i=0;i<A.length;i++) {
            if(i!=A.length-1 && A[i]>A[i+1]) {
                localInversions++;
            }
            if(A[i]>i) {
                globalInversions+=A[i]-i;
                incorrectIndex++; // 2,1,0
            } else if(A[i]==i && i!=A.length-1) { //1,0,2
                globalInversions+=incorrectIndex;
            } else if(A[i]<i) {
                incorrectIndex = incorrectIndex>0?incorrectIndex-1:0;
            }
        }

        System.out.println(localInversions+" "+globalInversions);

        return localInversions==globalInversions;

    }

    public static void main(String[] args) {
        int[] arr = new int[]{2,0,1,3,4};
        System.out.println(new LocalAndGlobalAversions().isIdealPermutation(arr));
    }

}

/*


Easier Approach :

All local inversions are also global inversion. So if find an extra global inversion, we return false.
So if find a number which is not correctly placed i.e. if(A[i] != i)
then it is ok as long as A[i+1] = i and A[i] = i+1
But if this condition is not true
it means that either
a) i is placed at some postion j > i+1 and A[i] and A[i+1] are greater than i, i.e. A[i] > i , A[i+1] > i
b) i+1 is placed at some position j > i+1 , so A[i] > i+1 , A[i+1] = i and i+1 is at some j > i+1
so A[i] is greater than A[i+1] and A[i] > A[j]
so we get an extra global inversion so we return false;

class Solution {
    public boolean isIdealPermutation(int[] A) {
        int l = A.length;
        if(l < 3){
            return true;
        }
        for(int i=0;i<l-1;i++){
            if(A[i] != i){
                if(A[i] == i+1 && A[i+1] == i){
                    i++;
                }else{
                    return false;
                }
            }
        }
        return true;
    }
}

A local inversion would also be a global inversion, so the numbers will only be different when there is global inversion that isn't also a local inversion.


We just check the offset of each number, if the absolute value is larger than 1, means the number of global inversion must be bigger than local inversion,
because a local inversion is a global inversion, but a global one may not be local.

class Solution {
public:
    bool isIdealPermutation(vector<int>& A) {
	for (int i = 0; i < A.size(); ++i) {
            if (abs(A[i] - i) > 1) return false;
        }
	return true;
    }
};

 */