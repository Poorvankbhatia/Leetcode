/*

There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

For example:

2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.

2, [[1,0],[0,1]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.



 */
package graph.medium;

import java.util.*;

/**
 * Created by poorvank.b on 11/03/17.
 */
public class CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        List[] graph = new ArrayList[numCourses];
        for(int i=0;i<numCourses;i++)
            graph[i] = new ArrayList<>();

        boolean[] visited = new boolean[numCourses];
        for (int[] prerequisite : prerequisites) {
            graph[prerequisite[1]].add(prerequisite[0]);
        }

        for(int i=0; i<numCourses; i++){
            if(isCyclic(graph,visited,i))
                return false;
        }

        return true;

    }

    private boolean isCyclic(List[] graph,boolean[] visited,int v) {
        if(visited[v]) {
            return true;
        }

        visited[v] = true;


        for(int i=0; i<graph[v].size();i++){
            if(isCyclic(graph,visited,(int)graph[v].get(i)))
                return true;
        }

        visited[v] = false;
        return false;

    }

    public static void main(String[] args) {
        int[][] pre = {{0,1},{1,0}};
        int n = 2;
        System.out.println(new CourseSchedule().canFinish(n,pre));
    }

}

/*

TOPOLOGICAL SORT

public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
        int[] indegree = new int[numCourses];
        Queue<Integer> queue = new LinkedList<Integer>();
        int count = numCourses;
        for (int i = 0; i < numCourses; i++) {
            map.put(i, new ArrayList<Integer>());
        }
        for (int i = 0; i < prerequisites.length; i++) {
            map.get(prerequisites[i][0]).add(prerequisites[i][1]);
            indegree[prerequisites[i][1]]++;
        }
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int i : map.get(current)) {
                if (--indegree[i] == 0) {
                    queue.offer(i);
                }
            }
            count--;
        }
        return count == 0;

        ex:
                2
                |
                |
               \/
            0-->1<--3

            indegree[0,3,0,0]
    }

 */