/*

In the video game Fallout 4, the quest "Road to Freedom" requires players to reach a metal dial called the "Freedom Trail Ring",
 and use the dial to spell a specific keyword in order to open the door.

Given a string ring, which represents the code engraved on the outer ring and another string key, which represents the keyword needs to be spelled.
 You need to find the minimum number of steps in order to spell all the characters in the keyword.

Initially, the first character of the ring is aligned at 12:00 direction. You need to spell all the characters in the string key one by one by
rotating the ring clockwise or anticlockwise to make each character of the string key aligned at 12:00 direction and then by pressing the center button.
At the stage of rotating the ring to spell the key character key[i]:
You can rotate the ring clockwise or anticlockwise one place, which counts as 1 step. The final purpose of the rotation is to align one of the
string ring's characters at the 12:00 direction, where this character must equal to the character key[i].
If the character key[i] has been aligned at the 12:00 direction, you need to press the center button to spell, which also counts as 1 step.
 After the pressing, you could begin to spell the next character in the key (next stage), otherwise, you've finished all the spelling.


 Input: ring = "godding", key = "gd"
Output: 4
Explanation:
 For the first key character 'g', since it is already in place, we just need 1 step to spell this character.
 For the second key character 'd', we need to rotate the ring "godding" anticlockwise by two steps to make it become "ddinggo".
 Also, we need 1 more step for spelling.
 So the final output is 4.
Note:
Length of both ring and key will be in range 1 to 100.
There are only lowercase letters in both strings and might be some duplcate characters in both strings.
It's guaranteed that string key could always be spelled by rotating the string ring.

 */

package dyanamicprogramming.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by poorvank.b on 17/03/17.
 */
public class FreedomTrial {

    //For every configuration we store minimum distance from starting of ring to current char of key
    Map<String,Integer> map;
    public int findRotateSteps(String ring, String key) {
        map = new HashMap<>();
        return key.length()+countRotations(ring,key,0);
    }

    private int firstOccurrence(String ring,char c) {
        return ring.indexOf(c);
    }

    private int lastOccurrence(String ring,char c) {
        if(ring.charAt(0)==c) {
            return 0;
        }

        return ring.lastIndexOf(c);
    }

    private int countRotations(String ring,String key,int keyIndex) {
        if(keyIndex==key.length()) {
            return 0;
        }
        String mapKey = keyIndex+"_"+ring;
        if(map.get(mapKey)!=null) {
            return map.get(mapKey);
        }

        int first = firstOccurrence(ring,key.charAt(keyIndex));
        int last = lastOccurrence(ring ,key.charAt(keyIndex));
        int clockwise = first+countRotations(ring.substring(first)+ring.substring(0,first),key,keyIndex+1);
        int anticlockwise = ring.length()-last+countRotations(ring.substring(last)+ring.substring(0,last),key,keyIndex+1);
        int min = Math.min(clockwise,anticlockwise);
        map.put(mapKey,min);
        return min;
    }

    public static void main(String[] args) {
        String ring = "godding";
        String key = "gg";
        System.out.print(new FreedomTrial().findRotateSteps(ring,key));
    }

}

/*

The key point in the problem is to make decision whether to move clockwise or anticlockwise.
Actually to get optimal answer, we have to move clockwise for some characters of key and anti-clockwise for others.
If apply brute force, then for each position in key we have two options,\
Search for the character clockwise
Search for the character anti-clockwise

To find optimal answer we need to try both options and get minimum of them.
Thus, we obtain dfs solution for the problem. But, there are duplicate calculation for some positions.
Therefore, we need to memorize states. The state is defined by position of the ring and the index of character in the key.
This way, we can avoid calculating number of steps for the same state.

 */