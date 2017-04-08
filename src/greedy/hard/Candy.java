/*

There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?

 */
package greedy.hard;

import java.util.Arrays;

/**
 * Created by poorvank.b on 07/04/17.
 */
public class Candy {

    public int candy(int[] ratings) {

        int n = ratings.length;
        if(n==0 || n==1) {
            return 1;
        }

        int[] result = new int[n];
        //Each child must have at least one candy.
        Arrays.fill(result, 1);

        for (int i = 1; i < result.length; i++){
            if (ratings[i] > ratings[i - 1]) {
                result[i] = (result[i - 1] + 1);
            }
        }

        for (int i = result.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                result[i] = Math.max(result[i], (result[i + 1] + 1));
            }
        }

        int sum = 0;

        for (Integer candyCount : result) {
            sum += candyCount;
        }

        System.out.println(Arrays.toString(result));

        return sum;

    }

    public static void main(String[] args) {
        int[] arr = {1,2,4,4,3};
        System.out.println(new Candy().candy(arr));
    }

}
