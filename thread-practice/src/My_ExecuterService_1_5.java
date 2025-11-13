import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class My_ExecuterService_1_5 {

    public static void main(String[] args) {
        // 1
        testFixedThreadPool();
        // 2
        testSingleThreadExecutor();
        // 3
        testReturnValueFromExecuterService();
    }

    public static void testFixedThreadPool() {
        // 2 cooks in the kitchen
        ExecutorService kitchen = Executors.newFixedThreadPool(2);

        // Submit 5 orders
        for (int i = 1; i <= 5; i++) {
            int taskId = i;
            kitchen.submit(() -> {
                System.out.println("Cooking order " + taskId + " by " + Thread.currentThread().getName());
                try { Thread.sleep(1000); } catch (Exception e) {}
            });
        }
        kitchen.shutdown(); // Stop accepting new tasks
    }


    public static void testSingleThreadExecutor() {
        ExecutorService single = Executors.newSingleThreadExecutor();
        single.submit(() -> System.out.println("First"));
        single.submit(() -> System.out.println("Second"));
        single.submit(() -> System.out.println("Third"));
        single.shutdown();
    }

    public static void testReturnValueFromExecuterService() {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Future<Integer> future = executor.submit(() -> {
            return 2 + 3;
        });

        try {
            int result = future.get(); // Waits for answer
            System.out.println("Result: " + result);
        } catch (Exception e) {}
        executor.shutdown();
    }

}
