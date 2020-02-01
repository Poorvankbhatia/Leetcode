/*
We are given N different types of stickers. Each sticker has a lowercase English word on it.

You would like to spell out the given target string by cutting individual letters from your collection of stickers and rearranging them.

You can use each sticker more than once if you want, and you have infinite quantities of each sticker.

What is the minimum number of stickers that you need to spell out the target? If the task is impossible, return -1.

Example 1:

Input:

["with", "example", "science"], "thehat"
Output:

3
Explanation:

We can use 2 "with" stickers, and 1 "example" sticker.
After cutting and rearrange the letters of those stickers, we can form the target "thehat".
Also, this is the minimum number of stickers necessary to form the target string.
Example 2:

Input:

["notice", "possible"], "basicbasic"
Output:

-1
Explanation:

We can't form the target "basicbasic" from cutting letters from the given stickers.
Note:

stickers has length in the range [1, 50].
stickers consists of lowercase English words (without apostrophes).
target has length in the range [1, 15], and consists of lowercase English letters.
In all test cases, all words were chosen randomly from the 1000 most common US English words, and the target was chosen as a concatenation of two random words.
The time limit may be more challenging than usual. It is expected that a 50 sticker test case can be solved within 35ms on average.
 */
package backtracking.hard;

public class StickersToSpellWords {

    int[][] countMap; //map[i][j] means the number of character (j + 'a') contains in the ith element of stickers...
    int count = Integer.MAX_VALUE;
    public int minStickers(String[] stickers, String target) {
        if (target == null) return -1;
        if (target.length() == 0) return 0;
        if (stickers == null || stickers.length == 0) return -1;

        int m = stickers.length;
        countMap = new int[m][26];

        for (int i = 0; i < stickers.length; i++) {
            String s = stickers[i];
            for (char c : s.toCharArray()) {
                countMap[i][c - 'a']++;
            }
        }
        count(0, 0, new int[26], target, stickers);
        return count == Integer.MAX_VALUE ? -1 : count;
    }

    private void count(int currentCount, int pos, int[] charAvailable, String target, String[] stickers) {
        if (currentCount >= count) {
            return; //prune the search, when currentCount will be larger then count...important step... other wise will get TLE..
        }
        int m = stickers.length, n = target.length();
        if (pos == n) {
            count = Math.min(count, currentCount);
            return;
        }
        char c = target.charAt(pos);
        if (charAvailable[c - 'a'] > 0) {
            charAvailable[c - 'a']--;
            count(currentCount, pos + 1, charAvailable, target, stickers);
            charAvailable[c - 'a']++;
        } else {
            for (int i = 0; i < m; i++) {
                if (countMap[i][c - 'a'] == 0) {
                    continue;
                }
                for(int j = 0; j < 26; j++) {
                    charAvailable[j] += countMap[i][j];
                }
                count(currentCount + 1, pos, charAvailable, target, stickers);
                for(int j = 0; j < 26; j++) {
                    charAvailable[j] -= countMap[i][j];
                }
            }
        }
    }

}

/* (1) keep an character indexed array for all characters we have at hand...
 * (2) traverse each character of the target, if we have the character in out charcter array...
 *     just remove it from the array, and go to check the character at next position
 * (3) if we don't have the character at hand...
 *     we will traverse the sticker, find a word that contains that character, increment the number of stiker used
 *     and update the array of character available
 * (4) we can simplify step (3) by doing some preprocess of the sticker array, create a charcter-indexed count array for each word in
 *     that array
 */