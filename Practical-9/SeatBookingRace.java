class Seats
{
    private int seatsLeft = 5;

    boolean book()
    {
        if(seatsLeft > 0){
            try{ Thread.sleep(10); } catch(InterruptedException e){}
            seatsLeft--;
            return true;
        }
        return false;
    }

    synchronized boolean safeBook()
    {
        if(seatsLeft > 0){
            try{ Thread.sleep(10); } catch(InterruptedException e){}
            seatsLeft--;
            return true;
        }
        return false;
    }

    int getSeatsLeft(){ return seatsLeft; }
}

public class SeatBookingRace {

    static void run(boolean safe) throws InterruptedException
    {
        final int THREADS = 10;

        Seats seats = new Seats();
        java.util.concurrent.atomic.AtomicInteger success = new java.util.concurrent.atomic.AtomicInteger();
        Thread[] t = new Thread[THREADS];

        for(int i=0; i<THREADS; i++){
            t[i] = new Thread(() -> {
                boolean booked = safe ? seats.safeBook() : seats.book();
                if(booked){
                    success.incrementAndGet();
                }
            });
            t[i].start();
        }

        for(int i=0; i<THREADS; i++){
            t[i].join();
        }

        System.out.println((safe ? "Synchronized" : "Unsynchronized") + " -> Booked: " + success.get() + " (only 5 seats) Seats left: " + seats.getSeatsLeft());
    }

    public static void main(String[] args) throws InterruptedException
    {
        run(false);
        run(true);
    }
}