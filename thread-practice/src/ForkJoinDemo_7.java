import java.util.concurrent.ForkJoinPool;

public class ForkJoinDemo_7 {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        long result = pool.invoke(new ForkJoinTask_7(1, 1_000_001));
        System.out.println("Total sum = " + result); // 500000500000
    }
}
