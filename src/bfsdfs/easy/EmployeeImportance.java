/*

You are given a data structure of employee information, which includes the employee's unique id, his importance value and his direct subordinates' id.

For example, employee 1 is the leader of employee 2, and employee 2 is the leader of employee 3. They have importance value 15, 10 and 5,
respectively. Then employee 1 has a data structure like [1, 15, [2]], and employee 2 has [2, 10, [3]], and employee 3 has [3, 5, []].
Note that although employee 3 is also a subordinate of employee 1, the relationship is not direct.

Now given the employee information of a company, and an employee id, you need to return the total importance value of this employee and all his subordinates.

Example 1:

Input: [[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1
Output: 11
Explanation:
Employee 1 has importance value 5, and he has two direct subordinates: employee 2 and employee 3. They both have

 */
package bfsdfs.easy;

import java.util.*;

public class EmployeeImportance {

    private class Employee {
        // It's the unique id of each node;
        // unique id of this employee
        public int id;
        // the importance value of this employee
        public int importance;
        // the id of direct subordinates
        public List<Integer> subordinates;
    };

    public int getImportance(List<Employee> employees, int id) {
        Map<Integer,Integer> importanceMap = new HashMap<>();
        Map<Integer, List<Integer>> subordinateMap = new HashMap<>();
        for(Employee e: employees) {
            if(!subordinateMap.containsKey(e.id)) {
                subordinateMap.put(e.id,new ArrayList<>());
            }
            importanceMap.put(e.id,e.importance);
            subordinateMap.get(e.id).addAll(e.subordinates);
        }
        int sum=0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(id);
        while(!queue.isEmpty()) {
            int poll = queue.poll();
            sum+=importanceMap.get(poll);
            queue.addAll(subordinateMap.get(poll));
        }
        return sum;
    }

}
