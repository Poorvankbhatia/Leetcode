/*
Given a binary tree root and a linked list with head as the first node.

Return True if all the elements in the linked list starting from the head correspond to some downward path connected in the binary tree otherwise return False.

In this context downward path means a path that starts at some node and goes downwards.


Input: head = [4,2,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
Output: true
Explanation: Nodes in blue form a subpath in the binary Tree.

Input: head = [1,4,2,6], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
Output: true

Input: head = [1,4,2,6,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
Output: false
Explanation: There is no path in the binary tree that contains all the elements of the linked list from head.


Constraints:

1 <= node.val <= 100 for each node in the linked list and binary tree.
The given linked list will contain between 1 and 100 nodes.
The given binary tree will contain between 1 and 2500 nodes.

 */
package tree.medium;

import linklist.ListNode;
import tree.TreeNode;

public class LinkedListBT {

    public boolean isSubPath(ListNode head, TreeNode root) {
        if(root == null){
            return false;
        }
        return util(root, head) || isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    private static boolean util(TreeNode root, ListNode head) {
        if(head == null){
            return true;
        }
        if(root == null){
            return false;
        }
        if(root.val != head.val){
            return false;
        }
        return util(root.left, head.next) || util(root.right, head.next);
    }
}

/*

Time: O(m * min(n, h)), where n is size of binary tree, m is size of linked list
Space: O(h), h is height of the binary tree.


KMP Search
Can check bare KMP implement on this solution: 28. Implement strStr()
Idea:

This problem is the same with problem: find needle in haystack where haystack is the binary tree, needle is the linked list.
Convert needle linked list to array, so we can jump to any position of the needle.
Compute KMP table (LPS) which is Longest Prefix also Suffix.
KMP Search, where i++ in string equal to i.left or i.right in binary tree.
    int[] needle, lps;
    public boolean isSubPath(ListNode head, TreeNode root) {
        needle = convertLinkedListToArray(head);
        lps = computeKMPTable(needle);
        return kmpSearch(root, 0);
    }
    boolean kmpSearch(TreeNode i, int j) {
        if (j == needle.length) return true;
        if (i == null) return false;
        while (j > 0 && i.val != needle[j]) j = lps[j - 1];
        if (i.val == needle[j]) j++;
        return kmpSearch(i.left, j) || kmpSearch(i.right, j);
    }
    int[] computeKMPTable(int[] pattern) {
        int n = pattern.length;
        int[] lps = new int[n];
        for (int i = 1, j = 0; i < n; i++) {
            while (j > 0 && pattern[i] != pattern[j]) j = lps[j - 1];
            if (pattern[i] == pattern[j]) lps[i] = ++j;
        }
        return lps;
    }
    int[] convertLinkedListToArray(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) { list.add(head.val); head = head.next; }
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) arr[i] = list.get(i);
        return arr;
    }
Complexity

Time: O(m+n), where n is size of binary tree, m is size of linked list
Space: O(h+m), h is height of the binary tree.

 */