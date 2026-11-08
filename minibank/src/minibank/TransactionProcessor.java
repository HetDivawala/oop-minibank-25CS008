package minibank;
import java.util.concurrent.*;
public class TransactionProcessor {
    private ExecutorService pool=Executors.newFixedThreadPool(4);
    public void submit(Runnable task){pool.execute(task);}
    public void stop(){pool.shutdown(); try{pool.awaitTermination(1,TimeUnit.MINUTES);}catch(InterruptedException e){Thread.currentThread().interrupt();}}
}
