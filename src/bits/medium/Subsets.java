/*

Given a set of distinct integers, nums, return all possible subsets.

Note: The solution set must not contain duplicate subsets.

For example,
If nums = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]

 */
package bits.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by poorvank.b on 27/04/17.
 */
public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        Set<String> set = new HashSet<>();
        if(nums.length==0) {
            return result;
        }
        int n = nums.length;
        for (int i=0;i<(1<<n);i++) {
            List<Integer> list = new ArrayList<>();
            for (int j=0;j<n;j++) {

                // (1<<j) is a number with jth bit 1
                // so when we 'and' them with the
                // subset number we get which numbers
                // are present in the subset and which
                // are not
                if ((i & (1 << j)) > 0) {
                    list.add(nums[j]);
                }

            }
            result.add(list);
        }

        return result;

    }

}
/*

The total number of subsets of any given set is equal to 2^ (no. of elements in the set). If we carefully notice it is nothing
but binary numbers from 0 to 15 which can be shown as below:

0000 {}

0001 {a}

0010 {b}

0011 {a, b}

0100 {c}

0101 {a, c}

0110 {b, c}

0111 {a, b, c}

1000 {d}

1001 {a, d}

1010 {b, d}

1011 {a, b, d}

1100 {c, d}

1101 {a, c, d}

1110 {b, c, d}

1111 {a, b, c, d}

Starting from right, 1 at ith position shows that the ith element of the set is present as 0 shows that the element is absent.
 Therefore, what we have to do is just generate the binary numbers from 0 to 2^n – 1, where n is the length of the set or the numbers of
 elements in the set.

 */
