/*

We sampled integers between 0 and 255, and stored the results in an array count:  count[k] is the number of integers we sampled equal to k.

Return the minimum, maximum, mean, median, and mode of the sample respectively, as an array of floating point numbers.  The mode is guaranteed to be unique.

(Recall that the median of a sample is:

The middle element, if the elements of the sample were sorted and the number of elements is odd;
The average of the middle two elements, if the elements of the sample were sorted and the number of elements is even.)


Example 1:

Input: count = [0,1,3,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
Output: [1.00000,3.00000,2.37500,2.50000,3.00000]
Example 2:

Input: count = [0,4,3,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
Output: [1.00000,4.00000,2.18182,2.00000,1.00000]


Constraints:

count.length == 256
1 <= sum(count) <= 10^9
The mode of the sample that count represents is unique.
Answers within 10^-5 of the true value will be accepted as correct.

 */
package arrays.medium;

public class StatisticsFromLargeSample {

    public double[] sampleStats(int[] count) {
        double[] ans = new double[5];
        double min = -1, max = -1, mean = 0, median = -1, mode = 0, total = 0;
        for(int i=0; i<count.length; i++){
            if(min == -1 && count[i] > 0){
                min = i;
            }
            if(count[i] > 0 && i > max){
                max = i;
            }
            //'mean' is the summation of all numbers (count[i]*i for ith number) divided by 'total' which is calculated below
            mean += i*count[i];
            total += count[i];
            //'mode' is the number with highest frequency
            if(count[i] > count[(int)mode]){
                mode = i;
            }
        }
        mean = mean/total;
        int countSoFar = 0;
        for(int i=0; i<count.length; i++){
            countSoFar += count[i];
            if(total % 2 != 0){
                if(countSoFar > total/2){   //if we 'cross' the mid element, it is the median
                    median = i;
                    break;
                }
            }
            //even length: median is average of 2 consecutive mid elements
            else{
                //'count[i] > 0 here is to avoid counting 'zero' freq elements
                //countSoFar == total/2 implies we are at the 'first' of 2 mid elements
                if(count[i] > 0 && countSoFar == total/2){
                    median = i;
                }
                //countSoFar crossed total/2 implies that we reached 'second' of  mid elements
                if(countSoFar > total/2){
                    //if median is -1 at this point, this means both the mid elements are the same element. Eg: 1,1,2,2,2,2,3,3, here median = (2+2)/2 => 2
                    if(median == -1){
                        median = i;
                    }
                    //add first and second and take average
                    else median = (median+i)/2;
                    break;
                }
            }
        }
        ans[0] = min; ans[1] = max; ans[2] = mean; ans[3] = median; ans[4] = mode;
        return ans;
    }

}
