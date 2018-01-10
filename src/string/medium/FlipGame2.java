/*

You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -,
you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move
and therefore the other person will be the winner.

Write a function to determine if the starting player can guarantee a win.

For example, given s = "++++", return true. The starting player can guarantee a win by flipping the middle "++" to become "+--+".

 */
package string.medium;

/**
 * Created by poorvank.b on 31/12/17.
 */
public class FlipGame2 {

    public boolean canWin(String s) {
        return helper(s.toCharArray());
    }

    private boolean helper(char[] s) {

        for (int i=0;i<s.length;i++) {
            if(s[i]=='+' && s[i+1]=='+') {
                s[i]='-';
                s[i+1]='-';
                boolean canOtherWin = helper(s);
                s[i]='+';
                s[i+1]='+';
                if(!canOtherWin) {
                    return true;
                }
            }
        }

        return false;

    }

}

/*

We can use a recursive algorithm to solve this problem. The basic idea is that for each valid move, we check whether
the other player can win. If the first player finds a move that can make the second player looses, the first player wins.
Otherwise, the first player looses. In the process, the second player will take the same strategy.


Time complexity

T(N) = T(N-2) + T(N – 3) + [T(2) + T(N – 4)]  + [T(3) + T(N – 5)] + … [T(N–4) + T(2)] + T(N–3) + T(N–2) = 2 * sum(T[i]) (i =3..N–2)

As we have T(1) = 1, T(2) = 1, T(3) = 2 * (T(1)) = 2, T(4) = 2 *(T(2) + T(1)) = 2, T(5) = 2 * (T(2) + T(3))

So the above formula is similar to the geometric sequence， So the time complexity is  2^N.


The reason is each recursion takes O(n) and there are totally n recursions.

 */
