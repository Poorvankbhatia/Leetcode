/*
Given an array of unique integers salary where salary[i] is the salary of the employee i.

Return the average salary of employees excluding the minimum and maximum salary.



Example 1:

Input: salary = [4000,3000,1000,2000]
Output: 2500.00000
Explanation: Minimum salary and maximum salary are 1000 and 4000 respectively.
Average salary excluding minimum and maximum salary is (2000+3000)/2= 2500
Example 2:

Input: salary = [1000,2000,3000]
Output: 2000.00000
Explanation: Minimum salary and maximum salary are 1000 and 3000 respectively.
Average salary excluding minimum and maximum salary is (2000)/1= 2000
Example 3:

Input: salary = [6000,5000,4000,3000,2000,1000]
Output: 3500.00000
Example 4:

Input: salary = [8000,9000,2000,3000,6000,1000]
Output: 4750.00000


Constraints:

3 <= salary.length <= 100
10^3 <= salary[i] <= 10^6
salary[i] is unique.
Answers within 10^-5 of the actual value will be accepted as correct.
 */
package arrays.easy;

public class AverageSalary {
    public double average(int[] salary) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int sum=0;
        for(int sal : salary) {
            max = Math.max(sal,max);
            min = Math.min(sal,min);
            sum+=sal;
        }
        sum-=(max+min);
        return (double) (sum)/(salary.length-2);
    }
}
