/*

Suppose you are given the following code:

class FooBar {
  public void foo() {
    for (int i = 0; i < n; i++) {
      print("foo");
    }
  }

  public void bar() {
    for (int i = 0; i < n; i++) {
      print("bar");
    }
  }
}
The same instance of FooBar will be passed to two different threads.
Thread A will call foo() while thread B will call bar(). Modify the given program to output "foobar" n times.



Example 1:

Input: n = 1
Output: "foobar"
Explanation: There are two threads being fired asynchronously. One of them calls foo(), while the other calls bar(). "foobar" is being output 1 time.
Example 2:

Input: n = 2
Output: "foobarfoobar"
Explanation: "foobar" is being output 2 times.

 */
package threads.medium;

public class PrintFooBarAlternatively {

    class FooBar {
        private int n;

        volatile boolean callFoo;
        volatile boolean callBar;

        public FooBar(int n) {
            this.n = n;
            callFoo = true;
            callBar = false;
        }

        public synchronized void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                while (callBar) {
                    wait();
                }
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                callFoo = false;
                callBar = true;
                notifyAll();
            }
        }

        public synchronized void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                while (callFoo) {
                    wait();
                }
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                callBar = false;
                callFoo = true;
                notifyAll();
            }
        }
    }

}

/*

Semaphore solution:
class FooBar {
    private int n;
    Semaphore s = new Semaphore(0);
    Semaphore s2 = new Semaphore(1);

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            // printFoo.run() outputs "foo". Do not change or remove this line.
            s2.acquire();

            printFoo.run();
            s.release();

        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            // printBar.run() outputs "bar". Do not change or remove this line.
            s.acquire();

            printBar.run();
            s2.release();

        }
    }
}


 */