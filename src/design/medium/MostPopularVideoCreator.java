/*
You are given two string arrays creators and ids, and an integer array views, all of length n.
The ith video on a platform was created by creator[i], has an id of ids[i], and has views[i] views.

The popularity of a creator is the sum of the number of views on all of the creator's videos.
Find the creator with the highest popularity and the id of their most viewed video.

If multiple creators have the highest popularity, find all of them.
If multiple videos have the highest view count for a creator, find the lexicographically smallest id.
Return a 2D array of strings answer where answer[i] = [creatori, idi] means that creatori has the
highest popularity and idi is the id of their most popular video. The answer can be returned in any order.



Example 1:

Input: creators = ["alice","bob","alice","chris"], ids = ["one","two","three","four"], views = [5,10,5,4]
Output: [["alice","one"],["bob","two"]]
Explanation:
The popularity of alice is 5 + 5 = 10.
The popularity of bob is 10.
The popularity of chris is 4.
alice and bob are the most popular creators.
For bob, the video with the highest view count is "two".
For alice, the videos with the highest view count are "one" and "three". Since "one" is lexicographically
smaller than "three", it is included in the answer.
Example 2:

Input: creators = ["alice","alice","alice"], ids = ["a","b","c"], views = [1,2,2]
Output: [["alice","b"]]
Explanation:
The videos with id "b" and "c" have the highest view count.
Since "b" is lexicographically smaller than "c", it is included in the answer.


Constraints:

n == creators.length == ids.length == views.length
1 <= n <= 105
1 <= creators[i].length, ids[i].length <= 5
creators[i] and ids[i] consist only of lowercase English letters.
0 <= views[i] <= 105
 */
package design.medium;

import java.util.*;

public class MostPopularVideoCreator {

    private class IdView {
        private String id;
        private int view;

        public IdView(String id, int view) {
            this.id = id;
            this.view = view;
        }
    }

    public List<List<String>> mostPopularCreator(String[] creators, String[] ids, int[] views) {

        int n = creators.length;
        // stores creator to ordered IDView's
        // a->(id1,3),(id2,1);
        Map<String, PriorityQueue<IdView>> creatorScoreMap = new HashMap<>();
        // Stores total sum for a creator.
        // a->4
        Map<String,Integer> creatorViewSumMap = new HashMap<>();
        // Stores creators corresponding to a given sum
        // 10->(a),(b)..
        TreeMap<Integer,TreeSet<String>> viewCreatorMap = new TreeMap<>();
        List<List<String>> result = new ArrayList<>();

        for (int i=0;i<n;i++) {
            String creator = creators[i];
            String id = ids[i];
            int view = views[i];
            creatorScoreMap.putIfAbsent(creator,new PriorityQueue<>((a,b)->(b.view!=a.view)?b.view-a.view:a.id.compareTo(b.id)));
            creatorScoreMap.get(creator).add(new IdView(id,view));
            int val = view;
            // check if the creator is coming up again.
            if(creatorViewSumMap.containsKey(creator)) {
                // get the current stored sum for the creator.
                int sum = creatorViewSumMap.get(creator);
                // update the sum by addung the current view.
                creatorViewSumMap.put(creator,(sum+view));
                // get the set for the last sum.
                // storing -ve so that, we don't need a comparator for tree set.
                TreeSet<String> set = viewCreatorMap.getOrDefault(-sum,new TreeSet<>());
                // remove the creator from the last sum in the viewCreatorMap.
                // previous sum 10->(A),(B)
                // Now view sum of A = 15,
                // Remove A from 10 and add it to 15.
                set.remove(creator);
                // if set is empty remove it from the viewCreatorMap.
                if(set.isEmpty()) {
                    viewCreatorMap.remove(-sum);
                }
                // add the last sum to the current view.
                val +=sum;
            } else {
                // check if the creator is coming for the first time..
                creatorViewSumMap.put(creator,view);
            }
            // get the new treeset.
            TreeSet<String> set = viewCreatorMap.getOrDefault(-val,new TreeSet<>());
            // add creator
            set.add(creator);
            // update map.
            viewCreatorMap.put(-val,set);
        }

        TreeSet<String> topViews = viewCreatorMap.firstEntry().getValue();
        for (String view : topViews) {
            List<String> r = new ArrayList<>();
            r.add(view);
            // get the top ids with the highest views.
            r.add(creatorScoreMap.get(view).poll().id);
            result.add(r);
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(new MostPopularVideoCreator().mostPopularCreator(new String[]{"alice","alice","alice"},
                new String[]{"a","b","c"},new int[]{1,2,2}));
    }

}

/*

Another sol:

public List<List<String>> mostPopularCreator(String[] creators, String[] ids, int[] views) {
        HashMap<String,int[]>map=new HashMap<>();
        int n=creators.length;
        long max=0;
        for(int i=0;i<n;i++){
            if(!map.containsKey(creators[i]))
                map.put(creators[i],new int[]{views[i],i});
            else{
                map.get(creators[i])[0]+=views[i];
                int idx=map.get(creators[i])[1];
                if(views[idx]<views[i])
                    idx=i;
                else if(views[idx]==views[i]){
                    if(ids[idx].compareTo(ids[i])>0)
                        idx=i;
                }
                map.get(creators[i])[1]=idx;
            }
            max=Math.max(max,map.get(creators[i])[0]);
        }
        List<List<String>>ans=new ArrayList<>();
        for(String k:map.keySet()){
            int f=map.get(k)[0];
            int idx=map.get(k)[1];
            if(f==max)
                ans.add(new ArrayList<>(Arrays.asList(k,ids[idx])));
        }
        return ans;
    }

 */