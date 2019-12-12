/*

There are n people whose IDs go from 0 to n - 1 and each person belongs exactly to one group. Given the array groupSizes of length n
telling the group size each person belongs to, return the groups there are and the people's IDs each group includes.

You can return any solution in any order and the same applies for IDs. Also, it is guaranteed that there exists at least one solution.



Example 1:

Input: groupSizes = [3,3,3,3,3,1,3]
Output: [[5],[0,1,2],[3,4,6]]
Explanation:
Other possible solutions are [[2,1,6],[5],[0,4,3]] and [[5],[0,6,2],[4,3,1]].
Example 2:

Input: groupSizes = [2,1,3,3,3,2]
Output: [[1],[0,5],[2,3,4]]


Constraints:

groupSizes.length == n
1 <= n <= 500
1 <= groupSizes[i] <= n

 */
package greedy.medium;

import java.util.ArrayList;
import java.util.List;

public class GroupThePeople {

    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        List<List<Integer>> result = new ArrayList<>();
        int n = groupSizes.length;

        List<Integer>[] arr = new List[n+1];
        for(int i=0;i<n+1;i++) {
            arr[i]=new ArrayList<>();
        }
        for(int i=0;i<n;i++) {
            arr[groupSizes[i]].add(i);
        }
        for(int i=1;i<n+1;i++) {
            if(arr[i].size()>=i) {
                for(int k=0;k<arr[i].size();k+=i) {
                    List<Integer> list = (arr[i].subList(k,k+i));
                    result.add(list);
                }
            }
        }
        return result;
    }

}

/*

Map Implementation:

public List<List<Integer>> groupThePeople(int[] groupSizes) {
        Map<Integer,List<Integer>> map = new HashMap<>();
        for(int i=0;i<groupSizes.length;i++) {
            if(!map.containsKey(groupSizes[i])) {
                map.put(groupSizes[i],new ArrayList<>());
            }
            map.get(groupSizes[i]).add(i);
        }

        List<List<Integer>> result = new ArrayList<>();
        for(Map.Entry<Integer,List<Integer>> entry : map.entrySet()) {
            int start=0;
            int end=entry.getKey();
            while(entry.getValue().size()>=end) {
                List<Integer> list = entry.getValue().subList(start,end);
                result.add(list);
                start = end;
                end +=entry.getKey();
            }
        }
        return result;
    }

 */