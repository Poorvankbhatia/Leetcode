/*

From any string, we can form a subsequence of that string by deleting some number of characters (possibly no deletions).

Given two strings source and target, return the minimum number of subsequences of source such that their concatenation equals target. If the task is impossible, return -1.



Example 1:

Input: source = "abc", target = "abcbc"
Output: 2
Explanation: The target "abcbc" can be formed by "abc" and "bc", which are subsequences of source "abc".
Example 2:

Input: source = "abc", target = "acdbc"
Output: -1
Explanation: The target string cannot be constructed from the subsequences of source string due to the character "d" in target string.
Example 3:

Input: source = "xyz", target = "xzyxz"
Output: 3
Explanation: The target string can be constructed as follows "xz" + "y" + "xz".


Constraints:

Both the source and target strings consist of only lowercase English letters from "a"-"z".
The lengths of source and target string are between 1 and 1000.

 */
package dyanamicprogramming.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShortestWayToFormString {

    Map<Character, List<Integer>> map = new HashMap<>();
    public int shortestWay(String source, String target) {

        for (int i=0;i<source.length();i++) {
            char c = source.charAt(i);
            map.putIfAbsent(c,new ArrayList<>());
            map.get(c).add(i);
        }
        int[] dp = new int[target.length()];

        if(isSubSequence(target.charAt(0)+"")) {
            dp[0]=1;
        } else {
            return -1;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(target.charAt(0));
        for (int i=1;i<target.length();i++) {
            sb.append(target.charAt(i));
            if(sb.length()<=source.length() && isSubSequence(sb.toString())) {
                dp[i] = dp[i-1];
            } else if(isSubSequence(target.charAt(i)+"")) {
                dp[i] = dp[i-1]+1;
                sb.setLength(0);
                sb.append(target.charAt(i));
            } else {
                return -1;
            }
        }

        return dp[target.length()-1];
    }

    private boolean isSubSequence(String T) {
        int prev = -1;
        for (char c : T.toCharArray()) {
            List<Integer> list = map.get(c);
            if(list==null || list.size()==0) {
                return false;
            }
            int idx = binarySearch(list, prev);
            if (idx==-1) {
                return false;
            }
            prev = idx;
        }
        return true;
    }

    private int binarySearch(List<Integer> list, int lastFound){
        int start=0;
        int end = list.size()-1;
        int result = -1;
        while(start<=end){
            int mid = start + (end-start)/2;
            if(list.get(mid)>lastFound){
                result = list.get(mid);
                end = mid-1;
            } else {
                start = mid+1;
            }

        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new ShortestWayToFormString().shortestWay("aaa","aaaaaa"));
    }

}

/*

Other approaches:

first opinion is that we can use two pointer , one iterate src, another iterate tar.
for each tar char, we move j until src[j] == tar[i], if j == src.length, res++, j = 0;
in this solution, we greedy match as many chars from src to tar as possible which can lead mininum use of src.
and we can build a set to save all the char in src, if there exists a char from tar which not exists in set, return -1.

public int shortestWay(String source, String target) {
	char[] cs = source.toCharArray(), ts = target.toCharArray();
	boolean[] map = new boolean[26];
	for (int i = 0; i < cs.length; i++)
		map[cs[i] - 'a'] = true;
	int j = 0, res = 1;
	for (int i = 0; i < ts.length; i++,j++) {
		if (!map[ts[i] - 'a']) return -1;
		while (j < cs.length && cs[j] != ts[i]) {
			j++;
		}
		if (j == cs.length) {
			j = -1;
			res++;
			i--;
		}
	}
	return res;
}
follow up 1: yes, correct. could u implement it with O 1 space, which mean without set.
okay. without set, we need a way to make sure there is a char which not in src. we can iterate src completely. if the j not move, then we can return -1.

public int shortestWay(String source, String target) {
	char[] cs = source.toCharArray(), ts = target.toCharArray();
	int res = 0;
	for (int i = 0; i < ts.length; ) {
		int oriI = i;
		for (int j = 0; j < cs.length; j++) {
			if (i < ts.length && cs[j] == ts[i])
				i++;
		}
		if (i == oriI) return -1;
		res++;
	}
	return res;
}
follow up 2: fine. what's the time complexity for above solutions. O(MN). could u make it better?
the time complexity is better than O (MN), should be O(logM * N) or O (N)
to find a logM way, it is easy to think of binary search. for each char in tar, we need loop from j to end, to find a char same as tar[i].
we can build a map which key is from 'a' -> 'z', the value is idx for this char in src. because idx is add from small to big. when we iterate tar[i], we can easily to find the tar[i]'s idx list. to search is there a idx is larger or equal than j+1. it is logM. and we have N char in tar, so the time complexity is N * logM
the time is to build the map is O(M);

public int shortestWay(String source, String target) {
	char[] cs = source.toCharArray(), ts = target.toCharArray();
	int res = 1;
	List<Integer>[] idx = new List[26];
	for (int i = 0; i < 26; i++) idx[i] = new ArrayList<>();
	for (int i = 0; i < cs.length; i++) idx[cs[i] - 'a'].add(i);
	int j = 0;
	for (int i = 0; i < ts.length; ) {
		List<Integer> tar = idx[ts[i] - 'a'];
		if (tar.isEmpty()) return -1;
		int k = Collections.binarySearch(tar,j);
		if (k < 0) k = -k - 1;
		if (k == tar.size()) {
			res++;
			j = 0;
		} else {
			j = tar.get(k) + 1;
			i++;
		}

	}
	return res;
}
follow up 3: great. could u improve it more?
so we have to think a solution which is O(N), how should we use O(1) to know the next J pos?
maybe we can use more to save time.
in binary search solution we will have a map like a ->{1,3,7,16} (total src length is 20), so we need binary search.
if we can flatten them, i mean for each pos in 20 length, we just save the next idx, we can use O 1 to find the next J.
a -> {1,1,3,3,7,7,7,7,16,16,16,16,16,16,16,16,16,0,0,0}
for example if now j is 4, we can just check map[4] = 7; we know 7 pos have an 'a', so next j will be 7 + 1.
if now j is 17, we get map[17] = 0, we know there is no more j after. so j = 0, res++;
the time complexity is O (N) , and build the map cost 26 * M

public int shortestWay(String source, String target) {
	char[] cs = source.toCharArray(), ts = target.toCharArray();
	int[][] idx = new int[26][cs.length];
	for (int i = 0; i < cs.length; i++) idx[cs[i] - 'a'][i] = i + 1;
	for (int i = 0; i < 26; i++) {
		for (int j = cs.length - 1, pre = 0; j >= 0; j--) {
			if (idx[i][j] == 0) idx[i][j] = pre;
			else pre = idx[i][j];
		}
	}
	int res = 1, j = 0;
	for (int i = 0; i < ts.length; i++) {
		if (j == cs.length) {
			j = 0;
			res++;
		}
		if (idx[ts[i] - 'a'][0] == 0) return -1;
		j = idx[ts[i] - 'a'][j];
		if (j == 0 ) {
			res++;
			i--;
		}
	}
	return  res;
}
follow up 4: cool, if we assume which can copy a array to another array with 26 length in constant time. could u implement it with O(M + N)
it sounds like we need switch the map from [26][src.length] to [src.length][26].
and we also need to use O 1 time to know what's next j position.
now we are in the 2rd idx (j = 1), so tar[i] = 'a' we should save the map[1]['a'] the next position of j.
if we are in the last idx, i think the map should be all 0, except the last tar char. for example the char is 'a'
so the map for it is [last+1,0,0,...,0]
how about last -1 idx, if the tar[last - 1] is same as tar[last], we just update it , [last - 1 + 1, 0....0]
if is not same. we can update a 0 with last - 1 + 1

public int shortestWay(String source, String target) {
	char[] cs = source.toCharArray(), ts = target.toCharArray();
	int[][] idx = new int[cs.length][26];
	idx[cs.length - 1][cs[cs.length - 1] - 'a'] = cs.length;
	for (int i = cs.length - 2; i >= 0; i--) {
		idx[i] = Arrays.copyOf(idx[i + 1],26);
		idx[i][cs[i] - 'a'] = i + 1;
	}
	int j = 0, res = 1;
	for (int i = 0; i < ts.length; i++) {
		if (j == cs.length) {
			j = 0;
			res++;
		}
		j = idx[j][ts[i] - 'a'];
		if (idx[0][ts[i] - 'a'] == 0) return -1;
		if (j == 0) {
			res++;
			i--;
		}
	}
	return res;
}

 */