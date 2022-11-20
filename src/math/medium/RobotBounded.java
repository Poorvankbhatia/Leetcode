/*

On an infinite plane, a robot initially stands at (0, 0) and faces north. Note that:

The north direction is the positive direction of the y-axis.
The south direction is the negative direction of the y-axis.
The east direction is the positive direction of the x-axis.
The west direction is the negative direction of the x-axis.
The robot can receive one of three instructions:

"G": go straight 1 unit.
"L": turn 90 degrees to the left (i.e., anti-clockwise direction).
"R": turn 90 degrees to the right (i.e., clockwise direction).
The robot performs the instructions given in order, and repeats them forever.

Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.



Example 1:

Input: instructions = "GGLLGG"
Output: true
Explanation: The robot is initially at (0, 0) facing the north direction.
"G": move one step. Position: (0, 1). Direction: North.
"G": move one step. Position: (0, 2). Direction: North.
"L": turn 90 degrees anti-clockwise. Position: (0, 2). Direction: West.
"L": turn 90 degrees anti-clockwise. Position: (0, 2). Direction: South.
"G": move one step. Position: (0, 1). Direction: South.
"G": move one step. Position: (0, 0). Direction: South.
Repeating the instructions, the robot goes into the cycle: (0, 0) --> (0, 1) --> (0, 2) --> (0, 1) --> (0, 0).
Based on that, we return true.
Example 2:

Input: instructions = "GG"
Output: false
Explanation: The robot is initially at (0, 0) facing the north direction.
"G": move one step. Position: (0, 1). Direction: North.
"G": move one step. Position: (0, 2). Direction: North.
Repeating the instructions, keeps advancing in the north direction and does not go into cycles.
Based on that, we return false.
Example 3:

Input: instructions = "GL"
Output: true
Explanation: The robot is initially at (0, 0) facing the north direction.
"G": move one step. Position: (0, 1). Direction: North.
"L": turn 90 degrees anti-clockwise. Position: (0, 1). Direction: West.
"G": move one step. Position: (-1, 1). Direction: West.
"L": turn 90 degrees anti-clockwise. Position: (-1, 1). Direction: South.
"G": move one step. Position: (-1, 0). Direction: South.
"L": turn 90 degrees anti-clockwise. Position: (-1, 0). Direction: East.
"G": move one step. Position: (0, 0). Direction: East.
"L": turn 90 degrees anti-clockwise. Position: (0, 0). Direction: North.
Repeating the instructions, the robot goes into the cycle: (0, 0) --> (0, 1) --> (-1, 1) --> (-1, 0) --> (0, 0).
Based on that, we return true.


Constraints:

1 <= instructions.length <= 100
instructions[i] is 'G', 'L' or, 'R'.

 */
package math.medium;

public class RobotBounded {

    public boolean isRobotBounded(String instructions) {

        int i=0;
        int j=0;
        int dir=1;

        for(char c : instructions.toCharArray()){  // Loop through to follow every instruction

            if(c == 'G'){
                if(dir == 1) j++;  //if direction is north, move forward
                else if(dir == 2) i++;  //if direction is East, move right
                else if(dir == 3) j--;  //if direction is South, move downward
                else i--;  //if direction is west, move West
            }
            else if(c == 'L'){  // if asked to turn left
                dir = (dir == 1) ? 4 : dir-1; // subtract 1 from current direction to turn left, if  dir == 1 i.e. North, we need to turn towards west i.e. 4
            }
            else if(c == 'R'){ // if asked to turn right
                dir = (dir == 4) ? 1 : dir+1;  // add 1 from current direction to turn right, if  dir == 4 i.e. West, we need to turn towards North i.e. 1
            }

        }
        return i == 0 && j == 0 || dir > 1;   // check the current position and direction and decide
    }

    public static void main(String[] args) {
        System.out.println(new RobotBounded().isRobotBounded("GLGLGLGRGLGLGLG"));
    }

}

/*

i=0, j=0, dir=1 starting position.
dir can be 1 = North, 2 = East, 3 = South, 4 = West.

Only when position is changed and direction did not change we won't have a cycle.

So in question its given we are initially at 0, 0 at North directions. So we need to keep track of the points as well
as the directions in which the robot travels. So we can have x, y = 0 and directions = North

    Now our problem is to find whether the robot is moving outside the circle after following some instructions.
    So the robot leaves the circle if it keep moving in the North direction.

    So lets loop through each and every character from the instruction string, then:
    1. We check whether its G, if G then we have to move one point from the current position.
        SO if we are at North direction, then if we consider the coordinate, we are at +y directions,
        so we will move only up, just understand like that, SO we increment our y by 1, by following
        this pattern we can automatically deduce that if we are at South, then decrement y by 1.
        Same way for East, increment x by 1 and for West decrement x by 1.
    2. Next we check wheter its L, then we have to move 90 degree left wards.
                    North
            West                East    , So do a counter clockwise assumption. If we are at a directions North, then its
                                          counter clockwis, yes West update direction by west, Same way if our directions
                                          is  South  West, them its counterclockwise south, same way for direction south,
                                          update direction by east.
                                          So just rememeber if character is L, then counterclockwise.
    3. Next whether the character if R, then we already got it remember about clockwise direction and
    update direction according to it
    Finally we check whether the robot get back to the position, if yes, return true as the robot won't go out of the circle.
    We check whether the direction is still North, then it will sure go out of the circle, so return false.
    If none of the above condition satisfies, then also the robot will be some where inside the circle, so return true.

 */