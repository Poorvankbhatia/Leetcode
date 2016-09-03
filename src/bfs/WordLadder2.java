/*

Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s)
from beginWord to endWord, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the word list
For example,

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]
Return
  [
    ["hit","hot","dot","dog","cog"],
    ["hit","hot","lot","log","cog"]
  ]
Note:
All words have the same length.
All words contain only lowercase alphabetic characters.

 */

package bfs;

import java.util.*;

/**
 * Created by poorvank on 03/09/16.
 */



public class WordLadder2 {

    class Word {

        String value;
        List<String> predecessors;
        int level;

        public Word(String value, List<String> predecessors, int level) {
            this.value = value;
            this.predecessors = predecessors;
            this.level = level;
        }
    }

    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {

        List<List<String>> result = new ArrayList<>();

        HashMap<String,List<String>> wordMap = new HashMap<>();

        /*
            Preprocess entire list to find words which are only one character away.
         */
        for (String word : wordList) {

            for (int i = 0; i < word.length(); i++) {
                char[] arr = word.toCharArray();

                for (char c = 'a'; c <= 'z'; c++) {
                    char temp = arr[i];
                    if (arr[i] != c) {
                        arr[i] = c;
                    }
                    String str = new String(arr);
                    if (str.equals(word)) {
                        continue;
                    }
                    if (wordList.contains(str)) {
                        if (wordMap.containsKey(word)) {
                            wordMap.get(word).add(str);
                        } else {
                            List<String> list = new ArrayList<>();
                            list.add(str);
                            wordMap.put(word, list);
                        }
                    }
                    arr[i] = temp;

                }

            }

        }
        ladderUtil(beginWord,endWord,wordList,result,wordMap);
        return result;

    }


    private void ladderUtil(String beginWord,String endWord,Set<String>
            wordList,List<List<String>> result,HashMap<String,List<String>> wordMap) {


        if(!wordList.contains(endWord)) {
            wordList.add(endWord);
        }
        Queue<Word> queue = new LinkedList<>();
        queue.add(new Word(beginWord,null,0));

        int firstReach = -1;
        //Once a word is used , it should not be used again
        HashSet<String> usedWords = new HashSet<>();

        while (!queue.isEmpty()) {

            Word current = queue.poll();
            List<String> list = new ArrayList<>();

            //As soon as a word is visited once remove it form the set, so that it is not visited again
            if(wordList.contains(current.value)) {
                usedWords.add(current.value);
            }

            //When final word is reached
            if(current.value.equals(endWord)) {
                List<String> finalList= new ArrayList<>(current.predecessors);
                finalList.add(current.value);
                if(firstReach==-1) {
                    result.add(finalList);
                    firstReach = current.level;
                } else {
                    if(current.level==firstReach) {
                        result.add(new ArrayList<>(finalList));
                    }
                }
                continue;
            }

            if(current.predecessors !=null) {
                list = new ArrayList<>(current.predecessors);
            }

            List<String> reachList = wordMap.get(current.value);

            if(reachList==null) {
                continue;
            }

            for (String oneStepWords : reachList) {
                if(usedWords.contains(oneStepWords)) {
                    continue;
                }
                List<String> childList = new ArrayList<>(list);
                childList.add(current.value);
                Word newWord = new Word(oneStepWords,childList,current.level+1);
                queue.add(newWord);
            }


        }

    }

    public static void main(String[] args) {

        String beginWord =  "hot";
        String endWord =  "dog";
        Set<String> set = new HashSet<>(Arrays.asList("hot","dog"));
        WordLadder2 wl = new WordLadder2();
        System.out.println(wl.findLadders(beginWord,endWord,set));
    }

}
