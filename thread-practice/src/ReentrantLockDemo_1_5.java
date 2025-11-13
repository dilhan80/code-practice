
    import java.util.concurrent.locks.*;

    public class ReentrantLockDemo_1_5 {
        private final ReentrantLock lock = new ReentrantLock(true); // true = FAIR
        private final Condition notEmpty = lock.newCondition();
        private int balance = 0;

        public static void main(String[] args) {
            ReentrantLockDemo_1_5 bank = new ReentrantLockDemo_1_5();

            // 1. tryLock() Demo
            //bank.demoTryLock();

            // 2. Fairness Demo
            //bank.demoFairness();

            // 3. Interruptible Lock
            //bank.demoInterruptibleLock();

            // 4. Condition Variables (Producer-Consumer)
            bank.demoCondition();
        }

        // 1. tryLock() - Try without waiting
        void demoTryLock() {
            System.out.println("\n--- 1. tryLock() Demo ---");

            new Thread(() -> {
                lock.lock();
                try {
                    System.out.println("Thread 1: Got lock, working 3 sec...");
                    Thread.sleep(3000);
                } catch (Exception e) {} finally {
                    lock.unlock();
                }
            }).start();

            // Wait a bit
            sleep(500);

            boolean gotLock = lock.tryLock();
            if (gotLock) {
                System.out.println("Thread 2: Got lock immediately!");
                lock.unlock();
            } else {
                System.out.println("Thread 2: tryLock() FAILED - lock is busy");
            }
        }

        // 2. Fairness - First come, first served
        void demoFairness() {
            System.out.println("\n--- 2. Fairness Demo (FAIR LOCK) ---");

            ReentrantLock fairLock = new ReentrantLock(true); // FAIR

            Runnable task = () -> {
                for (int i = 0; i < 3; i++) {
                    fairLock.lock();
                    try {
                        System.out.println(Thread.currentThread().getName() + " got lock");
                        sleep(200);
                    } finally {
                        fairLock.unlock();
                    }
                }
            };

            Thread t1 = new Thread(task, "Worker-A");
            Thread t2 = new Thread(task, "Worker-B");
            Thread t3 = new Thread(task, "Worker-C");

            t1.start(); t2.start(); t3.start();
        }

        // 3. Interruptible Lock - Can be interrupted while waiting
        void demoInterruptibleLock() {
            System.out.println("\n--- 3. Interruptible Lock Demo ---");

            lock.lock();
            System.out.println("Main: Holding lock for 5 sec...");

            Thread waiter = new Thread(() -> {
                try {
                    System.out.println("Waiter: Trying to get lock (interruptible)...");
                    lock.lockInterruptibly(); // Can be interrupted!
                    System.out.println("Waiter: Got lock!");
                    lock.unlock();
                } catch (InterruptedException e) {
                    System.out.println("Waiter: INTERRUPTED while waiting!");
                }
            });

            waiter.start();
            sleep(1000);
            waiter.interrupt(); // Interrupt after 1 sec
            sleep(1000);
            lock.unlock(); // Release main lock
        }

        // 4. Condition Variables - Producer/Consumer
        void demoCondition() {
            System.out.println("\n--- 4. Condition Variables (Producer-Consumer) ---");

            Thread producer = new Thread(() -> {
                lock.lock();
                try {
                    System.out.println("Producer: Depositing 100...");
                    balance = 100;
                    notEmpty.signal(); // Wake up consumer
                } finally {
                    lock.unlock();
                }
            });

            Thread consumer = new Thread(() -> {
                lock.lock();
                try {
                    while (balance == 0) {
                        System.out.println("Consumer: No money, waiting...");
                        notEmpty.await(); // Wait for signal
                    }
                    System.out.println("Consumer: Withdrew " + balance);
                    balance = 0;
                } catch (InterruptedException e) {} finally {
                    lock.unlock();
                }
            });

            consumer.start();
            sleep(500);
            producer.start();
        }

        static void sleep(long ms) {
            try { Thread.sleep(ms); } catch (Exception e) {}
        }
    }

