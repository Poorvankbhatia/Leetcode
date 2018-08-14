/*

An encoded string S is given.  To find and write the decoded string to a tape, the encoded string is read one character
 at a time and the following steps are taken:

If the character read is a letter, that letter is written onto the tape.
If the character read is a digit (say d), the entire current tape is repeatedly written d-1 more times in total.
Now for some encoded string S, and an index K, find and return the K-th letter (1 indexed) in the decoded string.



Example 1:

Input: S = "leet2code3", K = 10
Output: "o"
Explanation:
The decoded string is "leetleetcodeleetleetcodeleetleetcode".
The 10th letter in the string is "o".
Example 2:

Input: S = "ha22", K = 5
Output: "h"
Explanation:
The decoded string is "hahahaha".  The 5th letter is "h".
Example 3:

Input: S = "a2345678999999999999999", K = 1
Output: "a"
Explanation:
The decoded string is "a" repeated 8301530446056247680 times.  The 1st letter is "a".


Note:

2 <= S.length <= 100
S will only contain lowercase letters and digits 2 through 9.
S starts with a letter.
1 <= K <= 10^9
The decoded string is guaranteed to have less than 2^63 letters.

 */
package string.medium;

/**
 * Created by poorvank.b on 14/08/18.
 */
public class DecodeStringAtIndex {

    public String decodeAtIndex(String S, int K) {

        long len = 0;

        for (int i=0;i<S.length();i++) {
            if(Character.isLetter(S.charAt(i))) {
                len++;
            } else {
                len *= S.charAt(i)-'0';
            }
        }

        for (int n=S.length()-1;n>=0;n--) {
            char c = S.charAt(n);
            K%=len;
            if(K==0 && Character.isLetter(c)) {
                return Character.toString(c);
            }

            if(Character.isDigit(c)) {
                len /=  c-'0';
            } else {
                len--;
            }

        }

        return "";


    }

}

/*

If we have a decoded string like appleappleappleappleappleapple and an index like K = 24, the answer is the same if K = 4.

In general, when a decoded string is equal to some word with size length repeated some number of times
 (such as apple with size = 5 repeated 6 times), the answer is the same for the index K as it is for the index K % size.

We can use this insight by working backwards, keeping track of the size of the decoded string. Whenever the
 decoded string would equal some word repeated d times, we can reduce K to K % (word.length).

 First, find the length of the decoded string. After, we'll work backwards,
 keeping track of size: the length of the decoded string after parsing symbols S[0], S[1], ..., S[i].

If we see a digit S[i], it means the size of the decoded string after parsing S[0], S[1], ..., S[i-1]
will be size / Integer(S[i]). Otherwise, it will be size - 1.



 */