/*

A transaction is possibly invalid if:

the amount exceeds $1000, or;
if it occurs within (and including) 60 minutes of another transaction with the same name in a different city.
Each transaction string transactions[i] consists of comma separated values representing the name, time (in minutes), amount, and city of the transaction.

Given a list of transactions, return a list of transactions that are possibly invalid.  You may return the answer in any order.



Example 1:

Input: transactions = ["alice,20,800,mtv","alice,50,100,beijing"]
Output: ["alice,20,800,mtv","alice,50,100,beijing"]
Explanation: The first transaction is invalid because the second transaction occurs within a difference of 60 minutes,
have the same name and is in a different city. Similarly the second one is invalid too.
Example 2:

Input: transactions = ["alice,20,800,mtv","alice,50,1200,mtv"]
Output: ["alice,50,1200,mtv"]
Example 3:

Input: transactions = ["alice,20,800,mtv","bob,50,1200,mtv"]
Output: ["bob,50,1200,mtv"]


Constraints:

transactions.length <= 1000
Each transactions[i] takes the form "{name},{time},{amount},{city}"
Each {name} and {city} consist of lowercase English letters, and have lengths between 1 and 10.
Each {time} consist of digits, and represent an integer between 0 and 1000.
Each {amount} consist of digits, and represent an integer between 0 and 2000.

 */
package hashing.medium;

import java.util.*;

public class InvalidTransaction {

    public List<String> invalidTransactions(String[] trans) {
        int n = trans.length;
        Set<String> res = new HashSet<>();
        Map<String, Map<Integer, Map<String, String>>> map = new HashMap<>();
        for (String tran : trans) {
            String[] mm = tran.split(",");
            int curTime = Integer.parseInt(mm[1]);
            if (Integer.parseInt(mm[2]) > 1000) {
                res.add(tran);
            }

            if (map.containsKey(mm[0])) {
                for (Map.Entry<Integer, Map<String, String>> e : map.get(mm[0]).entrySet()) {
                    int lastTime = e.getKey();
                    if (Math.abs(lastTime - curTime) <= 60 && (e.getValue().size() > 1 ||
                            !e.getValue().containsKey(mm[3]))) {
                        res.add(tran);
                        for (Map.Entry<String, String> es : e.getValue().entrySet()) {
                            if (!es.getKey().equals(mm[3])) {
                                res.add(es.getValue());
                            }
                        }
                    }
                }
            }
            map.putIfAbsent(mm[0], new TreeMap<>());
            map.get(mm[0]).putIfAbsent(curTime, new HashMap<>());
            map.get(mm[0]).get(curTime).put(mm[3], tran);
        }

        return new ArrayList<>(res);
    }

}
