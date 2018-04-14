/*

Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent
different tasks.Tasks could be done without original order. Each task could be done in one interval. For each interval,
CPU could finish one task or just be idle.

However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that
CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU will take to finish all the given tasks.

Example 1:
Input: tasks = ['A','A','A','B','B','B'], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
Note:
The number of tasks is in the range [1, 10000].
The integer n is in the range [0, 100].

 */
package heap.medium;

import heap.MaxPriorityQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 23/06/17.
 */
public class TaskScheduler {

    private class TaskObject implements Comparable<TaskObject> {
        int frequency;
        char value;

        @Override
        public int compareTo(TaskObject o) {

            if(this.frequency!=o.frequency) {
                return this.frequency-o.frequency;
            } else {
                return this.value-o.value;
            }

        }

    }

    public int leastInterval(char[] tasks, int n) {

        TaskObject[] count = new TaskObject[26];

        MaxPriorityQueue<TaskObject> maxPriorityQueue = new MaxPriorityQueue<>();

        for (char c : tasks) {
            if(count[c-'A']==null) {
                count[c-'A'] = new TaskObject();
                count[c-'A'].frequency = 1;
                count[c-'A'].value = c;
            } else {
                count[c-'A'].frequency = count[c-'A'].frequency+1;
            }
        }

        for (TaskObject taskObject : count) {
            if(taskObject!=null && taskObject.frequency>0) {
                maxPriorityQueue.insert(taskObject);
            }
        }

        int result =0;

        while (!maxPriorityQueue.isEmpty()) {

            int k =n+1;// Number of steps before u encounter the same character again.
            List<TaskObject> list = new ArrayList<>();
            while (k>0 && !maxPriorityQueue.isEmpty()) {
                TaskObject taskObject = maxPriorityQueue.deleteMax();
                taskObject.frequency -= 1;
                // Since we don't need this object again in this iteration
                list.add(taskObject);
                k--;
                result++;
            }

            for (TaskObject taskObject : list) {
                if(taskObject.frequency>0) {
                    maxPriorityQueue.insert(taskObject);
                }
            }

            if(maxPriorityQueue.isEmpty()) {
                break;
            }

            result+=k; // if k > 0, then it means we need to be idle


        }

        return result;

    }

    public static void main(String[] args) {
        char[] arr = new char[]{'A','A','B','B'};
        int n =2;
        System.out.print(new TaskScheduler().leastInterval(arr,n));
    }


}

/*

Add them to a priority Queue and sort based on the highest frequency.
And pick the task in each round of 'n' with highest frequency. As you pick the task, decrease the frequency, and put them back after the round.

Frequency sort using java's queue:

PriorityQueue<Map.Entry<Character, Integer>> q = new PriorityQueue<>( //frequency sort
            (a,b) -> a.getValue() != b.getValue() ? b.getValue() - a.getValue() : a.getKey() - b.getKey());


Entire Soln :
 public int leastInterval(char[] tasks, int n) {

        Map<Character,Integer> map = new HashMap<>();

        for(char c : tasks) {
            map.put(c,map.getOrDefault(c,0)+1);
        }

        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>(
            (a,b) -> a.getValue() != b.getValue() ? b.getValue() - a.getValue() : a.getKey() - b.getKey());

        for(Map.Entry<Character,Integer> entry  : map.entrySet()) {
            pq.offer(entry);
        }

        int result=0;
        while(!pq.isEmpty()) {
            int k=n+1;
            List<Map.Entry<Character,Integer>> list = new ArrayList<>();
            while(k>0 && !pq.isEmpty()) {

                Map.Entry<Character,Integer> entry = pq.poll();
                entry.setValue(entry.getValue()-1);
                list.add(entry);
                k--;
                result++;

            }

            for(Map.Entry<Character,Integer> entry : list) {
                if(entry.getValue()>0) {
                    pq.add(entry);
                }
            }

            if(pq.isEmpty()) {
                break;
            }

            result+=k;

        }

        return result;

    }


 */