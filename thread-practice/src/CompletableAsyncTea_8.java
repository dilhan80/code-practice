import java.sql.SQLOutput;
import java.util.concurrent.*;

public class CompletableAsyncTea_8 {
    public static void main(String[] args) {
        System.out.println("1. Ordering tea...");

        CompletableFuture<String> teaFuture = CompletableFuture
                .supplyAsync(() -> {
                    System.out.println("  Boiling water...");
                    sleep(2000);
                    return "Hot Tea";
                })
                .thenApply(tea -> {
                    System.out.println("adding Milk");
                    return tea + " + Milk";
                })
                .thenApply(tea -> {
                    System.out.println("adding Sugar");
                    return tea + " + Sugar";
                });

        CompletableFuture<String> biscuitFuture = CompletableFuture
                .supplyAsync(() -> {
                    System.out.println("  Baking biscuits...");
                    sleep(1000);
                    return "Crispy Biscuits";
                });

        // Wait for both → have breakfast!
        CompletableFuture<Void> breakfast = teaFuture.thenCombine(biscuitFuture,
                (tea, biscuit) -> {
                    System.out.println("Breakfast: " + tea + " & " + biscuit);
                    return null;
                });

        breakfast.join(); // Wait for breakfast (optional)

        System.out.println("2. Done!");
    }

    static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (Exception e) {}
    }
}
