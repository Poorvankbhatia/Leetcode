/*

A tree rooted at node 0 is given as follows:

The number of nodes is nodes;
The value of the i-th node is value[i];
The parent of the i-th node is parent[i].
Remove every subtree whose sum of values of nodes is zero.

After doing so, return the number of nodes remaining in the tree.


Input: nodes = 7, parent = [-1,0,0,1,2,2,2], value = [1,-2,4,0,-2,-1,-1]
Output: 2


Constraints:

1 <= nodes <= 10^4
-10^5 <= value[i] <= 10^5
parent.length == nodes
parent[0] == -1 which indicates that 0 is the root.

 */

package tree.medium;

import java.util.*;

public class DeleteTreeNodes {

    Map<Integer, List<Integer>> parentChildMap;
    Set<Integer> zero = new HashSet<>();
    public int deleteTreeNodes(int nodes, int[] parent, int[] value) {
        parentChildMap = new HashMap<>();
        for(int i=0;i<nodes;i++) {
            parentChildMap.computeIfAbsent(parent[i], k -> new ArrayList<>());
            parentChildMap.get(parent[i]).add(i);
        }

        int sum = dfs(0,value);
        if(sum==0) {
            return 0;
        } else {
            int v=0;
            for (int x : zero) {
                v+=getSubTreeSize(x);
            }
            return nodes-v;
        }

    }

    private int getSubTreeSize(int x) {
        int size=1;
        if(parentChildMap.containsKey(x)) {
            for (int y : parentChildMap.get(x)) {
                if(!zero.contains(y)) {
                    size += getSubTreeSize(y);
                }
            }
            return size;
        }
        return size;
    }

    private int dfs(int start,int[] value) {
        int sum=value[start];
        if(parentChildMap.containsKey(start)) {
            for(int x : parentChildMap.get(start)) {
                sum+=dfs(x,value);
            }
        }
        if(sum==0) {
            zero.add(start);
        }
        return sum;
    }

}

/*

Better Sol:

for(int i=nodes-1;i>0;i--){
            value[parent[i]] += value[i];
        }
        HashSet<Integer> hs = new HashSet();
        int count = 0;
        for(int i=0;i<nodes;i++){
            if(hs.contains(parent[i])){
                value[i] = 0;
            }
            if(value[i] == 0){
                hs.add(i);
                count ++;
            }
        }
        return nodes-count;

 */