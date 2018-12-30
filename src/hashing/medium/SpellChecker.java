/*

Given a wordlist, we want to implement a spellchecker that converts a query word into a correct word.

For a given query word, the spell checker handles two categories of spelling mistakes:

Capitalization: If the query matches a word in the wordlist (case-insensitive),
then the query word is returned with the same case as the case in the wordlist.
Example: wordlist = ["yellow"], query = "YellOw": correct = "yellow"
Example: wordlist = ["Yellow"], query = "yellow": correct = "Yellow"
Example: wordlist = ["yellow"], query = "yellow": correct = "yellow"
Vowel Errors: If after replacing the vowels ('a', 'e', 'i', 'o', 'u') of the query word with any vowel individually,
it matches a word in the wordlist (case-insensitive), then the query word is returned with the same case as the match in the wordlist.
Example: wordlist = ["YellOw"], query = "yollow": correct = "YellOw"
Example: wordlist = ["YellOw"], query = "yeellow": correct = "" (no match)
Example: wordlist = ["YellOw"], query = "yllw": correct = "" (no match)
In addition, the spell checker operates under the following precedence rules:

When the query exactly matches a word in the wordlist (case-sensitive), you should return the same word back.
When the query matches a word up to capitlization, you should return the first such match in the wordlist.
When the query matches a word up to vowel errors, you should return the first such match in the wordlist.
If the query has no matches in the wordlist, you should return the empty string.
Given some queries, return a list of words answer, where answer[i] is the correct word for query = queries[i].



Example 1:

Input: wordlist = ["KiTe","kite","hare","Hare"], queries = ["kite","Kite","KiTe","Hare","HARE","Hear","hear","keti","keet","keto"]
Output: ["kite","KiTe","KiTe","Hare","hare","","","KiTe","","KiTe"]


Note:

1 <= wordlist.length <= 5000
1 <= queries.length <= 5000
1 <= wordlist[i].length <= 7
1 <= queries[i].length <= 7
All strings in wordlist and queries consist only of english letters.

 */
package hashing.medium;

import java.util.*;

/**
 * Created by poorvank.b on 30/12/18.
 */
public class SpellChecker {

    public String[] spellchecker(String[] wordlist, String[] queries) {
        Map<String,List<String>> map = new HashMap<>();
        Set<String> orignal = new HashSet<>();
        Map<String,List<String>> vowelMap = new HashMap<>();

        for(String word :  wordlist) {
            orignal.add(word);
            String key = word.toLowerCase();
            if(map.containsKey(key)) {
                map.get(key).add(word);
            } else {
                List<String> list = new ArrayList<>();
                list.add(word);
                map.put(key,list);
            }
            String vowel = getVowelString(word.toLowerCase());
            if(vowelMap.containsKey(vowel)) {
                vowelMap.get(vowel).add(word);
            } else {
                List<String> vowelList = new ArrayList<>();
                vowelList.add(word);
                vowelMap.put(vowel,vowelList);
            }
        }

        String[] result = new String[queries.length];
        int i=0;
        for(String query : queries) {
            if(orignal.contains(query)) {
                result[i]=query;
            } else if(map.containsKey(query.toLowerCase())) {
                result[i]=map.get(query.toLowerCase()).get(0);
            } else if(vowelMap.containsKey(getVowelString(query.toLowerCase()))) {
                result[i]=vowelMap.get(getVowelString(query.toLowerCase())).get(0);
            } else {
                result[i]="";
            }
            i++;
        }

        return result;

    }

    private String getVowelString(String s) {
        StringBuilder sb = new StringBuilder();
        for(char c : s.toCharArray()) {
            if(c=='a' || c=='e' || c=='i' || c=='o' || c=='u') {
                sb.append('-');
            }  else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new SpellChecker().spellchecker(new String[]{"wg","uo","as","kv","ra","mw","gi","we","og","zu"},new String[]{"ov"})));
    }

}
