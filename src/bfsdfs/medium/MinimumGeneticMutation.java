/*

A gene string can be represented by an 8-character long string, with choices from "A", "C", "G", "T".

Suppose we need to investigate about a mutation (mutation from "start" to "end"), where ONE mutation is defined as ONE single character changed
in the gene string.

For example, "AACCGGTT" -> "AACCGGTA" is 1 mutation.

Also, there is a given gene "bank", which records all the valid gene mutations. A gene must be in the bank to make it a valid gene string.

Now, given 3 things - start, end, bank, your task is to determine what is the minimum number of mutations needed to mutate from "start" to "end".
If there is no such a mutation, return -1.

Note:

Starting point is assumed to be valid, so it might not be included in the bank.
If multiple mutations are needed, all mutations during in the sequence must be valid.
You may assume start and end string is not the same.
Example 1:

start: "AACCGGTT"
end:   "AACCGGTA"
bank: ["AACCGGTA"]

return: 1
Example 2:

start: "AACCGGTT"
end:   "AAACGGTA"
bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]

return: 2
Example 3:

start: "AAAAACCC"
end:   "AACCCCCC"
bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]

return: 3

 */

package bfsdfs.medium;

import java.util.*;

/**
 * Created by poorvank.b on 09/03/17.
 */
public class MinimumGeneticMutation {

    private class QueueNode {

        private String mutation;
        private int jumps;

        QueueNode(String mutation, int jumps) {
            this.mutation = mutation;
            this.jumps = jumps;
        }
    }

    public int minMutation(String start, String end, String[] bank) {

        if(bank==null|| bank.length==0) {
            return -1;
        }


        int result = -1;

        if(start.equals(end)) {
            return 0;
        }

        Set<String> set = new HashSet<>();

        Collections.addAll(set, bank);

        set.add(start);

        Map<String,List<String>> map = new HashMap<>();

        for (String mutation : set) {

            for (int i=0;i<mutation.length();i++) {

                char[] arr = mutation.toCharArray();

                for (char c='A';c<='Z';c++) {

                    char temp = arr[i];
                    arr[i] = c;

                    String newMutation = new String(arr);

                    if(!newMutation.equals(mutation) && set.contains(newMutation)) {
                        List<String> list;
                        if(map.containsKey(mutation)) {
                            map.get(mutation).add(newMutation);
                        } else {
                            list = new ArrayList<>();
                            list.add(newMutation);
                            map.put(mutation,list);
                        }
                    }

                    arr[i] = temp;

                }

            }

        }

        Queue<QueueNode> queue = new LinkedList<>();

        queue.add(new QueueNode(start,0));

        while (!queue.isEmpty()) {

            QueueNode currentMutation = queue.poll();

            if(currentMutation.mutation.equals(end)) {
                result = currentMutation.jumps;
                break;
            }

            List<String> nextMutations = map.get(currentMutation.mutation);

            if(nextMutations!=null){
                for (String mutation : nextMutations) {
                    queue.add(new QueueNode(mutation,currentMutation.jumps+1));
                }
            }

            map.remove(currentMutation.mutation);
        }

        return result;

    }

    public static void main(String[] args) {
        String[] bank = new String[]{"AAAAAAAA","AAAAAAAC","AAAAAACC","AAAAACCC","AAAACCCC","AACACCCC","ACCACCCC","ACCCCCCC","CCCCCCCA"};
        String start = "AAAAAAAA";
        String end = "CCCCCCCC";

        System.out.println(new MinimumGeneticMutation().minMutation(start,end,bank));

    }

}

/*

Same as word ladder.

 */