/*

You have an array of logs.  Each log is a space delimited string of words.

For each log, the first word in each log is an alphanumeric identifier.  Then, either:

Each word after the identifier will consist only of lowercase letters, or;
Each word after the identifier will consist only of digits.
We will call these two varieties of logs letter-logs and digit-logs.  It is guaranteed that each log has at
 least one word after its identifier.

Reorder the logs so that all of the letter-logs come before any digit-log.  The letter-logs are ordered lexicographically
 ignoring identifier, with the identifier used in case of ties.  The digit-logs should be put in their original order.

Return the final order of the logs.



Example 1:

Input: ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
Output: ["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]


Note:

0 <= logs.length <= 100
3 <= logs[i].length <= 100
logs[i] is guaranteed to have an identifier, and a word after the identifier.

 */
package string.easy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by poorvank.b on 11/11/18.
 */
public class ReorderLogFiles {

    public String[] reorderLogFiles(String[] logs) {

        TreeMap<String,String > letterLogs = new TreeMap<>();
        List<String> digitLogs = new ArrayList<>();
        for (String log : logs) {
            int index = log.indexOf(" ");
            if(Character.isLetter(log.charAt(index+1))) {
                letterLogs.put(log.substring(index+1),log);
            } else {
                digitLogs.add(log);
            }
        }

        String[] result = new String[logs.length];
        int i=0;
        for (Map.Entry<String,String> entry : letterLogs.entrySet()) {
            result[i]=(entry.getValue());
            i++;
        }
        for (String s : digitLogs) {
            result[i]=s;
            i++;
        }

        return result;

    }

}
