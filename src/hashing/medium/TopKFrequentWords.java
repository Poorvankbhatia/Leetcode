/*

Given a non-empty list of words, return the k most frequent elements.

Your answer should be sorted by frequency from highest to lowest.
If two words have the same frequency, then the word with the lower alphabetical order comes first.

Example 1:
Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
Output: ["i", "love"]
Explanation: "i" and "love" are the two most frequent words.
    Note that "i" comes before "love" due to a lower alphabetical order.
Example 2:
Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
Output: ["the", "is", "sunny", "day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
    with the number of occurrence being 4, 3, 2 and 1 respectively.
Note:
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Input words contain only lowercase letters.


 */
package hashing.medium;

import java.util.*;

/**
 * Created by poorvank.b on 15/10/17.
 */
public class TopKFrequentWords {

    public List<String> topKFrequent(String[] words, int k) {

        Map<String,Integer> count = new HashMap<>();

        int max = Integer.MIN_VALUE;
        for (String s: words) {
            if(!count.containsKey(s)) {
                count.put(s,0);
            }
            count.put(s,count.get(s)+1);
            max = Math.max(count.get(s),max);
        }

        ArrayList<ArrayList<String>> lists = new ArrayList<>(max + 1);

        for (int i = 0; i < max + 1; i++) {
            lists.add(new ArrayList<>());
        }

        for (Map.Entry<String, Integer> entry : count.entrySet()) {
            lists.get(entry.getValue()).add(entry.getKey());
        }

        List<String> result = new ArrayList<>();
        for (int i=lists.size()-1;i>=0;i--) {
            if (lists.get(i) != null && lists.get(i).size() > 0) {
                Collections.sort(lists.get(i));
                for (int j=0;j<lists.get(i).size();j++) {
                    result.add(lists.get(i).get(j));
                    k--;
                    if(k==0) {
                        return result;
                    }
                }
            }
        }

        return result;

    }

    public static void main(String[] args) {
        String[] arr = new String[]{"i", "love", "leetcode", "i", "love", "coding"};
        System.out.println(new TopKFrequentWords().topKFrequent(arr,4));
    }


}

/*

https://stackoverflow.com/questions/185697/the-most-efficient-way-to-find-top-k-frequent-words-in-a-big-word-sequence

 */
