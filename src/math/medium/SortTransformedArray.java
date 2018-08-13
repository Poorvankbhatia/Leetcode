/*

Given a sorted array of integers nums and integer values a, b and c. Apply a quadratic function of
the form f(x) = ax2 + bx + c to each element x in the array.

The returned array must be in sorted order.

Expected time complexity: O(n)

Example:
nums = [-4, -2, 2, 4], a = 1, b = 3, c = 5,

Result: [3, 9, 15, 33]

nums = [-4, -2, 2, 4], a = -1, b = 3, c = 5

Result: [-23, -5, 1, 7]


 */
package math.medium;

/**
 * Created by poorvank.b on 01/08/18.
 */
public class SortTransformedArray {

    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int[] res = new int[nums.length];
        int start = 0;
        int end = nums.length - 1;
        int i = a >= 0 ? nums.length - 1 : 0;
        while(start <= end) {
            int startNum = getNum(nums[start], a, b, c);
            int endNum = getNum(nums[end], a, b, c);
            if(a >= 0) {
                if(startNum >= endNum) {
                    res[i--] = startNum;
                    start++;
                }
                else{
                    res[i--] = endNum;
                    end--;
                }
            }
            else{
                if(startNum <= endNum) {
                    res[i++] = startNum;
                    start++;
                }
                else{
                    res[i++] = endNum;
                    end--;
                }
            }
        }
        return res;
    }
    private int getNum(int x, int a, int b, int c) {
        return a * x * x + b * x + c;
    }

}

/*

The idea is simple:
For a parabola,
if a >= 0, the min value is at its vertex. So our our sorting should goes from two end points towards the vertex, high to low.
if a < 0, the max value is at its vertex. So our sort goes the opposite way.


1.a>0, two ends in original array are bigger than center if you learned middle school math before.

2.a<0, center is bigger than two ends.

so use two pointers i, j and do a merge-sort like process. depending on sign of a, you may want to start from the beginning or
end of the transformed array. For a==0 case, it does not matter what b's sign is.
The function is monotonically increasing or decreasing. you can start with either beginning or end.

 */
