/*

Five silent philosophers sit at a round table with bowls of spaghetti. Forks are placed between each pair of adjacent philosophers.

Each philosopher must alternately think and eat. However, a philosopher can only eat spaghetti when they have both left
and right forks. Each fork can be held by only one philosopher and so a philosopher can use the fork only if it is not being used
by another philosopher. After an individual philosopher finishes eating, they need to put down both forks so that the forks become
available to others. A philosopher can take the fork on their right or the one on their left as they become available, but cannot
start eating before getting both forks.

Eating is not limited by the remaining amounts of spaghetti or stomach space; an infinite supply and an infinite demand are assumed.

Design a discipline of behavior (a concurrent algorithm) such that no philosopher will starve; i.e., each can forever continue to
alternate between eating and thinking, assuming that no philosopher can know when others may want to eat or think.

The philosophers' ids are numbered from 0 to 4 in a clockwise order. Implement the function void
wantsToEat(philosopher, pickLeftFork, pickRightFork, eat, putLeftFork, putRightFork) where:

philosopher is the id of the philosopher who wants to eat.
pickLeftFork and pickRightFork are functions you can call to pick the corresponding forks of that philosopher.
eat is a function you can call to let the philosopher eat once he has picked both forks.
putLeftFork and pickRightFork are functions you can call to put down the corresponding forks of that philosopher.
The philosophers are assumed to be thinking as long as they are not asking to eat (the function is not being called with their number).
Five threads, each representing a philosopher, will simultaneously use one object of your class to simulate the process. It is possible that the function will be called for the same philosopher more than once, even before the last call ends.



Example 1:

Input: n = 1
Output: [[4,2,1],[4,1,1],[0,1,1],[2,2,1],[2,1,1],[2,0,3],[2,1,2],[2,2,2],[4,0,3],[4,1,2],[0,2,1],
[4,2,2],[3,2,1],[3,1,1],[0,0,3],[0,1,2],[0,2,2],[1,2,1],[1,1,1],[3,0,3],[3,1,2],[3,2,2],[1,0,3],[1,1,2],[1,2,2]]
Explanation:
n is the number of times each philosopher will call the function.
The output array describes the calls you made to the functions controlling the forks and the eat function, its format is:
output[i] = [a, b, c] (three integers)
- a is the id of a philosopher.
- b specifies the fork: {1 : left, 2 : right}.
- c specifies the operation: {1 : pick, 2 : put, 3 : eat}.


Constraints:

1 <= n <= 60

 */
package threads.medium;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosophers {

    private Lock forks[] = new Lock[5];
    private Semaphore semaphore = new Semaphore(4);

    public DiningPhilosophers()
    {
        for (int i = 0; i < 5; i++)
            forks[i] = new ReentrantLock();
    }

    void pickFork(int id, Runnable pick)
    {
        forks[id].lock();
        pick.run();
    }

    void putFork(int id, Runnable put)
    {
        put.run();
        forks[id].unlock();
    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException
    {
        int leftFork = philosopher;
        int rightFork = (philosopher + 4) % 5;

        semaphore.acquire();

        pickFork(leftFork, pickLeftFork);
        pickFork(rightFork, pickRightFork);
        eat.run();
        putFork(rightFork, putRightFork);
        putFork(leftFork, putLeftFork);

        semaphore.release();
    }

}

/*

Every philosopher starts by picking the left fork and after that he picks the right one.
Without any limitations a deadlock is possible when all philosophers pick the left fork simultaneously.
If we limit the number of philosophers who can pick the left fork to 4 there will always be a philosopher
who can pick the right fork (the one to the left of a philosopher without any forks).


Another sol:
class DiningPhilosophers {

   // philosopher 0 - forks 4 and 0,
   // philosopher 1 - forks 0 and 1,
   // philosopher 2 - forks 1 and 2,
   // philosopher 3 - forks 2 and 3,
   // philosopher 4 - forks 3 and 4

   Object forksLock = new Object();
   boolean[] forks = new boolean[5];

   public DiningPhilosophers() {

   }

   // call the run() method of any runnable to execute its code
   public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
      int left = (philosopher == 0) ? 4 : philosopher - 1;
      int right = philosopher;
      while (true) {
         boolean taken = false;
         synchronized (forksLock) {
            if (!forks[left] && !forks[right]) {
               taken = true;
               forks[left] = true;
               forks[right] = true;
            }
         }
         if (!taken) {
            Thread.sleep(1);
            continue;
         }
         pickLeftFork.run();
         pickRightFork.run();
         eat.run();
         putLeftFork.run();
         putRightFork.run();
         synchronized (forksLock) {
               forks[left] = false;
               forks[right] = false;
         }
         break;
      }
   }
}
 */