/*

There are two kinds of threads, oxygen and hydrogen. Your goal is to group these threads to form water molecules.
There is a barrier where each thread has to wait until a complete molecule can be formed. Hydrogen and oxygen threads
will be given a releaseHydrogen and releaseOxygen method respectfully, which will allow them to pass the barrier.
These threads should pass the barrier in groups of three, and they must be able to immediately bond with each other
to form a water molecule. You must guarantee that all the threads from one molecule bond before any other threads from the next molecule do.

In other words:

If an oxygen thread arrives at the barrier when no hydrogen threads are present, it has to wait for two hydrogen threads.
If a hydrogen thread arrives at the barrier when no other threads are present, it has to wait for an oxygen thread and another hydrogen thread.
Write synchronization code for oxygen and hydrogen molecules that enforces these constraints.



Example 1:

Input: "HOH"
Output: "HHO"
Explanation: "HOH" and "OHH" are also valid answers.
Example 2:

Input: "OOHHHH"
Output: "HHOHHO"
Explanation: "HOHHHO", "OHHHHO", "HHOHOH", "HOHHOH", "OHHHOH", "HHOOHH", "HOHOHH" and "OHHOHH" are also valid answers.


Constraints:

Total length of input string will be 3n, where 1 ≤ n ≤ 30.
Total number of H will be 2n in the input string.
Total number of O will be n in the input string.

 */
package threads.hard;

import java.util.concurrent.Semaphore;

public class BuildingH2O {

    class H2O {
        Semaphore outputting;
        Semaphore outputHydrogen, outputOxygen;
        public H2O() {
            outputting = new Semaphore(1);
            outputHydrogen = new Semaphore(0);
            outputOxygen = new Semaphore(0);
        }

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
            //we only "output" when oxygen says so
            outputHydrogen.acquire();
            releaseHydrogen.run();
            outputOxygen.release();
        }

        public void oxygen(Runnable releaseOxygen) throws InterruptedException {
            //we only output 1 complete set at a time.
            outputting.acquire();

            outputHydrogen.release(2);
            releaseOxygen.run();
            //we wait for 2 hydrogens to finish printing
            outputOxygen.acquire(2);

            outputting.release();
        }
    }

}
