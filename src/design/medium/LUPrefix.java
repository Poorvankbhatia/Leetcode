/*

 */
package design.medium;

import java.util.TreeSet;

public class LUPrefix {

    TreeSet<Integer> treeSet = new TreeSet<>();
    int n;

    public LUPrefix(int n) {
        for (int i = 1; i <=n; i++) {
            treeSet.add(i);
        }
        this.n = n;
    }

    public void upload(int video) {
        treeSet.remove(video);
    }

    public int longest() {
        return treeSet.isEmpty()?n : treeSet.first()-1;
    }

}

/*
Keep a TreeSet of all the numbers (videos) NOT uploaded yet.
You can then look at the lowest not uploaded value, and the prefix is the value just before this one.
Since it's a tree, it allows to get the lowest value (leftmost node in the tree) in O(log n)

Complexity
Time complexity:
initialization (new LUPrefix()) : O(n log n)
upload : O(log n)
longest : O(log n)
Space complexity: O(n)

Another method: O(1) longest()

class LUPrefix {

    boolean[] uploaded;
    int max;
    int size;
    public LUPrefix(int n) {
        uploaded = new boolean[n + 1];
        max = 0;
        size = n;
    }

    public void upload(int video) {
        uploaded[video] = true;


         //If the longest uploaded prefix is not the previous video then
         //there must be some other video (smaller than current video) which is not uploaded yet

        if(max != video - 1) return;


         //If the longest uploaded prefix is the previous video then we can conclude that
         //all the videos before it have been uploaded.

         //Now we need to check all videos (greater than current video) that have been uploaded and form a contigous subarray

        while(video <= size && uploaded[video]){
            video++;
        }
        max = video - 1;
    }

    public int longest() {
        return max;
    }
}

longest O(n)

class LUPrefix {

    int[] arr;
    int min =0;
    public LUPrefix(int n) {
        arr = new int[n];
    }

    public void upload(int video) {
        arr[video-1]=video;
    }

    public int longest() {
        if(arr[0]==0) {
            return 0;
        }
        for(int i=min;i<arr.length;i++) {
            if(arr[i]==0) {
                break;
            }
            min=i;
        }
        return min+1;
    }
}

https://leetcode.com/problems/longest-uploaded-prefix/discuss/2646734/Disjoint-Set-oror-Java

 */

