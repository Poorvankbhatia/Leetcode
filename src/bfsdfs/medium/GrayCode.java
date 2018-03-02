/*

The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code.
A gray code sequence must begin with 0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

00 - 0
01 - 1
11 - 3
10 - 2

 */

package bfsdfs.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 22/05/17.
 */

//Currently not working as leetcode is able to judge based on one instance of gray code sequence

public class GrayCode {

    public List<Integer> grayCode(int n) {

        List<String> result = new ArrayList<>();
        if(n<=0) {
            return new ArrayList<>();
        }

        result.add("0");
        result.add("1");

        for (int i=2;i<(1<<n);i=i<<1) {

            for (int j=result.size()-1;j>=0;j--) {
                result.add(result.get(j));
            }

            for (int j=0;j<i;j++) {
                result.set(j,result.get(j)+"0");
            }

            for (int j=i;j<2*i;j++) {
                result.set(j,result.get(j)+"1");
            }

        }

        List<Integer> finalResult = new ArrayList<>();

        for (String s : result) {
            finalResult.add(Integer.parseInt(s,2));
        }


        return finalResult;

    }

    public static void main(String[] args) {
        System.out.println(new GrayCode().grayCode(3));
    }

}

/*

n-bit Gray Codes can be generated from children of (n-1)-bit Gray codes using following steps.
1) Let the children of (n-1)-bit Gray codes be L1. Create another children L2 which is reverse of L1.
2) Modify the children L1 by prefixing a ‘0’ in all codes of L1.
3) Modify the children L2 by prefixing a ‘1’ in all codes of L2.
4) Concatenate L1 and L2. The concatenated children is required children of n-bit Gray codes.

For example, following are steps for generating the 3-bit Gray code children from the children of 2-bit Gray code children.
L1 = {00, 01, 11, 10} (List of 2-bit Gray Codes)
L2 = {10, 11, 01, 00} (Reverse of L1)
Prefix all entries of L1 with ‘0’, L1 becomes {000, 001, 011, 010}
Prefix all entries of L2 with ‘1’, L2 becomes {110, 111, 101, 100}
Concatenate L1 and L2, we get {000, 001, 011, 010, 110, 111, 101, 100}

To generate n-bit Gray codes, we start from children of 1 bit Gray codes. The children of 1 bit Gray code is {0, 1}.
We repeat above steps to generate 2 bit Gray codes from 1 bit Gray codes, then 3-bit Gray codes from 2-bit Gray codes till
the number of bits becomes equal to n

 */