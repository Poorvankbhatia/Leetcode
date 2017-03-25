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

    private class Cell {
        String val;
        int level;

        public Cell(String val, int level) {
            this.val = val;
            this.level = level;
        }
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        if(wordList==null || beginWord==null || endWord==null) {
            return 0;
        }

        HashMap<String,List<String>> map = new HashMap<>();

        Set<String> set = new HashSet<>(wordList);

        if(!set.contains(beginWord)) {
            set.add(beginWord);
        }

        fillMap(set,map);

        Queue<Cell> queue = new LinkedList<>();
        queue.add(new Cell(beginWord,0));
        Set<String> usedWords = new HashSet<>();

        while (!queue.isEmpty()) {

            Cell current = queue.poll();

            if(current.val.equals(endWord)) {
                return current.level+1;
            }

            if(usedWords.contains(current.val)) {
                continue;
            }

            usedWords.add(current.val);
            List<String> currentNeighbours = map.get(current.val);

            if(currentNeighbours!=null) {
                for (String neighbours : currentNeighbours) {
                    if(usedWords.contains(neighbours)) {
                        continue;
                    }
                    Cell newCell = new Cell(neighbours,current.level+1);
                    queue.add(newCell);
                }
            }


        }

        return 0;

    }

    private void fillMap(Set<String> wordList,HashMap<String,List<String>> map) {

        for (String word : wordList) {

            for (int i=0;i<word.length();i++) {

                char[] arr = word.toCharArray();
                for (char c ='a';c<='z';c++) {

                    char temp = arr[i];
                    arr[i] = c;

                    String newWord = new String(arr);
                    if(!newWord.equals(word) && wordList.contains(newWord)) {
                        List<String> list;
                        if(map.containsKey(word)) {
                            list = map.get(word);
                            list.add(newWord);
                        } else {
                            list = new ArrayList<>();
                            list.add(newWord);
                        }
                        map.put(word,list);
                    }

                    arr[i] = temp;

                }

            }

        }

    }

    public static void main(String[] args) {

        List<String> wordList = new ArrayList<>(Arrays.asList("hot","dot","dog","lot","log","cog"));
        String beginWord = "hit";
        String endWord = "cog";

        System.out.print(new WordLadder().ladderLength(beginWord,endWord,wordList));

    }

}
