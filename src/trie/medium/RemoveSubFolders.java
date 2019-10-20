/*

Given a list of folders, remove all sub-folders in those folders and return in any order the folders after removing.

If a folder[i] is located within another folder[j], it is called a sub-folder of it.

The format of a path is one or more concatenated strings of the form: / followed by one or more lowercase English letters.
For example, /leetcode and /leetcode/problems are valid paths while an empty string and / are not.



Example 1:

Input: folder = ["/a","/a/b","/c/d","/c/d/e","/c/f"]
Output: ["/a","/c/d","/c/f"]
Explanation: Folders "/a/b/" is a subfolder of "/a" and "/c/d/e" is inside of folder "/c/d" in our filesystem.
Example 2:

Input: folder = ["/a","/a/b/c","/a/b/d"]
Output: ["/a"]
Explanation: Folders "/a/b/c" and "/a/b/d/" will be removed because they are subfolders of "/a".
Example 3:

Input: folder = ["/a/b/c","/a/b/ca","/a/b/d"]
Output: ["/a/b/c","/a/b/ca","/a/b/d"]


Constraints:

1 <= folder.length <= 4 * 10^4
2 <= folder[i].length <= 100
folder[i] contains only lowercase letters and '/'
folder[i] always starts with character '/'
Each folder name is unique.

 */
package trie.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class RemoveSubFolders {

    private class TrieNode {
        TrieNode[] children = new TrieNode[27];
        boolean isEnd;
    }

    TrieNode root;

    public List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder, Comparator.comparingInt(String::length));
        root = new TrieNode();
        List<String> result = new ArrayList<>();
        for(String s : folder) {
            if(insert(s,root,0)) {
                result.add(s);
            }
        }
        return result;
    }

    private boolean insert(String key,TrieNode root,int d) {
        if(d==key.length()) {
            root.isEnd=true;
            return true;
        }
        char c = key.charAt(d);
        if((c=='/' && root.children[26]==null) || (c!='/' && root.children[c-'a']==null)) {
            if(c-'a'>=0) {
                root.children[c-'a']=new TrieNode();
            } else {
                root.children[26] = new TrieNode();
            }
        } else if(c!='/' && root.children[c-'a'].isEnd
                // For cases like /c/a,/c/ab both should exist.
                && d<key.length()-1 && key.charAt(d+1)=='/') {
            return false;
        }
        return insert(key,(c=='/'?root.children[26]:root.children[c-'a']),d+1);
    }

    public static void main(String[] args) {
        System.out.println(new RemoveSubFolders().removeSubfolders(new String[]{"/a","/a/b"}));
    }

}
