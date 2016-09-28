/*

Design a Phone Directory which supports the following operations:

get: Provide a number which is not assigned to anyone.
check: Check if a number is available or not.
release: Recycle or release a number.

 */
package design.medium;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by poorvank on 19/09/16.
 */
public class PhoneDirectory {

    int max;
    HashSet<Integer> set;
    LinkedList<Integer> linkedList;

    public PhoneDirectory(int phoneNoCount) {
        max = phoneNoCount;
        set = new HashSet<>();
        linkedList = new LinkedList<>();
        for (int i=1;i<=max;i++) {
            linkedList.add(i);
        }
    }

    public int get() {

        if(linkedList.isEmpty()) {
           return -1;
        }

        int phoneNo = linkedList.removeFirst();
        set.add(phoneNo);
        return phoneNo;

    }

    public void release(int n) {
        if(set.contains(n)) {
            set.remove(n);
            linkedList.addFirst(n);
        }
    }

    public boolean check(int n) {
        return !set.contains(n) && n<=max;
    }

}
