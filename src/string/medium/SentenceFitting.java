/*

Given a rows x cols screen and a sentence represented by a list of words, find how many times the given sentence can be fitted on the screen.
Note:
A word cannot be split into two lines.
The order of words in the sentence must remain unchanged.
Two consecutive words in a line must be separated by a single space.
Total words in the sentence won't exceed 100.
Length of each word won't exceed 10.
1 ≤ rows, cols ≤ 20,000.
Example 1:
Input:
rows = 2, cols = 8, sentence = ["hello", "world"]

Output:
1

Explanation:
hello---
world---

The character '-' signifies an empty space on the screen.
Example 2:
Input:
rows = 3, cols = 6, sentence = ["a", "bcd", "e"]

Output:
2

Explanation:
a-bcd-
e-a---
bcd-e-

The character '-' signifies an empty space on the screen.
Example 3:
Input:
rows = 4, cols = 5, sentence = ["I", "had", "apple", "pie"]

Output:
1

Explanation:
I-had
apple
pie-I
had--

The character '-' signifies an empty space on the screen.

 */
package string.medium;

/**
 * Created by poorvank.b on 10/12/17.
 */
public class SentenceFitting {

    public int wordsTyping(String[] sentence, int rows, int cols) {
        StringBuilder sb = new StringBuilder();
        for(String s : sentence) {
            sb.append(s).append(" ");
        }
        int length = sb.length();
        int begin = 0;
        for(int i=0;i<rows;i++) {
            begin+=cols;
            while(begin>0 && sb.charAt(begin%length)!=' ') {
                begin--;
            }
            begin++;
        }

        return begin/length;
    }

    public static void main(String[] args) {
        System.out.println(new SentenceFitting().wordsTyping(new String[]{"abc", "de" , "f"},4,6));
    }

}

/*

Imagine an infinite sentence that are concatenated by words from the given sentence, infiStr.
We want to cut the infiStr properly and put a piece at each row of the screen.

We maintain a pointer ptr. The ptr points to a position at infiStr, where next row will start.
Cutting the infiStr and putting a piece at a row can be simulated as advancing the pointer by cols positions.

After advancing the pointer, if ptr points to a space, it means the piece can fit in row perfectly.
If ptr points to the middle of a word, we must retreat the pointer to the beginning of the word, because a word cannot be split into two lines.



Say sentence=["abc", "de", "f], rows=4, and cols=6.
The screen should look like

"abc de"
"f abc "
"de f  "
"abc de"
Consider the following repeating sentence string, with positions of the start character of each row on the screen.

"abc de f abc de f abc de f ..."
 ^      ^     ^    ^      ^
 0      7     13   18     25
Our goal is to find the start position of the row next to the last row on the screen, which is 25 here. Since actually it's the length of everything
earlier, we can get the answer by dividing this number by the length of (non-repeated) sentence string.
Note that the non-repeated sentence string has a space at the end; it is "abc de f " in this example.

Here is how we find that position. In each iteration, we need to adjust start based on spaces either added or removed.

"abc de f abc de f abc de f ..." // start=0
 012345                          // start=start+cols+adjustment=0+6+1=7 (1 space removed in screen string)
        012345                   // start=7+6+0=13
              012345             // start=13+6-1=18 (1 space added)
                   012345        // start=18+6+1=25 (1 space added)
                          012345
Hope this helps.



This is a Greedy approach. Since we want to know how many times the given sentence can be fitted, we are actually looking for the MAXIMUM
time the given sentence can be fitted. Hence, we can try to put as many words in one line as possible, making up the greedy approach.
In this process, we first try to put as many words in one line as possible and trim the tailing words that does not fit in that line as a whole,
leaving all remaining positions in that line after trimming empty (as space). Then we continue filling next line by starting from the word after
the last word in previous line. In this process we can make use of modulo operation to deal with the wrapping back issue for the sentence.

Since there should be at least space between two words, we first add one space between each word and join the sentence.
Remember to add one space after the last word as well.

Then we consider each row sequentially. Using start to represent the number of characters we have put. For each row, we first try to put cols
character from the joined sentence s into one line start from start (Maximum number of characters we can put into this line).
Then we check the next position after the last position of this line. There are 2 conditions:

If the next position is a space, then this line can fit all words we put in, we add one to start according to the implicit space in this case.
If the next position is not space, we check for the current position. And we remove all characters that are not space until we meet one since
this last word we just removed cannot be fit in this line. In this process we subtract 1 from start during each character being removed.
At last, the start represents the length of the repeated lines in which each word is separated by exactly one space. And since the joined sentence
s has its words separated by 1 space as well, the number of repeats we can get is start / s.length().

Given there are n characters in sentence[]



Time complexity: O(rows + n)

 */