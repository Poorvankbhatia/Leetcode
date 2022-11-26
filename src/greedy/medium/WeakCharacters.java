package greedy.medium;

import java.util.*;

public class WeakCharacters {

    public int numberOfWeakCharacters(int[][] properties) {
        // Sort in ascending order of attack,
        // If attack is same sort in descending order of defense
        // Consider [(1, 2), (3, 4), (3, 6), (3, 7)]
        // when we reach the pair (3, 6), and we will consider this pair to be weak.
        // Although, it's not as the attack value is equal and not strictly greater.
        // The point to note here is, that we need to ignore the defense value of the pairs with the same attack value.
        // Hence sorting pairs ascending order of their attack value and then in descending order of their defense value
        // in case of a tie. This way, the above list would be [(1, 2), (3, 7), (3, 6), (3, 4)]
        // and hence when we iterate over it from the right end, the maximum defense value
        // will be equal to 4 when we reach the pair (3, 6).
        // same as russian doll envelopes.
        Arrays.sort(properties, (a, b) -> (a[0] == b[0]) ? (b[1] - a[1]) : a[0] - b[0]);

        int weakCharacters = 0;
        int maxDefense = 0;
        for (int i = properties.length - 1; i >= 0; i--) {
            // Compare the current defense with the maximum achieved so far
            if (properties[i][1] < maxDefense) {
                weakCharacters++;
            }
            maxDefense = Math.max(maxDefense, properties[i][1]);
        }

        return weakCharacters;
    }

    public static void main(String[] args) {
        int[][] properties = new int[][] {{7,7},{1,2},{9,7},{7,3},{3,10},{9,8},{8,10},{4,3},{1,5},{1,5}};
        System.out.println(new WeakCharacters().numberOfWeakCharacters(properties));
    }


}

/*
O(NLogN)

O(N+K)

Iterate over properties, and store the maximum defense value for attack values in the array maxDefense.

Iterate over all the possible values of attack from the maximum possible attack value (100000) to 0.
Keep the maximum value seen so far, maxDefense[i] will represent the maximum value in the suffix [i, maxAttack].

Iterate over the properties for every pair (attack, defense), increment the counter weakCharacters
if the value at maxDefense[attack + 1] is greater than defense.

Return weakCharacters.

class Solution {
    public int numberOfWeakCharacters(int[][] properties) {
        int maxAttack = 0;
        // Find the maximum atack value
        for (int[] property : properties) {
            int attack = property[0];
            maxAttack = Math.max(maxAttack, attack);
        }

        int maxDefense[] = new int[maxAttack + 2];
        // Store the maximum defense for an attack value
        for (int[] property : properties) {
            int attack = property[0];
            int defense = property[1];

            maxDefense[attack] = Math.max(maxDefense[attack], defense);
        }

        // Store the maximum defense for attack greater than or equal to a value
        for (int i = maxAttack - 1; i >= 0; i--) {
            maxDefense[i] = Math.max(maxDefense[i], maxDefense[i + 1]);
        }

        int weakCharacters = 0;
        for (int[] property : properties) {
            int attack = property[0];
            int defense = property[1];

            // If their is a greater defense for properties with greater attack
            if (defense < maxDefense[attack + 1]) {
                weakCharacters++;
            }
        }

        return weakCharacters;
    }
}


 */