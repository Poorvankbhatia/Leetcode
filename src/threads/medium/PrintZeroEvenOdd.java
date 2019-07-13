/*

Suppose you are given the following code:

class ZeroEvenOdd {
  public ZeroEvenOdd(int n) { ... }      // constructor
  public void zero(printNumber) { ... }  // only output 0's
  public void even(printNumber) { ... }  // only output even numbers
  public void odd(printNumber) { ... }   // only output odd numbers
}
The same instance of ZeroEvenOdd will be passed to three different threads:

Thread A will call zero() which should only output 0's.
Thread B will call even() which should only ouput even numbers.
Thread C will call odd() which should only output odd numbers.
Each of the thread is given a printNumber method to output an integer. Modify the given program to output the series
 010203040506... where the length of the series must be 2n.



Example 1:

Input: n = 2
Output: "0102"
Explanation: There are three threads being fired asynchronously. One of them calls zero(), the other calls even(),
and the last one calls odd(). "0102" is the correct output.
Example 2:

Input: n = 5
Output: "0102030405"

 */
package threads.medium;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class PrintZeroEvenOdd {
    class ZeroEvenOdd {
        int n;

        Semaphore semaphoreEven, semaphoreOdd, semaphoreZero;

        public ZeroEvenOdd(int n) {
            this.n = n;

            semaphoreZero = new Semaphore(1);
            semaphoreEven = new Semaphore(0);
            semaphoreOdd = new Semaphore(0);
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero(IntConsumer printNumber) throws InterruptedException {
            int numTimes = this.n;
            boolean printOdd = true;

            for (int i = 0; i < numTimes; i++) {
                semaphoreZero.acquire();

                printNumber.accept(0);

                //print the next number
                if (printOdd)
                    semaphoreOdd.release();
                else
                    semaphoreEven.release();

                printOdd = !printOdd;
            }
        }

        public void even(IntConsumer printNumber) throws InterruptedException {
            int numTimes = this.n / 2;

            int nextEvenNum = 2;
            for (int i = 0; i < numTimes; i++) {
                semaphoreEven.acquire();

                printNumber.accept(nextEvenNum);
                nextEvenNum += 2;

                semaphoreZero.release();
            }
        }

        public void odd(IntConsumer printNumber) throws InterruptedException {
            int numTimes = (int) Math.ceil((double) this.n / 2);

            int nextOdd = 1;
            for (int i = 0; i < numTimes; i++) {
                semaphoreOdd.acquire();

                printNumber.accept(nextOdd);
                nextOdd += 2;

                semaphoreZero.release();
            }
        }
    }
}
