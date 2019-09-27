/*

Given a start IP address ip and a number of ips we need to cover n, return a representation of the range as a list (of smallest possible length) of CIDR blocks.

A CIDR block is a string consisting of an IP, followed by a slash, and then the prefix length. For example: "123.45.67.89/20". That prefix length "20" represents the number of common prefix bits in the specified range.

Example 1:
Input: ip = "255.0.0.7", n = 10
Output: ["255.0.0.7/32","255.0.0.8/29","255.0.0.16/32"]
Explanation:
The initial ip address, when converted to binary, looks like this (spaces added for clarity):
255.0.0.7 -> 11111111 00000000 00000000 00000111
The address "255.0.0.7/32" specifies all addresses with a common prefix of 32 bits to the given address,
ie. just this one address.

The address "255.0.0.8/29" specifies all addresses with a common prefix of 29 bits to the given address:
255.0.0.8 -> 11111111 00000000 00000000 00001000
Addresses with common prefix of 29 bits are:
11111111 00000000 00000000 00001000
11111111 00000000 00000000 00001001
11111111 00000000 00000000 00001010
11111111 00000000 00000000 00001011
11111111 00000000 00000000 00001100
11111111 00000000 00000000 00001101
11111111 00000000 00000000 00001110
11111111 00000000 00000000 00001111

The address "255.0.0.16/32" specifies all addresses with a common prefix of 32 bits to the given address,
ie. just 11111111 00000000 00000000 00010000.

In total, the answer specifies the range of 10 ips starting with the address 255.0.0.7 .

There were other representations, such as:
["255.0.0.7/32","255.0.0.8/30", "255.0.0.12/30", "255.0.0.16/32"],
but our answer was the shortest possible.

Also note that a representation beginning with say, "255.0.0.7/30" would be incorrect,
because it includes addresses like 255.0.0.4 = 11111111 00000000 00000000 00000100
that are outside the specified range.
Note:
ip will be a valid IPv4 address.
Every implied address ip + x (for x < n) will be a valid IPv4 address.
n will be an integer in the range [1, 1000].

 */
package bits.easy;

import java.util.ArrayList;
import java.util.List;

public class IPToCIDR {

    public List<String> ipToCIDR(String ip, int n) {
        int cur = toInt(ip);
        List<String> res = new ArrayList<>();
        while(n>0){
            int maxBits = Integer.numberOfTrailingZeros(cur);
            int maxAmount = 1<<maxBits;
            int bitVal = 1;
            int count = 0;
            while(bitVal< n && count< maxBits){
                bitVal<<=1;
                ++count;
            }
            if(bitVal>n){
                bitVal>>=1;
                --count;
            }
            res.add(toString(cur,32-count));
            n-= bitVal;
            cur+=(bitVal);
        }
        return res;
    }
    private String toString(int number, int range){
        //convert every 8 into an integer
        final int WORD_SIZE = 8;
        StringBuilder sb = new StringBuilder();
        for(int i=3; i>=0; --i){
            sb.append(Integer.toString(((number>>(i*WORD_SIZE))&255)));
            sb.append(".");
        }
        sb.setLength(sb.length()-1);
        sb.append("/");
        sb.append(Integer.toString(range));
        return sb.toString();
    }
    private int toInt(String ip){
        String [] sep = ip.split("\\.");
        int sum = 0;
        for(int i=0; i<sep.length;++i){
            sum*=256;
            sum+=Integer.parseInt(sep[i]);
        }
        return sum;
    }

}

/*
The problem is asking to get IP Addresses starting with the given start IP Address that cover n IPs.
So we will take all the ips we can get at each IP Address (at each step this is either the maximum possible
(2^number of trailing bits) or if 2^number of trailing bits will make us pick more than n it is the largest (2^amount) that won't pick over n.

We get our next IP by adding the value of 2^bits we took in the result for the current IP Address value.
The reason this works, is that we advance out of the amount of values we added to the result's range.
(for example we found 2^3 values in the iteration so we add 8 because that's the next ip address that we didn't proccess).

For the problems example:
We start at
255.0.0.7 -> 11111111 00000000 00000000 00000111 (IP form -> Integer form) n=10
We get "255.0.0.7/32" because there are 0 leading zeroes, the range is 32- 0 bit shifted=32. We used 1 so n = 9
255.0.0.7 + 2^0 = 255.0.0.8 255.0.0.8 -> 11111111 00000000 00000000 00001000
We get "255.0.0.8/29". The number of leading zeros is 3 so we can use 2^3 digits from this range. We shifted 1 -> 2 -> 4 -> 8
three times so the range is 32-3 bits shifted=29. We used 2^3 =8 so n = 1.
255.0.0.8 + 2^3 = 255.0.0.16
255.0.0.16->11111111 00000000 00000000 00010000
We get "255.0.0.16/32".Theres 4 leading zero's but we only have n=1 remaining so we can only take 2^0 (maximum power of
two less than n and less than 2^leading zeroes). the range is 32- 0 bit shifted =32. We used 2^0 so n=0.
255.0.0.16+2^0 = 255.0.0.17 but n=0 so we're done

1. Convert the IP string into an Integer
The first goal is to convert the IP Address to an int so we can have an easier time processing it. First I split the string and
convert the ip formatted string into an integer. The way I do this is splitting the string by "."s and treating each block as a
digit of base 256. Now I want to convert this base 256 number into base 10 similar to how you would convert a base 2 number into
base ten by multiplying each digit with 2^digit places from the end, but instead base 256 by multiplying each block with 256^ places
from the end. So the last block is multiplied by 1 (256^0) the second to last block is multiplied by 2 ( 256^1), etc. and summed
(I did it the other way around by multiplying the sum instead of remultiplying each digit, but either way works).

2. Greedily take the biggest block of IP Addresses at each step
As mentioned before I get the number of trailing zeros. I know that this is the maximum number of bits I'm allowed to advance.
I start the value at 1 just so it's easier to calculate the 32 - used. I keep shifting it while it is less than n or the number
of bits shifted is less than the maximum allowed. If I went over the n value I have to go back one shift, because I have to take a
 power of 2 less than the valid amount (any more is invalid). If the number shifted is less than the maximum allowed shifting one
 more time will always make it = to the maximum allowed so the current amount is fine. You can implement this however u like; however, make sure it works in these two cases.

3. At each step convert back into String Form
During part 2, I have to convert the number back into string form and add the range at the end. The range is 32- the number of
bits shifted from 1 for part 2's bits advanced. I shift each number in blocks of 255 and & it with a 255 mask to get each block,
and append a . after. I remove the last ., and append the range at the end using string builder.
 */