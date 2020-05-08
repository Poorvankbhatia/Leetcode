/*

Given the string croakOfFrogs, which represents a combination of the string "croak" from different frogs, that is,
multiple frogs can croak at the same time, so multiple “croak” are mixed. Return the minimum number of different frogs to finish all the croak in the given string.

A valid "croak" means a frog is printing 5 letters ‘c’, ’r’, ’o’, ’a’, ’k’ sequentially. The frogs have to print all f
ive letters to finish a croak. If the given string is not a combination of valid "croak" return -1.



Example 1:

Input: croakOfFrogs = "croakcroak"
Output: 1
Explanation: One frog yelling "croak" twice.
Example 2:

Input: croakOfFrogs = "crcoakroak"
Output: 2
Explanation: The minimum number of frogs is two.
The first frog could yell "crcoakroak".
The second frog could yell later "crcoakroak".
Example 3:

Input: croakOfFrogs = "croakcrook"
Output: -1
Explanation: The given string is an invalid combination of "croak" from different frogs.
Example 4:

Input: croakOfFrogs = "croakcroa"
Output: -1


Constraints:

1 <= croakOfFrogs.length <= 10^5
All characters in the string are: 'c', 'r', 'o', 'a' or 'k'.

 */
package string.medium;

public class FrogsCroaking {

    public int minNumberOfFrogs(String croakOfFrogs) {

        if(croakOfFrogs.length()%5!=0) {
            return -1;
        }
        int ans = 0;
        int frogs = 0;
        int[] count = new int[5];

        for (char c : croakOfFrogs.toCharArray()) {
            switch (c) {
                case 'c':
                    frogs++;
                    ans = Math.max(ans,frogs);
                    count[0]++;
                    break;
                case 'r':
                    if(count[0]>count[1]) {
                        count[1]++;
                    } else {
                        return -1;
                    }
                    break;
                case 'o':
                    if(count[1]>count[2]) {
                        count[2]++;
                    } else {
                        return -1;
                    }
                    break;
                case 'a':
                    if(count[2]>count[3]) {
                        count[3]++;
                    } else {
                        return -1;
                    }
                    break;
                case 'k':
                    if(count[3]<count[4]) {
                        return -1;
                    }
                    frogs--;
            }
        }

        return frogs==0?ans:-1;

    }

    public static void main(String[] args) {
        System.out.println(new FrogsCroaking().minNumberOfFrogs("croakk"));
    }

}

/*

Better looking Solution:
public int minNumberOfFrogs(String croakOfFrogs) {
    int cnt[] = new int[5];
    int frogs = 0, max_frogs = 0;
    for (var i = 0; i < croakOfFrogs.length(); ++i) {
        var ch = croakOfFrogs.charAt(i);
        var n = "croak".indexOf(ch);
        ++cnt[n];
        if (n == 0)
            max_frogs = Math.max(max_frogs, ++frogs);
        else if (--cnt[n - 1] < 0)
            return -1;
        else if (n == 4)
            --frogs;
    }
    return frogs == 0 ? max_frogs : -1;
}

Also check : MeetingRooms2

 */