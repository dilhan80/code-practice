public class My_Interrupted_Task_1_4 implements Runnable{

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hellow Java world."); // 1

        /** Interupted example **/
        Thread thread1 = new Thread(new My_Interrupted_Task_1_4());
        thread1.start();
        Thread.sleep(700); // main thread sleep
        thread1.interrupt();// thread1 get interrupt while sleeping.
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Working...");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println("Interrupted during sleep by main thread after 700 millies");
                Thread.currentThread().interrupt(); // set the interrupted flag again for gracefully exit
            }
        }
        System.out.println("Thread exiting cleanly.");
    }
}
