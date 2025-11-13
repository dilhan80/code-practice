public class My_syncronized_task_1_4 implements Runnable{
    private int count = 0;
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            synchronized(this){
                count++;
            }
            System.out.println("Working..." + Thread.currentThread().threadId());
            System.out.println("count : " + count);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hellow Java world."); // 1
        Thread thread2 = new Thread(new My_syncronized_task_1_4());
        thread2.start();
        Thread thread3 = new Thread(new My_syncronized_task_1_4());
        thread3.start();

        Thread.sleep(1000); // main thread sleep
        thread2.interrupt();// thread1 get interrupt while sleeping.
        thread3.interrupt();
    }
}
