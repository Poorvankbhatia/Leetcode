package heap.medium;
import java.util.HashMap;
import java.util.Map;
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