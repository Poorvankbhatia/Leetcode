/*
You are given a tree with n nodes numbered from 0 to n - 1 in the form of a parent array parent where parent[i]
is the parent of the ith node. The root of the tree is node 0, so parent[0] = -1 since it has no parent.
You want to design a data structure that allows users to lock, unlock, and upgrade nodes in the tree.

The data structure should support the following functions:

Lock: Locks the given node for the given user and prevents other users from locking the same node. You may only lock
a node using this function if the node is unlocked.
Unlock: Unlocks the given node for the given user. You may only unlock a node using this function if it is currently
locked by the same user.
Upgrade: Locks the given node for the given user and unlocks all of its descendants regardless of who locked it. You
may only upgrade a node if all 3 conditions are true:
The node is unlocked,
It has at least one locked descendant (by any user), and
It does not have any locked ancestors.
Implement the LockingTree class:

LockingTree(int[] parent) initializes the data structure with the parent array.
lock(int num, int user) returns true if it is possible for the user with id user to lock the node num, or false
otherwise. If it is possible, the node num will become locked by the user with id user.
unlock(int num, int user) returns true if it is possible for the user with id user to unlock the node num, or
false otherwise. If it is possible, the node num will become unlocked.
upgrade(int num, int user) returns true if it is possible for the user with id user to upgrade the node num, or
false otherwise. If it is possible, the node num will be upgraded.

Input
["LockingTree", "lock", "unlock", "unlock", "lock", "upgrade", "lock"]
[[[-1, 0, 0, 1, 1, 2, 2]], [2, 2], [2, 3], [2, 2], [4, 5], [0, 1], [0, 1]]
Output
[null, true, false, true, true, true, false]

Explanation
LockingTree lockingTree = new LockingTree([-1, 0, 0, 1, 1, 2, 2]);
lockingTree.lock(2, 2);    // return true because node 2 is unlocked.
                           // Node 2 will now be locked by user 2.
lockingTree.unlock(2, 3);  // return false because user 3 cannot unlock a node locked by user 2.
lockingTree.unlock(2, 2);  // return true because node 2 was previously locked by user 2.
                           // Node 2 will now be unlocked.
lockingTree.lock(4, 5);    // return true because node 4 is unlocked.
                           // Node 4 will now be locked by user 5.
lockingTree.upgrade(0, 1); // return true because node 0 is unlocked and has at least one locked descendant (node 4).
                           // Node 0 will now be locked by user 1 and node 4 will now be unlocked.
lockingTree.lock(0, 1);    // return false because node 0 is already locked.


Constraints:

n == parent.length
2 <= n <= 2000
0 <= parent[i] <= n - 1 for i != 0
parent[0] == -1
0 <= num <= n - 1
1 <= user <= 104
parent represents a valid tree.
At most 2000 calls in total will be made to lock, unlock, and upgrade.

 */
package design.medium;

import java.util.*;

public class LockingTree {

    private final Map<Integer, Integer> locks;
    private final List<Integer>[] tree;
    private final int[] parent;

    public LockingTree(int[] parent) {
        locks = new HashMap<>();
        tree = new List[parent.length];
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] != -1) {
                if (tree[parent[i]] == null) tree[parent[i]] = new ArrayList<>();
                tree[parent[i]].add(i);
            }
        }
        this.parent = parent;
    }

    public boolean lock(int num, int user) {
        if (locks.containsKey(num)) {
            return false;
        }
        locks.put(num, user);
        return true;
    }

    public boolean unlock(int num, int user) {
        if (!locks.containsKey(num) || (locks.get(num) != user)) {
            return false;
        }
        locks.remove(num);
        return true;
    }

    public boolean upgrade(int num, int user) {
        Set<Integer> lockedChildren = lockedChildren(num);
        if (!checkLockedParent(num) && lockedChildren.size() > 0 && !locks.containsKey(num)) {
            locks.put(num, user);
            for (int x : lockedChildren) {
                locks.remove(x);
            }
            return true;
        }
        return false;
    }

    private boolean checkLockedParent(int num) {
        int p = num;
        while (parent[p] != -1) {
            if (locks.containsKey(p)) return true;
            p = parent[p];
        }
        return locks.containsKey(p);
    }

    /*
    Get all locked children.
     */
    private Set<Integer> lockedChildren(int num) {
        Set<Integer> locked = new HashSet<>();
        if(tree[num]!=null) {
            for (int next : tree[num]) {
                if (locks.containsKey(next)) {
                    locked.add(next);
                }
                locked.addAll(lockedChildren(next));
            }
        }
        return locked;
    }

    public static void main(String[] args) {
        LockingTree lockingTree = new LockingTree(new int[]{-1, 0, 3, 1, 0});
        System.out.println(lockingTree.upgrade(4, 5));
        System.out.println(lockingTree.upgrade(3, 8));
        System.out.println(lockingTree.unlock(0, 7));
        System.out.println(lockingTree.lock(2, 7));
        System.out.println(lockingTree.upgrade(4, 6));

    }

}
