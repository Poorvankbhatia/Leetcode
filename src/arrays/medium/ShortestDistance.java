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

    private static int value(String[] arr,String word1,String word2) {

        int i=-1,j=-1,k;
        int minDistance = 0;
        boolean flag= true;
        boolean wordsAreEqual = word1.equals(word2);
        for (k=0;k<arr.length;k++) {

            if(word1.equals(arr[k]) && flag) {
                i = k;
                if(wordsAreEqual) {
                    flag=!flag;
                }
            } else if(word2.equals(arr[k])) {
                j = k;
                if(wordsAreEqual) {
                    flag=!flag;
                }
            }

            if(i!=-1 && j!=-1) {
                minDistance = Math.abs(i-j);
            }

        }

        return minDistance;

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