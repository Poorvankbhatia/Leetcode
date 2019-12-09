/*

Given a non-empty array of integers, return the k most frequent elements.

For example,
Given [1,1,1,2,2,3] and k = 2, return [1,2].

Note:
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

 */
package heap.medium;

import heap.MinPriorityQueue;

import java.util.*;

/**
 * Created by poorvank on 05/09/16.
 */
public class KFrequent {

    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for(int n : nums) {
            if(!map.containsKey(n)) {
                list.add(n);
            }
            map.put(n,map.getOrDefault(n,0)+1);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        for(int i=0;i<k;i++) {
            pq.add(new int[]{list.get(i),map.get(list.get(i))});
        }
        for(int i=k;i<list.size();i++) {
            if(map.get(list.get(i))>pq.peek()[1]) {
                pq.poll();
                pq.add(new int[]{list.get(i),map.get(list.get(i))});
            }
        }
        List<Integer> result = new ArrayList<>();
        while(!pq.isEmpty()) {
            result.add(pq.poll()[0]);
        }
        return result;
    }

    public static void main(String[] args) {
        KFrequent kf = new KFrequent();
        int[] nums = new int[]{5,2,5,3,5,3,1,1,3};
        System.out.println(kf.topKFrequent(nums,2));
    }

}

/*

O(N) sol:

Bucket sort.

public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer>[] bucket = new List[nums.length + 1];
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        for (int n : nums) {
            frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
        }

        for (Map.Entry<Integer,Integer> entry : frequencyMap.entrySet()) {
            int frequency = entry.getValue();
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(entry.getKey());
        }

        List<Integer> res = new ArrayList<>();

        for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
            if (bucket[pos] != null) {
                res.addAll(bucket[pos]);
            }
        }
        return res;
    }

 */