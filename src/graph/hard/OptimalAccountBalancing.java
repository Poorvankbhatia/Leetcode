/*

A group of friends went on holiday and sometimes lent each other money. For example, Alice paid for Bill's lunch for $10.
Then later Chris gave Alice $5 for a taxi ride. We can model each transaction as a tuple (x, y, z) which means person x gave person y $z.
Assuming Alice, Bill, and Chris are person 0, 1, and 2 respectively (0, 1, 2 are the person's ID), the transactions can be represented as [[0, 1, 10], [2, 0, 5]].

Given a list of transactions between a group of people, return the minimum number of transactions required to settle the debt.

Note:

A transaction will be given as a tuple (x, y, z). Note that x ≠ y and z > 0.
Person's IDs may not be linear, e.g. we could have the persons 0, 1, 2 or we could also have the persons 0, 2, 6.
Example 1:

Input:
[[0,1,10], [2,0,5]]

Output:
2

Explanation:
Person #0 gave person #1 $10.
Person #2 gave person #0 $5.

Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.
Example 2:

Input:
[[0,1,10], [1,0,1], [1,2,5], [2,0,5]]

Output:
1

Explanation:
Person #0 gave person #1 $10.
Person #1 gave person #0 $1.
Person #1 gave person #2 $5.
Person #2 gave person #0 $5.

Therefore, person #1 only need to give person #0 $4, and all debt is settled.


 */
package graph.hard;

import java.util.*;

public class OptimalAccountBalancing {

    static int min = Integer.MAX_VALUE;
    public int minTransfers(int[][] transactions) {
        min=Integer.MAX_VALUE;
        HashMap<Integer,Integer> profitMap = new HashMap<>();
        for(int[] trans : transactions) {
            int a = trans[2];
            profitMap.put(trans[0],profitMap.getOrDefault(trans[0],0)+a);
            profitMap.put(trans[1],profitMap.getOrDefault(trans[1],0)-a);
        }
        LinkedList<Integer> positive = new  LinkedList<>();
        LinkedList<Integer> negative = new  LinkedList<>();
        for(Integer key : profitMap.keySet()){
            Integer val = profitMap.get(key);
            if(val > 0){
                positive.add(val);
            }else if(val < 0){
                negative.add(val);
            }
        }
        dfs(positive,negative,0);
        return min;
    }

    public void dfs(List<Integer> positive,List<Integer> negative,int count){
        if(positive.size() == 0 && negative.size() == 0){
            min = Math.min(count,min);
            return;
        }
        if(count >= min){
            return;
        }
        int positiveVal = positive.get(0);

        // We start will different negative values and use
        for(int j=0;j<negative.size();j++){
            int negativeVal = negative.get(j);
            // Deduct the balance. If the new values become zero then we remove those values from the list.
            int newPositiveVal = Math.max(positiveVal+negativeVal,0);
            int newNegativeVal = Math.min(0,positiveVal+negativeVal);
            if(newPositiveVal == 0){
                positive.remove(0);
            }else{
                positive.set(0,newPositiveVal);
            }
            if(newNegativeVal == 0){
                negative.remove(j);
            }else{
                negative.set(j,newNegativeVal);
            }

            dfs(positive,negative,count+1);

            // Backtrack, we need to add back the values.
            if(newPositiveVal == 0){
                positive.add(0,positiveVal);
            }else{
                positive.set(0,positiveVal);
            }
            if(newNegativeVal == 0){
                negative.add(j,negativeVal);
            }else{
                negative.set(j,negativeVal);
            }
        }
    }

    public static void main(String[] args) {
        int[][] a = new int[][]{
                {1,0,5},
                {1,2,20},
                {3,4,2},
                {3,5,1}
        };
        System.out.println(new OptimalAccountBalancing().minTransfers(a));
    }
}

/*

We go through every transaction and update the values for each person.

If a person has given money to some one else, he gets back that much money. So we add that money to his account.
If a person owes money, then we deduct the money from his account.
We will be left with three kinds of people

People who will get money back (Positive)
People who owe money. (Negative)
People whoe neither get back or owe money. We will ignore these people as they are settled.
Now we need to match the positive amounts with negative amounts. Once all the positive and negatives cancel out, we check the transactions required and we take the minimum.

We do not know the order in which these balances are matched which will yeild minimum transactions. So we need to try out all the combinations. We use backtracking/dfs.
We will discard the dfs where the transactions count is greater than the minimum. (Pruning)

 */