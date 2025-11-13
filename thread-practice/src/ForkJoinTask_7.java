
    import java.util.concurrent.*;

    public class ForkJoinTask_7 extends RecursiveTask<Long> {
        private final int start, end;
        private static final int THRESHOLD = 1000; // Small enough?

        ForkJoinTask_7(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if (end - start <= THRESHOLD) {
                // Small task → just calculate
                long sum = 0;
                for (int i = start; i < end; i++) {
                    sum += i;
                }
                return sum;
            } else {
                // Big task → SPLIT!
                int mid = (start + end) / 2;
                ForkJoinTask_7 left  = new ForkJoinTask_7(start, mid);
                ForkJoinTask_7 right = new ForkJoinTask_7(mid, end);

                left.fork();           // Run left in background
                long rightResult = right.compute();  // Run right now
                long leftResult  = left.join();      // Wait for left

                return leftResult + rightResult;
            }
        }
    }


