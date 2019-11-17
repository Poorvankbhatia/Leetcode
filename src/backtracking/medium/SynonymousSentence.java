/*

Given a list of pairs of equivalent words synonyms and a sentence text, Return all possible synonymous sentences sorted lexicographically.


Example 1:

Input:
synonyms = [["happy","joy"],["sad","sorrow"],["joy","cheerful"]],
text = "I am happy today but was sad yesterday"
Output:
["I am cheerful today but was sad yesterday",
​​​​​​​"I am cheerful today but was sorrow yesterday",
"I am happy today but was sad yesterday",
"I am happy today but was sorrow yesterday",
"I am joy today but was sad yesterday",
"I am joy today but was sorrow yesterday"]


Constraints:

0 <= synonyms.length <= 10
synonyms[i].length == 2
synonyms[0] != synonyms[1]
All words consist of at most 10 English letters only.
text is a single space separated sentence of at most 10 words.

 */
package backtracking.medium;

import java.util.*;
import java.util.stream.Collectors;

public class SynonymousSentence {
    public List<String> generateSentences(List<List<String>> synonyms, String text) {
        Map<String, List<String>> graph = new HashMap<>();
        for (List<String> synonymPair : synonyms) {
            String w1 = synonymPair.get(0), w2 = synonymPair.get(1);
            connect(graph, w1, w2);
            connect(graph, w2, w1);
        }
        // BFS
        Set<String> ans = new TreeSet<>();
        Queue<String> q = new LinkedList<>();
        q.add(text);
        while (!q.isEmpty()) {
            String out = q.remove();
            ans.add(out); // Add to result
            String[] words = out.split("\\s");
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                if (graph.get(word) == null) continue;
                for (String neighbor : graph.get(word)) {
                    words[i] = neighbor;
                    String newText = String.join(" ", words);
                    if (!ans.contains(newText)) q.add(newText);
                }
            }
        }
        return new ArrayList<>(ans);
    }

    private void connect(Map<String, List<String>> graph, String v1, String v2) {
        graph.computeIfAbsent(v1, k -> new LinkedList<>());
        graph.get(v1).add(v2);
    }


}

/*

synonyms = [["happy","joy"],["strong","healthy"],["joy","cheerful"]],
text = "I am happy and strong"
This solution will work like below picture
 */