/*
Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd".
 We can keep "shifting" which forms the sequence: "abc" -> "bcd" -> ... -> "xyz".

Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence, return:

[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]
 */
package string.medium;

import java.util.*;

/**
 * Created by poorvank on 07/09/17.
 */
public class GroupShiftedStrings {

    public List<List<String>> groupStrings(String[] strings) {

        List<List<String>> result = new ArrayList<>();

        Map<String,List<String>> map = new HashMap<>();
        for (int i=0;i<strings.length;i++) {
            String h = hash(strings[i]);
            if(map.containsKey(h)) {
                List<String> list = map.get(h);
                list.add(strings[i]);
                map.put(h,list);
            } else {
                List<String> list = new ArrayList<>();
                list.add(strings[i]);
                map.put(h,list);
            }
        }

        for(Map.Entry<String, List<String>> entry: map.entrySet()){
            Collections.sort(entry.getValue());
        }

        result.addAll(map.values());

        return result;
    }

    private String hash(String s) {
        if(s==null || s.length()==0) {
            return "";
        }
        int count = 26;
        StringBuilder hash=new StringBuilder();
        for (int i=1;i<s.length();i++) {
            int diff = s.charAt(i)-s.charAt(i-1);
            if(diff<0) {
                diff+=count;
            }
            hash.append(diff);
        }
        return hash.toString();
    }

    public static void main(String[] args) {
        String[] arr = new String[]{"a","abc","acef","bcd","az","ba","xyz","z"};
        System.out.println(new GroupShiftedStrings().groupStrings(arr));
    }

}


/*

Input  : str[] = {"acd", "dfg", "wyz", "yab", "mop",
                 "bdfh", "a", "x", "moqs"};

Output : a x
         acd dfg wyz yab mop
         bdfh moqs
All shifted strings are grouped together.

We can see a pattern among string of one group, the difference between consecutive characters for all character of string are equal.
As in above example take acd, dfg and mop

a c d -> 2 1
d f g -> 2 1
m o p -> 2 1

Since the differences are same, we can use this to identify strings that belong to same group.
The idea is to form a string of differences as key. If a string with same difference string is found,
then this string also belongs to same group. For example, above three strings have same difference string, that is “21”.

In below implementation, we add ‘a’ to every difference and store 21 as “ba”.
 */