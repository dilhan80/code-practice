import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class ReadWriteLockDemo_1_5 {
    // The shared data
    private final Map<String, String> cache = new HashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock  = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    // READ: Many threads can call this
    public String get(String key) {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " READING: " + key);
            Thread.sleep(500); // Simulate reading
            return cache.getOrDefault(key, "Not Found");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        } finally {
            readLock.unlock();
        }
    }

    // WRITE: Only one thread at a time
    public void put(String key, String value) {
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " WRITING: " + key + " = " + value);
            Thread.sleep(1000); // Simulate writing
            cache.put(key, value);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            writeLock.unlock();
        }
    }

    // Demo
    public static void main(String[] args) {
        ReadWriteLockDemo_1_5 db = new ReadWriteLockDemo_1_5();
        ExecutorService exec = Executors.newFixedThreadPool(5);

        // 3 readers
        for (int i = 0; i < 3; i++) {
            exec.submit(() -> {
                System.out.println("Result: " + db.get("name"));
            });
        }

        // 1 writer
        exec.submit(() -> db.put("name", "Grok"));

        // Another reader (after write)
        exec.submit(() -> {
            try {
                Thread.sleep(1500);
            } catch (Exception e) {
            }
            System.out.println("Final Result: " + db.get("name"));
        });

        exec.shutdown();
    }
}


