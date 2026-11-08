import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class MyTask implements Runnable {
    private int taskId;

    public MyTask(int id) {
        this.taskId = id;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println("Task " + taskId + " is executed by " + threadName);

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            System.out.println("Task interrupted");
        }
    }
}

public class ThreadPoolRunner {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 10; i++) {
            MyTask task = new MyTask(i);
            executor.submit(task);
        }

        executor.shutdown();

        while (!executor.isTerminated()) {

        }

        System.out.println("All tasks finished execution.");
    }
}