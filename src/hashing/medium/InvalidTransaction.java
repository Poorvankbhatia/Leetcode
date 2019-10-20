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

    public class Transaction {
        String name;
        int time;
        String city;
        String trans;

        Transaction(String name, int time, String city, String trans) {
            this.name = name;
            this.time = time;
            this.city = city;
            this.trans = trans;
        }
    }

    public List<String> invalidTransactions(String[] transactions) {
        Set<String> out = new HashSet<>();

        Map<String, List<Transaction>> perPerson = new HashMap<>();

        for(String trans : transactions) {
            String[] split = trans.split(",");

            String name = split[0];
            int time = Integer.valueOf(split[1]);
            int amount = Integer.valueOf(split[2]);
            String city = split[3];

            if(amount > 1000) {
                out.add(trans);
            }

            List<Transaction> otherTransactions = perPerson.get(name);

            if(otherTransactions == null) {
                otherTransactions = new ArrayList<>();
                otherTransactions.add(new Transaction(name, time, city, trans));
                perPerson.put(name, otherTransactions);
            } else {
                for(Transaction transa : otherTransactions) {
                    if(!transa.city.equals(city) && Math.abs(transa.time - time) <= 60) {
                        out.add(transa.trans);
                        out.add(trans);
                    }
                }

                otherTransactions.add(new Transaction(name, time, city, trans));
            }
        }

        return new ArrayList<>(out);
    }

}
