class Buffer
{
    private final int[] items = new int[3];
    private int count = 0, in = 0, out = 0;

    synchronized void put(int value) throws InterruptedException
    {
        while(count == items.length){
            wait();
        }
        items[in] = value;
        in = (in + 1) % items.length;
        count++;
        notifyAll();
    }

    synchronized int take() throws InterruptedException
    {
        while(count == 0){
            wait();
        }
        int value = items[out];
        out = (out + 1) % items.length;
        count--;
        notifyAll();
        return value;
    }
}

public class ProducerConsumer {
    public static void main(String[] args) throws InterruptedException
    {
        final int ITEMS = 10;
        Buffer buffer = new Buffer();

        Thread producer = new Thread(() -> {
            try{
                for(int i=1; i<=ITEMS; i++){
                    buffer.put(i);
                    System.out.println("Produced: " + i);
                    Thread.sleep(50);
                }
            }
            catch(InterruptedException e){
                System.out.println("Producer interrupted");
            }
        }, "Producer");

        Thread consumer = new Thread(() -> {
            try{
                for(int i=1; i<=ITEMS; i++){
                    int v = buffer.take();
                    System.out.println("Consumed: " + v + (v == i ? "" : " <-- OUT OF ORDER"));
                    Thread.sleep(120);
                }
            }
            catch(InterruptedException e){
                System.out.println("Consumer interrupted");
            }
        }, "Consumer");

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        System.out.println("All " + ITEMS + " items produced and consumed.");
    }
}