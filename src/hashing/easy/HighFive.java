/*

Given a list of scores of different students, return the average score of each student's top five scores in the order of each student's id.

Each entry items[i] has items[i][0] the student's id, and items[i][1] the student's score.  The average score is calculated using integer division.



Example 1:

Input: [[1,91],[1,92],[2,93],[2,97],[1,60],[2,77],[1,65],[1,87],[1,100],[2,100],[2,76]]
Output: [[1,87],[2,88]]
Explanation:
The average of the student with id = 1 is 87.
The average of the student with id = 2 is 88.6. But with integer division their average converts to 88.


Note:

1 <= items.length <= 1000
items[i].length == 2
The IDs of the students is between 1 to 1000
The score of the students is between 1 to 100
For each student, there are at least 5 scores

 */
package hashing.easy;

import java.util.PriorityQueue;
import java.util.TreeMap;

public class HighFive {

    public int[][] highFive(int[][] items) {
        TreeMap<Integer, PriorityQueue<Integer>> map = new TreeMap<>();

        for(int[] item : items){
            int id = item[0];
            int score = item[1];

            if(!map.containsKey(id)){
                PriorityQueue<Integer> pq = new PriorityQueue<>(5);
                pq.offer(score);
                map.put(id, pq);
            }else{
                PriorityQueue<Integer> pq = map.get(id);
                pq.offer(score);
                if(pq.size() > 5){
                    pq.poll();
                }
                map.put(id, pq);
            }
        }

        int index = 0;

        int[][] res = new int[map.size()][2];

        for(int id : map.keySet()){

            res[index][0] = id;

            PriorityQueue<Integer> pq = map.get(id);
            int sum = 0;
            int size = pq.size();

            while(!pq.isEmpty()){
                sum+= pq.poll();
            }

            res[index][1] = sum/size;

            index++;

        }


        return res;

    }

}
