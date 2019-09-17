/*

There are N courses, labelled from 1 to N.

We are given relations[i] = [X, Y], representing a prerequisite relationship between course X and course Y: course X has to be studied before course Y.

In one semester you can study any number of courses as long as you have studied all the prerequisites for the course you are studying.

Return the minimum number of semesters needed to study all courses.  If there is no way to study all the courses, return -1.


Input: N = 3, relations = [[1,3],[2,3]]
Output: 2
Explanation:
In the first semester, courses 1 and 2 are studied. In the second semester, course 3 is studied.


Input: N = 3, relations = [[1,2],[2,3],[3,1]]
Output: -1
Explanation:
No course can be studied because they depend on each other.

 */
package graph.hard;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ParallelCourses {

    public int minimumSemesters(int N, int[][] relations) {

        int[] inDegree = new int[N+1];

        List<List<Integer>> lists = new ArrayList<>();

        for (int i=0;i<=N;i++) {
            lists.add(i,new ArrayList<>());
        }

        for (int[] relation : relations) {
            lists.get(relation[0]).add(relation[1]);
            inDegree[relation[1]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i=1;i<=N;i++) {
            if(inDegree[i]==0) {
                queue.add(i);
            }
        }

        int ans = 0;
        int completion = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            completion+=size;
            while (size>0) {
                Integer poll = queue.poll();
                if(poll!=null) {
                    for (int dep : lists.get(poll)) {
                        if (--inDegree[dep] == 0) {
                            queue.add(dep);
                        }
                    }
                }
                size--;
            }
            ans++;
        }


        return completion!=N?-1:ans;
    }

    public static void main(String[] args) {
        int[][] relations = new int[][]{
                {1, 3},
                {2, 3},
                {3, 4},
                {4, 1},
        };

        System.out.println(new ParallelCourses().minimumSemesters(7,relations));

    }

}

/*

Simple Topological Sort.

O(V+E)

 */