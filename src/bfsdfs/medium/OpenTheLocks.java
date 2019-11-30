/*

You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'.
The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning
one wheel one slot.

The lock initially starts at '0000', a string representing the state of the 4 wheels.

You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning
and you will be unable to open it.

Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to open
the lock, or -1 if it is impossible.

Example 1:
Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
Output: 6
Explanation:
A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
because the wheels of the lock become stuck after the display becomes the dead end "0102".
Example 2:
Input: deadends = ["8888"], target = "0009"
Output: 1
Explanation:
We can turn the last wheel in reverse to move from "0000" -> "0009".
Example 3:
Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
Output: -1
Explanation:
We can't reach the target without getting stuck.
Example 4:
Input: deadends = ["0000"], target = "8888"
Output: -1
Note:
The length of deadends will be in the range [1, 500].
target will not be in the list deadends.
Every string in deadends and the string target will be a string of 4 digits from the 10,000 possibilities '0000' to '9999'.

 */

package bfsdfs.medium;

import java.util.*;

/**
 * Created by poorvank.b on 24/12/17.
 */
public class OpenTheLocks {

    public int openLock(String[] deadends, String target) {
        Set<String> deadSet = new HashSet<>();
        Collections.addAll(deadSet, deadends);
        if(deadSet.contains("0000")) {
            return -1;
        }
        Queue<String> queue = new LinkedList<>();
        queue.add("0000");
        int step=0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0;i<size;i++) {
                String pop = queue.poll();
                if(pop.equals(target)) {
                    return step;
                }
                for(int k=0;k<pop.length();k++) {
                    int c = (int) pop.charAt(k)-'0';
                    String left="",right="";
                    if(c==0) {
                        left = pop.substring(0,k)+""+9+""+pop.substring(k+1);
                        right = pop.substring(0,k)+""+1+""+pop.substring(k+1);
                    } else if(c==9) {
                        left = pop.substring(0,k)+""+8+""+pop.substring(k+1);
                        right = pop.substring(0,k)+""+0+""+pop.substring(k+1);
                    } else {
                        left = pop.substring(0,k)+""+(c-1)+""+pop.substring(k+1);
                        right = pop.substring(0,k)+""+(c+1)+""+pop.substring(k+1);
                    }
                    if(!deadSet.contains(left)) {
                        queue.add(left);
                        deadSet.add(left); // Add to deadset so that we don't visit that combination again.
                    }
                    if(!deadSet.contains(right)) {
                        queue.add(right);
                        deadSet.add(right);
                    }
                }

            }
            step++;
        }
        return -1;
    }

    public static void main(String[] args) {
        String[] deadends = {"0201","0101","0102","1212","2002"};
        String target = "0202";
        System.out.println(new OpenTheLocks().openLock(deadends,target));
    }

}

/*

Bidirectional Search:

public int openLock(String[] deadends, String target) {
        Set<String> begin = new HashSet<>();
        Set<String> end = new HashSet<>();
        Set<String> deads = new HashSet<>(Arrays.asList(deadends));
        begin.add("0000");
        end.add(target);
        int level = 0;
        while(!begin.isEmpty() && !end.isEmpty()) {
            Set<String> temp = new HashSet<>();
            for(String s : begin) {
                if(end.contains(s)) return level;
                if(deads.contains(s)) continue;
                deads.add(s);
                StringBuilder sb = new StringBuilder(s);
                for(int i = 0; i < 4; i ++) {
                    char c = sb.charAt(i);
                    String s1 = sb.substring(0, i) + (c == '9' ? 0 : c - '0' + 1) + sb.substring(i + 1);
                    String s2 = sb.substring(0, i) + (c == '0' ? 9 : c - '0' - 1) + sb.substring(i + 1);
                    if(!deads.contains(s1))
                        temp.add(s1);
                    if(!deads.contains(s2))
                        temp.add(s2);
                }
            }
            level ++;
            begin = end;
            end = temp;
        }
        return -1;
    }

 */