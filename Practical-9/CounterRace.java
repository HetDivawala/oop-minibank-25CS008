class Counter
{
    int count = 0;

    void increment(){
        int temp = count;
        Thread.yield();
        count = temp + 1;
    }

    synchronized void safeIncrement(){ count++; }
}

class CounterTask implements Runnable
{
    private Counter counter;
    private int times;
    private boolean safe;

    CounterTask(Counter counter, int times, boolean safe){
        this.counter = counter;
        this.times = times;
        this.safe = safe;
    }

    @Override
    public void run(){
        for(int i=0; i<times; i++){
            if(safe){
                counter.safeIncrement();
            }
            else{
                counter.increment();
            }
        }
    }
}

public class CounterRace {

    static void run(boolean safe) throws InterruptedException
    {
        final int THREADS = 10;
        final int TIMES = 100000;

        Counter counter = new Counter();
        Thread[] t = new Thread[THREADS];

        for(int i=0; i<THREADS; i++){
            t[i] = new Thread(new CounterTask(counter, TIMES, safe));
            t[i].start();
        }

        for(int i=0; i<THREADS; i++){
            t[i].join();
        }

        System.out.println((safe ? "Synchronized" : "Unsynchronized") + " -> Expected: " + (THREADS*TIMES) + " Actual: " + counter.count);
    }

    public static void main(String[] args) throws InterruptedException
    {
        run(false);
        run(true);
    }
}