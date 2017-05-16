/*

There is a list of sorted integers from 1 to n. Starting from left to right, remove the first number and every other number
afterward until you reach the end of the list.

Repeat the previous step again, but this time from right to left, remove the right most number and every other number from the remaining numbers.

We keep repeating the steps again, alternating left to right and right to left, until a single number remains.

Find the last number that remains starting with a list of length n.

Example:

Input:
n = 9,
1 2 3 4 5 6 7 8 9
2 4 6 8
2 6
6

Output:
6

 */
package arrays.medium;

/**
 * Created by poorvank on 15/05/17.
 */
public class EliminationGame {

    public int lastRemaining(int n) {
        if(n==1) {
            return 1;
        }
        return leftRight(n);
    }

    private int leftRight(int n) {
        if(n==1) {
            return 1;
        }
        return 2*rightLeft(n/2);
    }

    private int rightLeft(int n) {
        if(n==1) {
            return 1;
        }
        if (n % 2 == 0){
            return 2 * leftRight(n / 2) - 1;
        } else {
            return 2 * leftRight(n / 2);
        }
    }

    public static void main(String[] args) {
        System.out.println(new EliminationGame().lastRemaining(19403939));
    }

}


/*

Treat both leftRight and RightLeft as different computations.
In case of RightLeft
If the length of array is even, we will get only odd number
        [1, 2, 3, 4] -> [1, 3] = 2 * [1, 2] - 1
else if the length of array is odd, we will get only even number
        [1, 2, 3, 4, 5] -> [2, 4] = 2 * [1, 2]


In case of LeftRight:
the length of array doesn't matter
         [1, 2, 3, 4] -> 2 * [1, 2]
         [1, 2, 3, 4, 5] -> 2 * [1, 2]

O(logN) solution


TLE Solution:

public int lastRemaining(int n) {

        Deque<Integer> deque1 = new LinkedList<>();
        Deque<Integer> deque2 = new LinkedList<>();

        for (int i=1;i<=n;i++) {
            deque1.add(i);
        }



        while (!deque1.isEmpty() || !deque2.isEmpty()) {
            if(deque1.size()+deque2.size()==1) {
                break;
            }
            removeAndAdd(deque1,deque2);
        }

        return deque1.isEmpty()?deque2.peek():deque1.peek();

    }

    private void removeAndAdd(Deque<Integer> d1,Deque<Integer> d2) {

        if(d2.size()==0) {
            while (d1.size()!=0) {
                d1.removeFirst();
                if(!d1.isEmpty()) {
                    int addBack = d1.removeFirst();
                    d2.add(addBack);
                }
            }
        } else {
            while (d2.size()!=0) {
                d2.removeLast();
                if(!d2.isEmpty()) {
                    int addBack = d2.removeLast();
                    d1.addFirst(addBack);
                }
            }
        }

    }

 */