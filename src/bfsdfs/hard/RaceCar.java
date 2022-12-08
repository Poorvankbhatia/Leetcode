/*
Your car starts at position 0 and speed +1 on an infinite number line. Your car can go into negative positions.
Your car drives automatically according to a sequence of instructions 'A' (accelerate) and 'R' (reverse):

When you get an instruction 'A', your car does the following:
position += speed
speed *= 2
When you get an instruction 'R', your car does the following:
If your speed is positive then speed = -1
otherwise speed = 1
Your position stays the same.
For example, after commands "AAR", your car goes to positions 0 --> 1 --> 3 --> 3, and your speed goes to 1 --> 2 --> 4 --> -1.

Given a target position target, return the length of the shortest sequence of instructions to get there.



Example 1:

Input: target = 3
Output: 2
Explanation:
The shortest instruction sequence is "AA".
Your position goes from 0 --> 1 --> 3.
Example 2:

Input: target = 6
Output: 5
Explanation:
The shortest instruction sequence is "AAARA".
Your position goes from 0 --> 1 --> 3 --> 7 --> 7 --> 6.


Constraints:

1 <= target <= 104
 */
package bfsdfs.hard;

import java.util.*;

public class RaceCar {

    public int racecar(int target) {

        int pos = 0;
        int speed = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{pos,speed});
        Set<String> set = new HashSet<>();
        set.add(pos+"~"+speed);
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-->0) {
                int[] poll = queue.poll();
                if(poll[0]==target) {
                    return ans;
                }
                int nextP = poll[0]+poll[1];
                int nextS = poll[1]*2;
                if(set.add(nextP+"~"+nextS) && target-pos>Math.abs(target-nextP)) {
                    queue.add(new int[]{nextP, nextS});
                }
                nextP = poll[0];
                nextS = poll[1]>0?-1:1;
                if(set.add(nextP+"~"+nextS) && nextP>=0) {
                    queue.add(new int[]{nextP, nextS});
                }
            }
            ans++;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new RaceCar().racecar(5478));
    }

}

/*

Target - initial position(0) -> initial_distance.
target - nextPosition -> remaining_distance. There is no point in accelerating("A") when
remaining_distance > initial_distance. If you do, you will go further away from the target,
which will increase the number of steps, so, accelerating now isn't necessary.
But we can reverse ("R"). - Why? coz, you can approach target from both directions,
there might be cases, where you can't hit the target by just accelerating,
so you will have to go further than target(not more than the initial_distance) and reverse till you reach the target

init_pos(0) ------------------- |(T)----------------------------

Case 1:
init_pos(0) --------nextPos---|(T)-----------------------------

T - nextPos < T - init_pos: Can Accelerate and reach T.

Case 2:

init_pos(0) -------------------|(T)------nextPos-----------------------

T-nextPos < T - init_pos: Maybe acceleration is needed after which we can reverse.

The BFS runs at O(target * log(target)) in the worst case, with O(target * log(target)) space.
The reasoning is as follows: in the worst case, all positions in the range [-target, target]
will be visited and for each position there can be as many as 2 * log(target) different speeds.

 */