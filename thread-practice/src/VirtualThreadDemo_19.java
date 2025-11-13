// File: VirtualThreadDemo.java
public class VirtualThreadDemo_19 {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting 5 virtual threads...\n");

        // Start 5 virtual threads using Thread.startVirtualThread()
        for (int i = 1; i <= 5; i++) {
            int taskId = i;

            Thread.startVirtualThread(() -> {
                System.out.println("Task " + taskId + " STARTED on "
                        + Thread.currentThread());

                // Simulate work (blocking is OK!)
                try {
                    Thread.sleep(1000);  // 1 second
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                System.out.println("Task " + taskId + " FINISHED");
            });
        }

        System.out.println("\nAll tasks launched! Main continues...\n");

        // Wait a bit so we can see output
        Thread.sleep(2000);

        System.out.println("Main thread done!");
    }
}