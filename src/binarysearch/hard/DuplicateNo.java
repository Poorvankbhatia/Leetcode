/*

Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

Note:
You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n2).
There is only one duplicate number in the array, but it could be repeated more than once.

 */
package binarysearch.hard;

/**
 * Created by poorvank.b on 14/10/16.
 */
public class DuplicateNo {

    public int findDuplicate(int[] nums) {


        int l=0,r=nums.length-1;
        while(l<r){
            int m=(l+r)/2;
            int c=0;

            //Count numbers smaller than mid left of mid
            for(int i: nums){
                if(i<=m){
                    c++;
                }
            }

            /*
                 The idea is calculate a middle number between low and high, then in for loop to check total numbers less or equal
                 than middle. If numbers less than middle, then the duplicate must fall in the [low, middle] range;
                 else in [middle+1, high]. For this solution, time is O(nlgn) and space is O(1)
             */
            if(c>m){
                r=m;
            }else{
                l=m+1;
            }
        }

        return r;


    }

    public static void main(String[] args) {
        int[] arr = new int[]{4,4,3,2,1};
        System.out.println(new DuplicateNo().findDuplicate(arr));
    }

}

/*

This solution is based on binary search.

At first the search space is numbers between 1 to n. Each time I select a number mid (which is the one in the middle)
and count all the numbers equal to or less than mid. Then if the count is more than mid, the search space will be [1 mid]
otherwise [mid+1 n]. I do this until search space is only one number.

Let's say n=10 and I select mid=5. Then I count all the numbers in the array which are less than equal mid.
 If the there are more than 5 numbers that are less than 5, then by Pigeonhole Principle
one of them has occurred more than once. So I shrink the search space from [1 10] to [1 5].
Otherwise the duplicate number is in the second half so for the next step the search space would be [6 10].


The pigeonhole principle is only one way. It does tell you that there's a duplicate if there are "too many" items.
But it doesn't tell you that there isn't a duplicate if there aren't "too many" items. So how do you know the duplicate is
in the second half?

I'd put it this way:

Let count be the number of elements in the range 1 .. mid, as in your solution.

If count > mid, then there are more than mid elements in the range 1 .. mid and thus that range contains a duplicate.

If count <= mid, then there are n+1-count elements in the range mid+1 .. n. That is, at least n+1-mid elements in a range of
size n-mid. Thus this range must contain a duplicate.

Or less formally:

We know that the whole range is "too crowded" and thus that the first half or the second half of the range is too crowded
(if both weren't, then neither would be the whole range). So you check to know whether the first half is too crowded,
and if it isn't, you know that the second half is.

Also, better say "less than or equal to 5", not just "less than 5".

Btw, you could also use count = sum(i <= mid for i in nums)
or even directly if sum(i <= mid for i in nums) <= mid:.

For O(n)
http://keithschwarz.com/interesting/code/?dir=find-duplicate

 */
