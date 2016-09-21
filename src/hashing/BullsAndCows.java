/*

You are playing the following Bulls and Cows game with your friend: You write down a number and ask your
friend to guess what the number is. Each time your friend makes a guess, you provide a hint that indicates how many
digits in said guess match your secret number exactly in both digit and position (called "bulls") and how many digits
match the secret number but locate in the wrong position (called "cows"). Your friend will use successive guesses and
hints to eventually derive the secret number.

For example:

Secret number:  "1807"
Friend's guess: "7810"
Hint: 1 bull and 3 cows. (The bull is 8, the cows are 0, 1 and 7.)
Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and B to indicate the cows.
In the above example, your function should return "1A3B".

Please note that both secret number and friend's guess may contain duplicate digits, for example:

Secret number:  "1123"
Friend's guess: "0111"
In this case, the 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow, and your function should return "1A1B".
You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.

 */
package hashing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by poorvank on 20/09/16.
 */
public class BullsAndCows {

    public String getHint(String secret, String guess) {

        HashMap<Character,Integer> countMap = new HashMap<>();

        for (int i=0;i<guess.length();i++) {
            if(countMap.containsKey(secret.charAt(i))) {
                countMap.put(secret.charAt(i),countMap.get(secret.charAt(i))+1);
            } else {
                countMap.put(secret.charAt(i),1);
            }
        }

        int A=0,B=0;

        Set<Integer> matchingPos = new HashSet<>();

        for (int i=0;i<guess.length();i++) {
            if(guess.charAt(i)==secret.charAt(i)) {
                A++;
                matchingPos.add(i);
                countMap.put(secret.charAt(i),countMap.get(secret.charAt(i))-1);
            }
        }

        for (int i=0;i<guess.length();i++) {
           if(!matchingPos.contains(i)) {
               Character c = guess.charAt(i);
               if(countMap.containsKey(c) && countMap.get(c)>0) {
                   B++;
                   countMap.put(guess.charAt(i),countMap.get(guess.charAt(i))-1);
               }
           }
        }

        return A+"A"+B+"B";


    }

    public static void main(String[] args) {
        System.out.println(new BullsAndCows().getHint("1807","7810"));
    }

}
