/*

In an election, the i-th vote was cast for persons[i] at time times[i].

Now, we would like to implement the following query function: TopVotedCandidate.q(int t) will return the number of the person that was leading the election at time t.

Votes cast at time t will count towards our query.  In the case of a tie, the most recent vote (among tied candidates) wins.



Example 1:

Input: ["TopVotedCandidate","q","q","q","q","q","q"], [[[0,1,1,0,0,1,0],[0,5,10,15,20,25,30]],[3],[12],[25],[15],[24],[8]]
Output: [null,0,1,1,0,0,1]
Explanation:
At time 3, the votes are [0], and 0 is leading.
At time 12, the votes are [0,1,1], and 1 is leading.
At time 25, the votes are [0,1,1,0,0,1], and 1 is leading (as ties go to the most recent vote.)
This continues for 3 more queries at time 15, 24, and 8.


Note:

1 <= persons.length = times.length <= 5000
0 <= persons[i] <= persons.length
times is a strictly increasing array with all elements in [0, 10^9].
TopVotedCandidate.q is called at most 10000 times per test case.
TopVotedCandidate.q(int t) is always called with t >= times[0].

 */
package heap.medium;
import java.util.TreeMap;

public class TopVotedCandidate {

    TreeMap<Integer,Integer> map = new TreeMap<>();
    public TopVotedCandidate(int[] persons, int[] times) {
        int[] count = new int[persons.length];
        int maxCount = 0;
        int mostRecent = -1;
        int n = persons.length;
        for(int i=0;i<n;i++) {
            count[persons[i]]+=1;
            if(count[persons[i]]>=maxCount) {
                maxCount = count[persons[i]];
                mostRecent = persons[i];
            }
            map.put(times[i],mostRecent);
        }
    }

    public int q(int t) {
        if(map.get(t)!=null) {
            return map.get(t);
        }
        return map.get(map.floorKey(t));
    }

    public static void main(String[] args) {
        TopVotedCandidate topVotedCandidate = new TopVotedCandidate(new int[]{0,0,0,0,1},new int[]{0,6,39,52,75});
        System.out.println(topVotedCandidate.q(99));
    }

}

/*

Time complexity for constructor TopVotedCandidate(int[] persons, int[] times) is O(nlogn), and for q(int t) is O(logn).



private Map<Integer, Integer> map = new HashMap<>();
    private int[] times;
    public TopVotedCandidate(int[] persons, int[] times) {
        this.times = times;
        int[] count = new int[persons.length + 1];
        for (int i = 0, winner = -1; i < times.length; ++i) {
            ++count[persons[i]];
            if (map.isEmpty() || count[winner] <= count[persons[i]])
            {
                winner = persons[i];
            }
            map.put(times[i], winner);
        }
    }
    public int q(int t) {
        int i = 0, j = times.length;
        while (i < j){
            int mid = i + (j - i) / 2;
            if (times[mid] <= t){
                i = mid + 1;
            }else{
                j = mid;
            }
        }
        return map.get(times[i-1]); // fetch the corresponding information.
    }}

HashMap.put() cost only O(1) for each operation. Therefore,
time complexity: Constructor O(n), q(int t) is O(logn).

 */