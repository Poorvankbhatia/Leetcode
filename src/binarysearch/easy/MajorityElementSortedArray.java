/*

Given an array nums sorted in non-decreasing order, and a number target, return True if and only if target is a majority element.

A majority element is an element that appears more than N/2 times in an array of length N.



Example 1:

Input: nums = [2,4,5,5,5,5,5,6,6], target = 5
Output: true
Explanation:
The value 5 appears 5 times and the length of the array is 9.
Thus, 5 is a majority element because 5 > 9/2 is true.
Example 2:

Input: nums = [10,100,101,101], target = 101
Output: false
Explanation:
The value 101 appears 2 times and the length of the array is 4.
Thus, 101 is not a majority element because 2 > 4/2 is false.


Note:

1 <= nums.length <= 1000
1 <= nums[i] <= 10^9
1 <= target <= 10^9

 */
package binarysearch.easy;

public class MajorityElementSortedArray {

    public boolean isMajorityElement(int[] nums, int target) {
        int f = firstOccurrance(0,nums.length-1,nums,target);
        if(f==-1) {
            return false;
        } else {
            int l = lastOccurrance(0,nums.length-1,nums,target);
            return (l-f+1)>(nums.length)/2;
        }
    }

    private int firstOccurrance(int start,int end,int[] nums, int target) {

        if(start>end) {
            return -1;
        }

        int mid = start+(end-start)/2;
        if(nums[mid]==target) {
            if(mid>0 && nums[mid-1]<target) {
                return mid;
            } else if(mid>0) {
                return firstOccurrance(start,mid-1,nums,target);
            } else {
                return mid;
            }
        } else if(nums[mid]>target) {
            return firstOccurrance(start,mid-1,nums,target);
        } else {
            return firstOccurrance(mid+1,end,nums,target);
        }

    }

    private int lastOccurrance(int start,int end,int[] nums,int target) {
        if(start>end) {
            return -1;
        }

        int mid = start+(end-start)/2;
        if(nums[mid]==target) {
            if(mid<nums.length-1 && nums[mid+1]>target) {
                return mid;
            } else if(mid<nums.length-1) {
                return lastOccurrance(mid+1,end,nums,target);
            } else {
                return mid;
            }
        } else if(nums[mid]>target) {
            return lastOccurrance(start,mid-1,nums,target);
        } else {
            return lastOccurrance(mid+1,end,nums,target);
        }

    }

    public static void main(String[] args) {
        int[] nums = new int[]{
                17254032,44726461,94429656,95088006,110536255,138190931,150275052,164761538,221923321,296618557,
                305947091,332191050,372173382,381735837,396788118,429860682,437749703,437749703,437749703,437749703,
                437749703,437749703,437749703,437749703,437749703,437749703,437749703,437749703,437749703,437749703,
                437749703,437749703,437749703,437749703,437749703,437749703,437749703,437749703,437749703,437749703,
                437749703,437749703,437749703,437749703,437749703,437749703,437749703,455951973,463237207,498375239,
                526236757,537304401,621610945,658169340,678150935,718727426,744118395,763870453,793588980,991511570
        };

        System.out.println(new MajorityElementSortedArray().isMajorityElement(nums, 437749703));
    }

}
/*

With one BS:

public boolean isMajorityElement(int[] nums, int target) {
        int firstIndex = firstOccur(nums, target);
        int plusNBy2Index = firstIndex + nums.length/2;

        if (plusNBy2Index<nums.length
            && nums[firstIndex] == target
            && nums[plusNBy2Index] == target) {
            return true;
        }

        return false;
    }

    private int firstOccur(int[] nums, int target) {
        int low = 0;
        int high = nums.length;
        while (low < high) {
            int mid = low + (high-low)/2;
            if (nums[mid] < target) low = mid + 1;
            else if (nums[mid] >= target) high = mid;
        }
        return low;
    }

 */
