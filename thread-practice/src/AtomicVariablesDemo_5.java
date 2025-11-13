import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicVariablesDemo_5 {
    public static void main(String[] args) throws Exception {
        AtomicInteger count = new AtomicInteger(0);

        // 1000 threads incrementing
        ExecutorService pool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            pool.submit(() -> {
                count.incrementAndGet();
                System.out.println(Thread.currentThread().getName() + " → " + count.get());
            });
        }

        pool.shutdown();
        pool.awaitTermination(10, TimeUnit.SECONDS);

        System.out.println("Final count: " + count.get()); // Always 1000!
    }
}
