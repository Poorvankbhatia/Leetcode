/*

Given a sequence of words, check whether it forms a valid word square.

A sequence of words forms a valid word square if the kth row and column read the exact same string, where 0 ≤ k < max(numRows, numColumns).

Note:

The number of words given is at least 1 and does not exceed 500.
Word length will be at least 1 and does not exceed 500.
Each word contains only lowercase English alphabet a-z.
Example 1:

Input:
[
  "abcd",
  "bnrt",
  "crmy",
  "dtye"
]

Output:
true

Explanation:
The first row and first column both read "abcd".
The second row and second column both read "bnrt".
The third row and third column both read "crmy".
The fourth row and fourth column both read "dtye".

Therefore, it is a valid word square.
Example 2:

Input:
[
  "abcd",
  "bnrt",
  "crm",
  "dt"
]

Output:
true

Explanation:
The first row and first column both read "abcd".
The second row and second column both read "bnrt".
The third row and third column both read "crm".
The fourth row and fourth column both read "dt".

Therefore, it is a valid word square.
Example 3:

Input:
[
  "ball",
  "area",
  "read",
  "lady"
]

Output:
false

Explanation:
The third row reads "read" while the third column reads "lead".

Therefore, it is NOT a valid word square.


 */

package arrays.Hard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 16/10/16.
 */
public class WordSquares {

    public boolean validWordSquare(List<String> words) {
        for (int i=0;i<words.size();i++) {
            String s = words.get(i);
            if(!s.equals(util(i,words))) {
                return false;
            }

        }

        return true;

    }

    private String util(int col,List<String> words) {
        StringBuilder sb = new StringBuilder();

        for (String word : words) {
            //Check example 2; y is this condition needed.
            if (col < word.length()) {
                sb.append(word.charAt(col));
            }
        }

        return sb.toString();

    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("ball");
        list.add("area");
        list.add("read");
        list.add("lady");
        System.out.print(new WordSquares().validWordSquare(list));
    }

}
