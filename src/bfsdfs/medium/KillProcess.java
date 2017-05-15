/*

Given n processes, each process has a unique PID (process id) and its PPID (parent process id).

Each process only has one parent process, but may have one or more children processes. This is just like a tree structure. Only one
process has PPID that is 0, which means this process has no parent process. All the PIDs will be distinct positive integers.

We use two list of integers to represent a list of processes, where the first list contains PID for each process and the second list
contains the corresponding PPID.

Now given the two lists, and a PID representing a process you want to kill, return a list of PIDs of processes that will be killed in the end.
You should assume that when a process is killed, all its children processes will be killed. No order is required for the final answer.

Example 1:
Input:
pid =  [1, 3, 10, 5]
ppid = [3, 0, 5, 3]
kill = 5
Output: [5,10]
Explanation:
           3
         /   \
        1     5
             /
            10
Kill 5 will also kill 10.

 */
package bfsdfs.medium;

import java.util.*;

/**
 * Created by poorvank on 14/05/17.
 */
public class KillProcess {

    List<Integer> result = new ArrayList<>();
    Map<Integer,List<Integer>> map = new HashMap<>();

    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {

        int n = pid.size();

        for (int i=0;i<n;i++) {
            List<Integer> list = map.get(ppid.get(i));
            if(list==null) {
                list = new ArrayList<>();
                list.add(pid.get(i));
                map.put(ppid.get(i),list);
            } else {
                list.add(pid.get(i));
                map.put(ppid.get(i),list);
            }
        }

        System.out.println(map.toString());

        HashSet<Integer> set  = new HashSet<>();

        dfs(set,kill);

        return result;
    }

    private void dfs(HashSet<Integer> set,int kill) {


        while (!set.contains(kill)) {
            set.add(kill);
            result.add(kill);
            for (Integer next : map.get(kill)) {
                dfs(set,next);
            }
        }

    }

}
