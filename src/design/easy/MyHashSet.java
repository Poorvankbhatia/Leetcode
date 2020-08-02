/*
Design a HashSet without using any built-in hash table libraries.

To be specific, your design should include these functions:

add(value): Insert a value into the HashSet.
contains(value) : Return whether the value exists in the HashSet or not.
remove(value): Remove a value in the HashSet. If the value does not exist in the HashSet, do nothing.

Example:

MyHashSet hashSet = new MyHashSet();
hashSet.add(1);
hashSet.add(2);
hashSet.contains(1);    // returns true
hashSet.contains(3);    // returns false (not found)
hashSet.add(2);
hashSet.contains(2);    // returns true
hashSet.remove(2);
hashSet.contains(2);    // returns false (already removed)

Note:

All values will be in the range of [0, 1000000].
The number of operations will be in the range of [1, 10000].
Please do not use the built-in HashSet library.
 */
package design.easy;

import java.util.*;

public class MyHashSet {


    private static class BSTNode {
        private BSTNode left;
        private BSTNode right;
        private int val;

        public BSTNode(int val) {
            this.val = val;
        }

        private void add(BSTNode bstNode) {
            if(bstNode.val>this.val) {
                if(this.right==null) {
                    this.right=bstNode;
                } else {
                    this.right.add(bstNode);
                }
            } else if(bstNode.val<this.val) {
                if(this.left==null) {
                    this.left = bstNode;
                } else {
                    this.left.add(bstNode);
                }
            }
        }
        private List<BSTNode> getAllNodes() {
            Queue<BSTNode> q =new LinkedList<>();
            q.add(this);
            List<BSTNode> list=new LinkedList<>();
            while(!q.isEmpty()){
                BSTNode node=q.poll();
                list.add(node);
                if(node.right!=null)q.add(node.right);
                if(node.left!=null)q.add(node.left);
            }
            return list;
        }
        private boolean contains(int val) {
            if(this.val==val) {
                return true;
            }
            if(this.val>val && this.left!=null) {
                return this.left.contains(val);
            } else if(this.val<val && this.right!=null) {
                return this.right.contains(val);
            }
            return false;
        }
        private BSTNode remove(int val) {
            if(this.val == val) {
                if(this.right==null && this.left==null) //if leaf remove
                    return null;

                if(this.right==null || this.left==null) //if one child return that one child!
                    return (this.left!=null)?this.left:this.right;

                this.val=this.left.findMax().val; //substitute with the rightmost left successor
                this.left.remove(this.val);
            }
            if(this.val>val && this.left!=null)
                this.left=this.left.remove(val);

            if(this.val<val && this.right!=null)
                this.right=this.right.remove(val);

            return this;
        }

        private BSTNode findMax(){
            BSTNode node=this;
            while(node.right!=null){
                node=node.right;
            }
            return node;
        }
    }
    private BSTNode[] arr;
    private int size;
    private final double LOAD_FACTOR = 0.7;

    public MyHashSet() {
        arr = new BSTNode[1];
        size = 0;
    }

    private int hash(int key) {
        return key % arr.length;
    }

    public void add(int key) {
        int h = hash(key);
        BSTNode bstNode = new BSTNode(key);
        if(arr[h]==null) {
            arr[h] = bstNode;
        } else {
            arr[h].add(bstNode);
        }
        size++;
        if(size>(int)(LOAD_FACTOR * arr.length)) {
            reHash();
        }
    }

    private void reHash(){
        BSTNode [] newArr = new BSTNode[arr.length*2];
        BSTNode [] old = arr;
        arr=newArr;
        size=0;
        for(BSTNode n:old){
            if(n!=null){
                List<BSTNode> list = n.getAllNodes();
                for (BSTNode node : list) {
                    add(node.val);
                }
            }
        }
    }

    public void remove(int key) {
        int h=hash(key);
        if(arr[h]!=null)
            arr[h] = arr[h].remove(key);
        size--;
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int h=hash(key);
        if(arr[h]!=null)
            return arr[h].contains(key);
        return false;
    }

    public static void main(String[] args) {
        MyHashSet set = new MyHashSet();
        set.add(1);
        set.add(2);
    }

}
