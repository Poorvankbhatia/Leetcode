package bits.medium;

/**
 * Created by poorvank.b on 10/03/18.
 */
public class TotalHammingDistance {

    public int totalHammingDistance(int[] nums) {
        int count = 0;

        // iterate thru "column" or bit position
        // Note: you could stop at 10^9 as stated in the problem if you want to optimize
        for (int i = 0; i < 32; i++)
        {
            int mask = 1 << i;
            int countOnes = 0;
            int countZeros = 0;
            for (int x : nums)
            {
                if ((x & mask) != 0) countOnes++;
                else countZeros++;
            }

            count += countOnes * countZeros;
        }
        return count;
    }

}

/*

for each “column” or bit position, once you count the number of set bits you can figure out the number of pairs that
will contribute to the count using combination logic.

Consider you have 10 numbers and only one of them is a 1 the rest are zeros. How many (1, 0) pairs can you make?
Clearly you can make 9, pair the 1 with each of the other 9 zeros. If you have 2 ones, you can pair each of those
with the other 8 zeros giving 2*8 = 16. Keep going and you see that you can pair each 1 with each zero so the number
of pairs is just the number of 1’s times the number of 0’s.

This would be an O(32 * n) solution which is an O(n) solution, no space used.


http://stackoverflow.com/questions/21388448/sum-of-xor-values-of-all-pairs

 */