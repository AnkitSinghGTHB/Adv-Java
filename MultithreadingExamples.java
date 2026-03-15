/**
 * This class covers common exam programming questions related to
 * Multithreading.
 * 
 * 1. Creating a Thread by extending Thread class.
 * 2. Creating a Thread by implementing Runnable interface.
 * 3. Thread Synchronization.
 */

// === 1. Extending the Thread Class ===
class MyThread extends Thread {
    public void run() {
        for (int i = 1; i <= 3; i++) {
            System.out.println("MyThread (Extends) executing: " + i);
            try {
                Thread.sleep(500); // Pauses for 0.5 seconds
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted.");
            }
        }
    }
}

// === 2. Implementing the Runnable Interface ===
class MyRunnable implements Runnable {
    public void run() {
        for (int i = 1; i <= 3; i++) {
            System.out.println("MyRunnable (Implements) executing: " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted.");
            }
        }
    }
}

// === 3. Thread Synchronization ===
// Shared Resource
class Printer {
    // Synchronized method prevents threads from interfering with each other
    // Try removing 'synchronized' and see how the output gets mixed up!
    synchronized void printDocument(String docName, int copies) {
        for (int i = 1; i <= copies; i++) {
            System.out.println("Printing " + docName + " - Copy " + i);
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

// Thread using the shared Printer resource
class PrintTask extends Thread {
    Printer p;
    String docName;

    PrintTask(Printer p, String docName) {
        this.p = p;
        this.docName = docName;
    }

    public void run() {
        p.printDocument(docName, 3);
    }
}

// === 4. Thread Priority ===
class PriorityThread extends Thread {
    public void run() {
        System.out.println("PriorityThread [" + getName() + "] executing with priority: " + getPriority());
    }
}

public class MultithreadingExamples {
    public static void main(String[] args) {

        System.out.println("=== Demonstrating Thread Creation ===");

        // Starting Thread subclass
        MyThread t1 = new MyThread();
        t1.start(); // Always call start(), not run()

        // Starting Runnable implementation
        MyRunnable runObj = new MyRunnable();
        Thread t2 = new Thread(runObj);
        t2.start();

        // Wait for t1 and t2 to finish before moving to synchronization example
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n=== Demonstrating Thread Priorities ===");

        PriorityThread p1 = new PriorityThread();
        PriorityThread p2 = new PriorityThread();
        PriorityThread p3 = new PriorityThread();

        p1.setName("Low Priority Thread");
        p2.setName("Normal Priority Thread");
        p3.setName("High Priority Thread");

        p1.setPriority(Thread.MIN_PRIORITY); // Priority 1
        p2.setPriority(Thread.NORM_PRIORITY); // Priority 5
        p3.setPriority(Thread.MAX_PRIORITY); // Priority 10

        // Note: Exact execution order is determined by the Thread Scheduler,
        // but higher priority threads get preference.
        p1.start();
        p2.start();
        p3.start();

        // Wait for priority threads to finish
        try {
            p1.join();
            p2.join();
            p3.join();
        } catch (InterruptedException e) {
        }

        System.out.println("\n=== Demonstrating Thread Synchronization ===");

        // Create ONE shared Printer object
        Printer sharedPrinter = new Printer();

        // Two threads share the SAME printer
        PrintTask task1 = new PrintTask(sharedPrinter, "Resume.pdf");
        PrintTask task2 = new PrintTask(sharedPrinter, "ExamHallTicket.pdf");

        // Because the printDocument method is synchronized,
        // task2 will wait until task1 finishes printing its copies.
        task1.start();
        task2.start();
    }
}

/*
 * EXPECTED OUTPUT:
 * === Demonstrating Thread Creation ===
 * MyThread (Extends) executing: 1
 * MyRunnable (Implements) executing: 1
 * MyThread (Extends) executing: 2
 * MyRunnable (Implements) executing: 2
 * MyThread (Extends) executing: 3
 * MyRunnable (Implements) executing: 3
 * 
 * === Demonstrating Thread Priorities ===
 * PriorityThread [High Priority Thread] executing with priority: 10
 * PriorityThread [Normal Priority Thread] executing with priority: 5
 * PriorityThread [Low Priority Thread] executing with priority: 1
 * 
 * === Demonstrating Thread Synchronization ===
 * Printing Resume.pdf - Copy 1
 * Printing Resume.pdf - Copy 2
 * Printing Resume.pdf - Copy 3
 * Printing ExamHallTicket.pdf - Copy 1
 * Printing ExamHallTicket.pdf - Copy 2
 * Printing ExamHallTicket.pdf - Copy 3
 * 
 * EXAM EXPLANATION:
 * 1. Two thread creation methods are shown. `Runnable` is passed into a
 * `Thread` constructor.
 * 2. We use `join()` to force the main thread to wait for threads to finish
 * before continuing.
 * 3. Priorities (1 to 10) hint the Thread Scheduler which thread to prefer, but
 * execution order is not guaranteed.
 * 4. In the synchronization example, `sharedPrinter` is accessed by both
 * `task1` and `task2`.
 * The `synchronized` keyword ensures that only one thread can execute
 * `printDocument` at a time.
 */
