// Algorithm:
// 1. Create two CountDownLatch objects: latch1 and latch2, each initialized with a count of 1.
// 2. In the first() method, execute the printFirst Runnable, then call latch1.countDown() to signal that first() is complete.
// 3. In the second() method, call latch1.await() to wait until first() has finished. Then execute printSecond and call latch2.countDown().
// 4. In the third() method, call latch2.await() to wait until second() has finished. Then execute printThird.
//
// Pseudo Code:
// -------------
// class Foo:
//     initialize latch1 = new CountDownLatch(1)
//     initialize latch2 = new CountDownLatch(1)
//
//     method first(printFirst):
//         printFirst.run()
//         latch1.countDown()
//
//     method second(printSecond):
//         latch1.await()      // Wait until first() finishes
//         printSecond.run()
//         latch2.countDown()
//
//     method third(printThird):
//         latch2.await()      // Wait until second() finishes
//         printThird.run()
//
// Visualization:
// --------------
// Timeline:
//  Thread A: first()  --> prints "first", then signals latch1.
//  Thread B: second() --> waits on latch1; once latch1 is released, prints "second" and signals latch2.
//  Thread C: third()  --> waits on latch2; once latch2 is released, prints "third".
// This ensures the execution order is: first -> second -> third.

import java.util.concurrent.CountDownLatch;

public class Foo {
    // Two CountDownLatch instances for synchronization:
    // latch1 ensures second() waits for first(), latch2 ensures third() waits for second().
    private CountDownLatch latch1 = new CountDownLatch(1);
    private CountDownLatch latch2 = new CountDownLatch(1);
    
    public Foo() {
        // Constructor: No initialization required beyond CountDownLatch instantiation
    }
    
    // first() method prints "first" and signals that first step is complete.
    public void first(Runnable printFirst) throws InterruptedException {
        // Execute the first print function
        printFirst.run(); // yahan printFirst.run() "first" print karta hai.
        // Signal that first() is done so that second() can proceed.
        latch1.countDown(); // latch1 ko release karte hain.
    }
    
    // second() method waits until first() is done, then prints "second" and signals completion.
    public void second(Runnable printSecond) throws InterruptedException {
        // Wait for first() to finish
        latch1.await(); // yahan wait karte hain jab tak first() complete nahi hota.
        // Execute the second print function
        printSecond.run(); // printSecond.run() "second" print karta hai.
        // Signal that second() is done so that third() can proceed.
        latch2.countDown(); // latch2 ko release karte hain.
    }
    
    // third() method waits until second() is done, then prints "third".
    public void third(Runnable printThird) throws InterruptedException {
        // Wait for second() to finish
        latch2.await(); // yahan wait karte hain jab tak second() complete nahi hota.
        // Execute the third print function
        printThird.run(); // printThird.run() "third" print karta hai.
    }
}
