/*

There are N network nodes, labelled 1 to N.

Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node,
v is the target node, and w is the time it takes for a signal to travel from source to target.

Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.

Note:
N will be in the range [1, 100].
K will be in the range [1, N].
The length of times will be in the range [1, 6000].
All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 1 <= w <= 100.

 */
package bfsdfs.medium;

import java.util.*;

/**
 * Created by poorvank.b on 16/12/17.
 */
public class NetworkDelayTime {

    public int networkDelayTime(int[][] times, int N, int K) {

        if(times==null || times.length==0) {
            return -1;
        }

        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for(int[] time : times){
            Map<Integer, Integer> sourceMap = map.get(time[0]);
            if(sourceMap == null){
                sourceMap = new HashMap<>();
                map.put(time[0], sourceMap);
            }
            Integer dis = sourceMap.get(time[1]);
            if(dis == null || dis > time[2]){
                sourceMap.put(time[1], time[2]);
            }

        }

        Map<Integer,Integer> distanceMap = new HashMap<>();
        distanceMap.put(K,0);
        PriorityQueue<int[]> pq = new PriorityQueue<>((i1, i2) -> {return i1[1] - i2[1];});
        pq.offer(new int[]{K, 0});
        int max = -1;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int node = cur[0];
            int distance = cur[1];

            Map<Integer, Integer> sourceMap = map.get(node);
            if(sourceMap == null){
                continue;
            }
            for(Map.Entry<Integer, Integer> entry : sourceMap.entrySet()){
                int absoluteDistance = distance + entry.getValue();
                int targetNode = entry.getKey();
                if(distanceMap.containsKey(targetNode) && distanceMap.get(targetNode) <= absoluteDistance){
                    continue;
                }
                distanceMap.put(targetNode, absoluteDistance);
                pq.offer(new int[]{targetNode, absoluteDistance});
            }
        }

        for(int val : distanceMap.values()){
            if(val > max){
                max = val;
            }
        }
        return distanceMap.size() == N ? max : -1;

    }


    public static void main(String[] args) {
        int[][] arr = new int[][]{{1,3,4},{1,2,1},{2,3,2}};
        System.out.println(new NetworkDelayTime().networkDelayTime(arr,3,1));
    }

}

/*

It is a direct graph.

Use Map<Integer, Map<Integer, Integer>> to store the source node, target node and the distance between them.
Offer the node K to a PriorityQueue.
Then keep getting the closest nodes to the current node and calculate the distance from the source (K)
to this node (absolute distance). Use a Map to store the shortest absolute distance of each node.
Return the node with the largest absolute distance.

 */