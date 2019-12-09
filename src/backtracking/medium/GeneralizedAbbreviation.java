/*

Write a function to generate the generalized abbreviations of a word.

Note: The order of the output does not matter.

Example:

Input: "word"
Output:
["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]

 */
package backtracking.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank.b on 09/06/18.
 */
public class GeneralizedAbbreviation {

    public List<String> generateAbbreviations(String word){
        List<String> ret = new ArrayList<>();
        backtrack(ret, word, 0, "", 0);

        return ret;
    }

    private void backtrack(List<String> ret, String word, int pos, String cur, int count){
        if(pos==word.length()){
            if(count > 0) cur += count;
            ret.add(cur);
        }
        else{
            backtrack(ret, word, pos + 1, cur, count + 1);
            backtrack(ret, word, pos+1, cur + (count>0 ? count : "") + word.charAt(pos), 0);
        }
    }

    public static void main(String[] args) {
        System.out.println(new GeneralizedAbbreviation().generateAbbreviations("word"));
    }

}

/*

The idea is: for every character, we can keep it or abbreviate it. To keep it, we add it to the current solution and carry on backtracking.
 To abbreviate it, we omit it in the current solution, but increment the count, which indicates how many characters have we abbreviated.
  When we reach the end or need to put a character in the current solution, and count is bigger than zero, we add the number into the solution

 */