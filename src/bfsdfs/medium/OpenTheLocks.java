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

        Set<String> set = new HashSet<>();

        if(deadends!=null) {
            Collections.addAll(set, deadends);
        }

        if(target==null || set.contains(target) || set.contains("0000")) {
            return -1;
        }

        Queue<String> queue = new LinkedList<>();
        queue.add("0000");
        Set<String> covered = new HashSet<>();
        covered.add("0000");

        int result=0;

        while (!queue.isEmpty()) {

            int size=queue.size();

            for (int i=0;i<size;i++) {
                String current = queue.remove();

                if(current.equals(target)) {
                    return result;
                }

                if(set.contains(current)) {
                    continue;
                }

                List<String> list = getNext(set,current);

                for (String nextLock : list) {
                    if(!covered.contains(nextLock)) {
                        queue.add(nextLock);
                        covered.add(nextLock); // This addition is done here because 1100 can be reached from both 0100 and 1000, So only visited once.
                    }
                }
            }

            result++;

        }

        return -1;


    }

    private List<String> getNext(Set<String> set,String start) {

        List<String> list = new ArrayList<>();

        if(!set.contains(start)) {
            StringBuilder sb = new StringBuilder(start);
            for (int i=0;i<4;i++) {
                Character c = sb.charAt(i);
                if(c=='0') {

                    sb.setCharAt(i,'9');
                    list.add(sb.toString());

                    sb.setCharAt(i,'1');
                    list.add(sb.toString());

                    sb.setCharAt(i,c);

                } else if(c=='9') {

                    sb.setCharAt(i,'0');
                    list.add(sb.toString());

                    sb.setCharAt(i,'8');
                    list.add(sb.toString());

                    sb.setCharAt(i,c);

                } else {

                    char next = (char) ((int)c + 1);
                    sb.setCharAt(i,next);
                    list.add(sb.toString());

                    char prev = (char) ((int)c - 1);
                    sb.setCharAt(i,prev);
                    list.add(sb.toString());

                    sb.setCharAt(i,c);
                }
            }

        }

        return list;

    }

    public static void main(String[] args) {
        String[] deadends = {"0201","0101","0102","1212","2002"};
        String target = "0202";
        System.out.println(new OpenTheLocks().openLock(deadends,target));
    }

}
