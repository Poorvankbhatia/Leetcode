/*
Suppose we have a class:

public class Foo {
  public void first() { print("first"); }
  public void second() { print("second"); }
  public void third() { print("third"); }
}
The same instance of Foo will be passed to three different threads.
Thread A will call first(), thread B will call second(), and thread C will call three().
Design a mechanism and modify the program to ensure that second() is executed after first(), and third() is executed after second().



Example 1:

Input: [1,2,3]
Output: "firstsecondthird"
Explanation: There are three threads being fired asynchronously. The input [1,2,3] means thread A calls first(),
thread B calls second(), and thread C calls third(). "firstsecondthird" is the correct output.
Example 2:

Input: [1,3,2]
Output: "firstsecondthird"
Explanation: The input [1,3,2] means thread A calls first(), thread B calls third(), and thread C calls second(). "firstsecondthird" is the correct output.


Note:

We do not know how the threads will be scheduled in the operating system, even though the numbers in the input seems to imply the ordering.
The input format you see is mainly to ensure our tests' comprehensiveness.
 */
package threads.easy;

public class PrintInOrder {

    class Foo {

        volatile boolean firstDone;
        volatile boolean secondDone;

        public Foo() {
            firstDone = false;
            secondDone = false;
        }

        public synchronized void first(Runnable printFirst) throws InterruptedException {

            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            firstDone = true;
            notifyAll();
        }

        public synchronized void second(Runnable printSecond) throws InterruptedException {
            while(!firstDone) {
                wait();
            }
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            secondDone = true;
            notifyAll();
        }

        public synchronized void third(Runnable printThird) throws InterruptedException {
            while(!secondDone) {
                wait();
            }
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();

        }
    }

}

/*
Do Read : https://www.xyzws.com/Javafaq/why-wait-notify-notifyall-must-be-called-inside-a-synchronized-method-block/127

 */

/*

Semaphore solution :

class Foo {
    Semaphore run2, run3;

    public Foo() {
        run2 = new Semaphore(0);
        run3 = new Semaphore(0);
    }

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        run2.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        run2.acquire();
        printSecond.run();
        run3.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        run3.acquire();
        printThird.run();
    }
}

A semaphore controls access to a shared resource through the use of a counter. If the counter is greater than zero,
then access is allowed. If it is zero, then access is denied. What the counter is counting are permits that allow access to the shared resource.
Thus, to access the resource, a thread must be granted a permit from the semaphore.

Working of semaphore

In general, to use a semaphore, the thread that wants access to the shared resource tries to acquire a permit.

If the semaphore’s count is greater than zero, then the thread acquires a permit, which causes the semaphore’s count to be decremented.
Otherwise, the thread will be blocked until a permit can be acquired.
When the thread no longer needs an access to the shared resource, it releases the permit, which causes the semaphore’s count to be incremented.
If there is another thread waiting for a permit, then that thread will acquire a permit at that time.


Constructors in Semaphore class : There are two constructors in Semaphore class.

Semaphore(int num)
Semaphore(int num, boolean how)
Here, num specifies the initial permit count. Thus, it specifies the number of threads that can access a shared resource at any one time.
If it is one, then only one thread can access the resource at any one time. By default, all waiting threads are granted a permit in an undefined order.
By setting how to true, you can ensure that waiting threads are granted a permit in the order in which they requested access.

Using Semaphores as Locks(preventing race condition)

We can use a semaphore to lock access to a resource, each thread that wants to use that resource must first call acquire( ) before accessing the
resource to acquire the lock. When the thread is done with the resource, it must call release( ) to release lock

 */