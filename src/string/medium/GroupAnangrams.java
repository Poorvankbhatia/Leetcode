/*

Given an array of strings, group anagrams together.

For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
Return:

[
  ["ate", "eat","tea"],
  ["nat","tan"],
  ["bat"]
]
Note: All inputs will be in lower-case.

 */
package string.medium;

import java.util.*;

/**
 * Created by poorvank on 17/10/16.
 */
public class GroupAnangrams {

    public List<List<String>> groupAnagrams(String[] strs) {

        HashMap<String,List<String>> map = new HashMap<>();

        List<List<String>> result = new ArrayList<>();

        if(strs.length==0) {
            return result;
        }

        for (String str : strs) {

            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            String s = new String(arr);
            if(map.containsKey(s)) {
                map.get(s).add(str);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(s,list);
            }

        }


        for (Map.Entry<String,List<String>> entry: map.entrySet()) {
            result.add(entry.getValue());
        }

        return result;

    }

}


