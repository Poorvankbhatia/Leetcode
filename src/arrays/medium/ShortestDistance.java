package arrays.medium;/*

Given a children of words and two words word1 and word2, return the shortest distance between these two words in the children.
For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
Given word1 = “coding”, word2 = “practice”, return 3.
Given word1 = "makes", word2 = "coding", return 1.

Word1 & Word2 can be equal also.

 */

/**
 * Created by poorvank on 21/08/16.
 */
public class ShortestDistance {

    private static int value(String[] words,String word1,String word2) {

        int index = -1;
        int min = words.length;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1) || words[i].equals(word2)) {
                if (index != -1 && (word1.equals(word2) || !words[index].equals(words[i]))) {
                    min = Math.min(i - index, min);
                }
                index = i;
            }
        }
        return min;

    }

    public static void main(String[] args) {
        String[] arr = new String[]{"practice", "makes", "perfect", "coding", "makes","Hello","makes"};
        String word1 = "makes";
        String word2 = "coding";
        System.out.println("Minimum distance between " + word1 + " & " + word2 +" = " + ShortestDistance.value(arr,word1,word2));

    }


}

/*

 A cleaner method would be to check if words are equal , assign i to j i.e
 if(wordsAreEqual) {
    i=j
 }

 */