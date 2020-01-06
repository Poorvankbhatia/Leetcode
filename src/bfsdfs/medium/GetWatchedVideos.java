/*

There are n people, each person has a unique id between 0 and n-1. Given the arrays watchedVideos and friends, where watchedVideos[i] and friends[i]
contain the list of watched videos and the list of friends respectively for the person with id = i.

Level 1 of videos are all watched videos by your friends, level 2 of videos are all watched videos by the friends of your friends and so on. In general,
the level k of videos are all watched videos by people with the shortest path equal to k with you. Given your id and the level of videos,
return the list of videos ordered by their frequencies (increasing). For videos with the same frequency order them alphabetically from least to greatest.


Input: watchedVideos = [["A","B"],["C"],["B","C"],["D"]], friends = [[1,2],[0,3],[0,3],[1,2]], id = 0, level = 1
Output: ["B","C"]
Explanation:
You have id = 0 (green color in the figure) and your friends are (yellow color in the figure):
Person with id = 1 -> watchedVideos = ["C"]
Person with id = 2 -> watchedVideos = ["B","C"]
The frequencies of watchedVideos by your friends are:
B -> 1
C -> 2

Input: watchedVideos = [["A","B"],["C"],["B","C"],["D"]], friends = [[1,2],[0,3],[0,3],[1,2]], id = 0, level = 2
Output: ["D"]
Explanation:
You have id = 0 (green color in the figure) and the only friend of your friends is the person with id = 3 (yellow color in the figure).


Constraints:

n == watchedVideos.length == friends.length
2 <= n <= 100
1 <= watchedVideos[i].length <= 100
1 <= watchedVideos[i][j].length <= 8
0 <= friends[i].length < n
0 <= friends[i][j] < n
0 <= id < n
1 <= level < n
if friends[i] contains j, then friends[j] contains i


 */
package bfsdfs.medium;

import java.util.*;

public class GetWatchedVideos {

    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {

        boolean[] levelFriends = new boolean[101];
        Set<Integer> visited = new HashSet<>();
        visited.add(id);
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{id,0});
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size>0) {
                int[] pop = queue.poll();
                assert pop!=null;
                if(pop[1]==level) {
                    levelFriends[pop[0]]=true;
                }
                if(pop[1]<level) {
                    for (int friend : friends[pop[0]]) {
                        if(visited.add(friend)) {
                            queue.add(new int[]{friend,pop[1]+1});
                        }
                    }
                }
                size--;
            }
        }
        HashMap<String,Integer> freq = new HashMap<>();
        for (int i=0;i<levelFriends.length;i++) {
            if(levelFriends[i]) {
                for (String video : watchedVideos.get(i)) {
                    freq.put(video,freq.getOrDefault(video,0)+1);
                }
            }
        }
        List<String> ans = new ArrayList<>(freq.keySet());

        ans.sort((a, b) -> {
            int fa = freq.get(a);
            int fb = freq.get(b);
            if (fa != fb) {
                return fa - fb;
            } else {
                return a.compareTo(b);
            }
        });

        return ans;
    }

}
