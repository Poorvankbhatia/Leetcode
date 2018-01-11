package arrays.medium;

/**
 * Created by poorvank.b on 11/01/18.
 */
public class HIndex {

    public int hIndex(int[] citations) {
        int len = citations.length;
        int[] count = new int[len + 1];

        for (int c: citations)
            if (c > len)
                count[len]++;
            else
                count[c]++;


        int total = 0;
        for (int i = len; i >= 0; i--) {
            total += count[i];
            if (total >= i)
                return i;
        }

        return 0;
    }

}

/*

1) The easier solution which given by the wiki page:

First we order the values of f from the largest to the lowest value.
Then, we try to find the last number >= its index, its index is the H-index.

For example, if we have a researcher with 5 publications:
25, 8, 5, 3, 3 citations respectively,
1 , 2, 3, 4, 5 as index.

The number is 5, H-index is 3.


2) The O(n) solution:
A researcher have 5 publications:
A, B, C, D, E with
5, 8,10, 4, 3 citation respectively.

After first loop:

  for (int c: citations)
        if (c > len)
            count[len]++;
        else
            count[c]++;
we have the count array:

value: 0, 0, 0, 1, 1, 3
index: 0, 1, 2, 3, 4, 5
In second loop:

  for (int i = len; i >= 0; i--) {
        total += count[i];
        if (total >= i)
            return i;
    }
Step 1: index is 5, total = 3.
Step 2: index is 4, total = 4, return 4.


 */