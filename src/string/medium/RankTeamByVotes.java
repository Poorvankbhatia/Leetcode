/*
In a special ranking system, each voter gives a rank from highest to lowest to all teams participated in the competition.

The ordering of teams is decided by who received the most position-one votes. If two or more teams tie in the first position,
we consider the second position to resolve the conflict, if they tie again, we continue this process until the ties are resolved.
If two or more teams are still tied after considering all positions, we rank them alphabetically based on their team letter.

Given an array of strings votes which is the votes of all voters in the ranking systems. Sort all teams according to the ranking system described above.

Return a string of all teams sorted by the ranking system.



Example 1:

Input: votes = ["ABC","ACB","ABC","ACB","ACB"]
Output: "ACB"
Explanation: Team A was ranked first place by 5 voters. No other team was voted as first place so team A is the first team.
Team B was ranked second by 2 voters and was ranked third by 3 voters.
Team C was ranked second by 3 voters and was ranked third by 2 voters.
As most of the voters ranked C second, team C is the second team and team B is the third.
Example 2:

Input: votes = ["WXYZ","XYZW"]
Output: "XWYZ"
Explanation: X is the winner due to tie-breaking rule. X has same votes as W for the first position but X has one vote as second position
while W doesn't have any votes as second position.
Example 3:

Input: votes = ["ZMNAGUEDSJYLBOPHRQICWFXTVK"]
Output: "ZMNAGUEDSJYLBOPHRQICWFXTVK"
Explanation: Only one voter so his votes are used for the ranking.
Example 4:

Input: votes = ["BCA","CAB","CBA","ABC","ACB","BAC"]
Output: "ABC"
Explanation:
Team A was ranked first by 2 voters, second by 2 voters and third by 2 voters.
Team B was ranked first by 2 voters, second by 2 voters and third by 2 voters.
Team C was ranked first by 2 voters, second by 2 voters and third by 2 voters.
There is a tie and we rank teams ascending by their IDs.
Example 5:

Input: votes = ["M","M","M","M"]
Output: "M"
Explanation: Only team M in the competition so it has the first rank.


Constraints:

1 <= votes.length <= 1000
1 <= votes[i].length <= 26
votes[i].length == votes[j].length for 0 <= i, j < votes.length.
votes[i][j] is an English upper-case letter.
All characters of votes[i] are unique.
All the characters that occur in votes[0] also occur in votes[j] where 1 <= j < votes.length.
 */
package string.medium;

import java.util.*;

public class RankTeamByVotes {

    //Item class contains character with counts at different positions
    private class Item {
        char c;
        int[] arr;

        Item(char c, int[] arr) {
            this.c = c;
            this.arr = arr;
        }
    }

    public String rankTeams(String[] votes) {

        if(votes.length==1) {
            return votes[0];
        }
        int len = votes[0].length();
        // Mapping of each character with its item class(Counts at every position)
        Map<Character,Item> map = new HashMap<>();
        for (String vote : votes) {
            for (int i=0;i<vote.length();i++) {
                char c = vote.charAt(i);
                map.putIfAbsent(c,new Item(c,new int[len]));
                map.get(c).arr[i]++;
            }
        }
        // Add to a list
        List<Item> list = new ArrayList<>();
        for (Map.Entry<Character,Item> entry : map.entrySet()) {
            list.add(entry.getValue());
        }
        //Sort the list.
        list.sort((o1, o2) -> {
            int[] a1 = o1.arr;
            int[] a2 = o2.arr;
            for (int i=0;i<len;i++) {
                if(a1[i]!=a2[i]) {
                    return a2[i]-a1[i];
                }
            }
            return o1.c-o2.c;
        });

        //Final Result.
        StringBuilder result = new StringBuilder();
        for (Item item : list) {
            result.append(item.c);
        }

        return result.toString();

    }

}
