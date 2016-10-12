/*

You are a product manager and currently leading a team to develop a new product.
Unfortunately, the latest version of your product fails the quality check. Since each version is
developed based on the previous version, all the versions after a bad version are also bad.

Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.

You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad
version. You should minimize the number of calls to the API.

 */
package binarysearch.easy;

/**
 * Created by poorvank on 07/10/16.
 */
public class FirstBadVersion {

    public int firstBadVersion(int n) {

        if(n==0) {
            return -1;
        }

        int start = 1,end=n;
        /*
            if mid==start
            && isBadVersion(start) = false
            && isBadVersion(end) = true
            Infinite loop will start
         */
        while (start<end-1) {
            /*
              INSTEAD OF mid=(start+end)/2 the following is done because if start & end are both big integers it might cross the
              max value of int
             */
            int mid = start + (end - start)/2;
            if(isBadVersion(mid)) {
                end = mid;
            } else {
                start = mid;
            }
        }

        if(isBadVersion(start)) {
            return start;
        } else if(isBadVersion(end)) {
            return end;
        } else {
            return -1;
        }

    }

    private boolean isBadVersion(int version) {
        return version>=1702766719;
    }

    public static void main(String[] args) {
        System.out.print(new FirstBadVersion().firstBadVersion(2126753390));
    }

}

/*

2126753390 versions
2147483647
1702766719 is the first bad version.

 */