/*


Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest
transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
For example,

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log","cog"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

 */

package bfsdfs.medium;

import java.util.*;

/**
 * Created by poorvank.b on 22/03/17.
 */
public class WordLadder {


    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        if (wordList == null || beginWord == null || endWord == null) {
            return 0;
        }

        HashMap<String, List<String>> map = new HashMap<>();

        Set<String> set = new HashSet<>(wordList);

        if (!set.contains(beginWord)) {
            set.add(beginWord);
        }

        fillMap(set, map);

        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);

        int level = 0;
        while (!queue.isEmpty()) {

            int size = queue.size();
            while (size > 0) {
                String current = queue.poll();
                if (current.equals(endWord)) {
                    return level + 1; // Since we need to return the total transformation and not just jumps
                }
                List<String> neighbours = map.get(current);
                if (neighbours != null) {
                    for (String neighbour : neighbours) {
                        queue.add(neighbour);
                    }
                }
                map.remove(current);
                size--;
            }

            level++;

        }

        return 0;

    }

    private void fillMap(Set<String> wordList, HashMap<String, List<String>> map) {

        for (String word : wordList) {

            for (int i = 0; i < word.length(); i++) {

                char[] arr = word.toCharArray();
                for (char c = 'a'; c <= 'z'; c++) {

                    char temp = arr[i];
                    arr[i] = c;

                    String newWord = new String(arr);
                    if (!newWord.equals(word) && wordList.contains(newWord)) {
                        if (!map.containsKey(word)) {
                            map.put(word, new ArrayList<>());
                        }
                        map.get(word).add(newWord);
                    }

                    arr[i] = temp;

                }

            }

        }

    }


    public static void main(String[] args) {

        List<String> wordList = new ArrayList<>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
        String beginWord = "hit";
        String endWord = "cog";

        System.out.print(new WordLadder().ladderLength(beginWord, endWord, wordList));

    }

}
