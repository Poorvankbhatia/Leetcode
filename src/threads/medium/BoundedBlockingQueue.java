/*

Implement a thread safe bounded blocking queue that has the following methods:

BoundedBlockingQueue(int capacity) The constructor initializes the queue with a maximum capacity.
void enqueue(int element) Adds an element to the front of the queue. If the queue is full, the calling thread is blocked until the queue is no longer full.
int dequeue() Returns the element at the rear of the queue and removes it. If the queue is empty, the calling thread is blocked until the queue is no longer empty.
int size() Returns the number of elements currently in the queue.
Your implementation will be tested using multiple threads at the same time. Each thread will either be a producer thread that only makes calls to the enqueue
method or a consumer thread that only makes calls to the dequeue method. The size method will be called after every test case.

Please do not use built-in implementations of bounded blocking queue as this will not be accepted in an interview.



Example 1:

Input:
1
1
["BoundedBlockingQueue","enqueue","dequeue","dequeue","enqueue","enqueue","enqueue","enqueue","dequeue"]
[[2],[1],[],[],[0],[2],[3],[4],[]]

Output:
[1,0,2,2]

Explanation:
Number of producer threads = 1
Number of consumer threads = 1

BoundedBlockingQueue queue = new BoundedBlockingQueue(2);   // initialize the queue with capacity = 2.

queue.enqueue(1);   // The producer thread enqueues 1 to the queue.
queue.dequeue();    // The consumer thread calls dequeue and returns 1 from the queue.
queue.dequeue();    // Since the queue is empty, the consumer thread is blocked.
queue.enqueue(0);   // The producer thread enqueues 0 to the queue. The consumer thread is unblocked and returns 0 from the queue.
queue.enqueue(2);   // The producer thread enqueues 2 to the queue.
queue.enqueue(3);   // The producer thread enqueues 3 to the queue.
queue.enqueue(4);   // The producer thread is blocked because the queue's capacity (2) is reached.
queue.dequeue();    // The consumer thread returns 2 from the queue. The producer thread is unblocked and enqueues 4 to the queue.
queue.size();       // 2 elements remaining in the queue. size() is always called at the end of each test case.


Example 2:

Input:
3
4
["BoundedBlockingQueue","enqueue","enqueue","enqueue","dequeue","dequeue","dequeue","enqueue"]
[[3],[1],[0],[2],[],[],[],[3]]

Output:
[1,0,2,1]

Explanation:
Number of producer threads = 3
Number of consumer threads = 4

BoundedBlockingQueue queue = new BoundedBlockingQueue(3);   // initialize the queue with capacity = 3.

queue.enqueue(1);   // Producer thread P1 enqueues 1 to the queue.
queue.enqueue(0);   // Producer thread P2 enqueues 0 to the queue.
queue.enqueue(2);   // Producer thread P3 enqueues 2 to the queue.
queue.dequeue();    // Consumer thread C1 calls dequeue.
queue.dequeue();    // Consumer thread C2 calls dequeue.
queue.dequeue();    // Consumer thread C3 calls dequeue.
queue.enqueue(3);   // One of the producer threads enqueues 3 to the queue.
queue.size();       // 1 element remaining in the queue.

Since the number of threads for producer/consumer is greater than 1, we do not know how the threads will be scheduled in th

 */
package threads.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class BoundedBlockingQueue {

    Semaphore pushLock, popLock; // Locks for blocking and unblocking the queue before push and pop
    List<Integer> queue;

    public BoundedBlockingQueue(int capacity) {
        pushLock = new Semaphore(capacity); // Block push when capacity of the queue is full
        popLock = new Semaphore(0); // Block pop when queue is empty
        queue = new ArrayList<>();
    }

    public void enqueue(int element) throws InterruptedException {
        pushLock.acquire();
        queue.add(element);
        popLock.release();
    }

    public int dequeue() throws InterruptedException {
        popLock.acquire();
        int element = queue.remove(0);
        pushLock.release();
        return element;
    }

    public int size() throws InterruptedException {
        return queue.size();
    }
}

/*

Use two locks to block operation to the queue when pushing and poping from queue
Push until capacity is full
Pop only if queue has an element


Another Sol:

ReentrantLock = Voltaile + CAS + Condition
Condition = LinkedNode which contains Waiting Thread
In same ReentrantLock, we can create multiple Conditions
For this question, blockingqueue need 2 waiting list ( full / empty)
When queue is empty, we block dequeue thread, and add thread to empty waiting list
When queue is full, block enqueue thread, add thread to full waiting list

Lock --- --- > empty condition -> blocked Thread1 -> blocked Thread2
|
| --- > full condition -> blocked Thread 3 -> blocked Thread4

Signal = Notify
SignalAll = NotifyAll
Await = Wait

Same as BlockingQueue implmentation in Java source code.

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

class BoundedBlockingQueue {
    private ReentrantLock lock = new ReentrantLock();
    private Condition full = lock.newCondition();
    private Condition empty = lock.newCondition();
    private int[] queue;
    private int tail = 0;
    private int head = 0;
    private int size = 0;
    public BoundedBlockingQueue(int capacity) {
        queue = new int[capacity];
    }

    public void enqueue(int element) throws InterruptedException {
        try {
            lock.lock();
            while(size == queue.length) {
                full.await();
            }
            queue[tail++] = element;
            tail %= queue.length;
            size++;
            empty.signal();
        } finally {
            lock.unlock();
        }
    }

    public int dequeue() throws InterruptedException {
        try {
            lock.lock();
            while(size == 0) {
                empty.await();
            }
            int res = queue[head++];
            head %= queue.length;
            size--;
            full.signal();
            return res;
        } finally {
            lock.unlock();
        }
    }

    public int size() throws InterruptedException {
        lock.lock();
		try {
			return this.size;
		} finally {
			lock.unlock();
		}
    }
}

 */