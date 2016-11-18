package arrays.medium;/*

Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

Example:
(1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6].
(2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].
The mainly difference between Wiggle sort I and II is this question asks for nums[even] < nums[odd], not nums[even] <= nums[odd].


 */

import java.util.Arrays;

/**
 * Created by poorvank on 21/08/16.
 */
public class WiggleSort2 {

    //TODO : O(n) solution
    public static void main(String[] args) {

        int[] a = new int[]{1,1,1,2,2,2};

        Arrays.sort(a);
        int left,right,k=0;
        if(a.length%2==0) {
            //Last occurrence of left array
            left = (a.length/2)-1;
        } else {
            left = (a.length/2);
        }
        //Last occurrence of right array
        right = a.length-1;

        /*
            If the first occurrence is taken this [4,5,5,6] will fail
         */
        int[] result = new int[a.length];

        for (k=0;k<a.length;k++) {
            if(k%2==0) {
                result[k] = a[left];
                left--;
            } else {
                result[k] = a[right];
                right--;
            }
        }


        System.arraycopy(result,0,a,0,a.length);
        System.out.println(Arrays.toString(a));
    }

}

/*

An O(nlogn) time solution, out-of-place:
We can first sort the array, then partition the array into two halves. So all elements in the first half is less than
the second half. Then we can pick up one element from each half from the end, and form the solution . e.g.
nums = [1, 3, 2, 2, 3, 1].

After the sorting, the array becomes [1, 1, 2, 2, 3, 3]. Then we partition the array into two halves, the left half is [1, 1, 2].
The right half is [2, 3, 3]. Then we pick up one element from each and form the solution.
[1, 1, 2]            [2, 3, 3]
         |                        |
       left                    right
2, 3, 1, 3, 1, 2

Since there must be a solution, the left element we choose each time must be less than the right element (why ? because if the left is
equal to the right, all elements before right and after left must be equal as well, so there will be no solution existed).

Note that if there are odd number of elements, the left half must be 1 more than the right, not reverse. That is because the last element
must be indexed as even (e.g. 0, 1, 2, 3, 4, 5, 6), and since nums[even] < nums[odd], so the last number must be in the smaller half.

 */