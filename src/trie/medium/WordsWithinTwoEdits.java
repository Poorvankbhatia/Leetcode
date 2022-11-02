package trie.medium;

import java.util.ArrayList;
import java.util.List;

public class WordsWithinTwoEdits {

    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> result = new ArrayList<>();
        for(String q : queries) {
            for(String d : dictionary) {
                if(compare(q,d)) {
                    result.add(q);
                    break;
                }
            }
        }
        return result;
    }

    private boolean compare(String a, String b) {
        if(a.length()!=b.length()) {
            return false;
        } else {
            int sum = 0;
            for(int i=0;i<a.length();i++) {
                if(a.charAt(i)!=b.charAt(i)) sum++;
            }
            return sum<=2;
        }
    }

}


/*

Time Complexity: O(m * n * k)
Space Complexity : O(1)
m = length queries array
n = length dictionary array
m = length String

 Trie sol:

 Time Complexity: O(n * k + m * k)
Space Complexity : O(n * 26)
m = length queries array
n = length dictionary array
m = length String

 class Solution {
    private static class Node {
		private char data;
		private boolean isEnd;
		private Node[] children;

		public Node(char data) {
			this.data = data;
			this.isEnd = false;
			this.children = new Node[26];
		}
	}

	private Node root = new Node('/');

	private void insertWord(String word) {
		Node curr = root;
		for (int i = 0; i < word.length(); i++) {
			int childIdx = word.charAt(i) - 'a';
			if (curr.children[childIdx] == null) {
				curr.children[childIdx] = new Node(word.charAt(i));
			}
			curr = curr.children[childIdx];
		}
		curr.isEnd = true;
	}

	private boolean searchWord(Node root, String word, int count, int index) {
		if (index == word.length()) {
			return count <= 2;
		}
		boolean ans = false;
		for (int i = 0; i < root.children.length; i++) {
			if (root.children[i] != null) {
				ans |= searchWord(root.children[i], word, count + (((word.charAt(index) - 'a') == i) ? 0 : 1),
						index + 1);
			}
		}
		return ans;
	}

	public List<String> twoEditWords(String[] queries, String[] dictionary) {
		List<String> ans = new ArrayList<>();
		for (String word : dictionary) {
			insertWord(word);
		}
		for (String query : queries) {
			Node curr = root;
			if (searchWord(curr, query, 0, 0)) {
				ans.add(query);
			}
		}
		return ans;
	}
}

 */