/*
Given an integer array of digits, return the largest multiple of three that can be formed by concatenating some of the given digits in any order.

Since the answer may not fit in an integer data type, return the answer as a string.

If there is no answer return an empty string.



Example 1:

Input: digits = [8,1,9]
Output: "981"
Example 2:

Input: digits = [8,6,7,1,0]
Output: "8760"
Example 3:

Input: digits = [1]
Output: ""
Example 4:

Input: digits = [0,0,0,0,0,0]
Output: "0"


Constraints:

1 <= digits.length <= 10^4
0 <= digits[i] <= 9
The returning answer must not contain unnecessary leading zeros.
 */
package math.hard;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class LargestMultipleOfThree {

    public String largestMultipleOfThree(int[] arr) {
        Arrays.sort(arr);
        /*
        Take three queues.
        queue0 for storing elements which on dividing by 3 gives remainder as 0.
        queue1 stores digits which on dividing by 3 gives remainder as 1.
        queue2 stores digits which on dividing by 3 gives remainder as 2.
         */
        Queue<Integer> queue0=new LinkedList<>();
        Queue<Integer> queue1=new LinkedList<>();
        Queue<Integer> queue2=new LinkedList<>();
        int sum=0;
        for (int a : arr) {
            sum += a;
            if (a % 3 == 0) {
                queue0.add(a);
            }
            else if (a % 3 == 1) {
                queue1.add(a);
            }
            else {
                queue2.add(a);
            }
        }
        if(sum==0) {
            return "0";
        }
        /*
        Sum of digits produces remainder 1 when divided by 3.
        Remove one item from queue1.
        If queue1 is empty, remove two items from queue2.
        If queue2 contains less than two items, the number is not possible.
         */
        if ((sum % 3) == 1) {
            if (!queue1.isEmpty()) {
                queue1.remove();
            }
            else {
                if (queue2.size() < 2) {
                    return "";
                }
                queue2.remove();
                queue2.remove();

            }
        }
        /*
        Sum of digits produces remainder 2 when divided by 3.
        Remove one item from queue2.
        If queue2 is empty, remove two items from queue1.
        If queue1 contains less than two items, the number is not possible.
        8,8,6,0,3
         */
        else if (sum % 3 == 2)
        {
            if (!queue2.isEmpty()) {
                queue2.remove();
            }
            else {
                if(queue1.size()<2) {
                    return "";
                }
                queue1.remove();
                queue1.remove();
            }
        }

        int[] temp = new int[arr.length];
        int top= populate(temp,queue0,queue1,queue2);
        Arrays.sort(temp,0,top);

        StringBuilder result = new StringBuilder();
        for (int i = top-1; i>=0; i--) {
            result.append(temp[i]);
        }

        return result.toString();

    }

    private int populate(int[] temp, Queue<Integer> queue0, Queue<Integer> queue1, Queue<Integer> queue2) {
        int top=0;
        while(!queue0.isEmpty()) {
            temp[top++] = queue0.remove();
        }
        while(!queue1.isEmpty()) {
            temp[top++]=queue1.remove();
        }
        while(!queue2.isEmpty()) {
            temp[top++]=queue2.remove();
        }
        return top;
    }

}

/*

G I



 */