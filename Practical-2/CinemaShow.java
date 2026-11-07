public class CinemaShow {

    private int seatsAvailable;
    private final int capacity;
    private String title;
    private static int totalBooked = 0;

    CinemaShow(String title, int capacity) {
        this.title = title;
        this.capacity = capacity;
        this.seatsAvailable = capacity;
    }

    CinemaShow(String title) {
        this(title, 100);
    }

    boolean book(int n) {
        if (n <= seatsAvailable) {
            seatsAvailable -= n;
            totalBooked += n;
            return true;
        }
        else {
            return false;
        }
    }

    void cancel(int n) {
        if (seatsAvailable + n > capacity) {
            System.out.println("Seats Available can't exceed capacity!");
        }
        else {
            seatsAvailable += n;
        }
    }

    int getSeatsAvailable() {
        return seatsAvailable;
    }

    static int getTotalBooked() {
        return totalBooked;
    }

    public static void main(String[] args) {

        CinemaShow show = new CinemaShow("movie", 120);

        System.out.println("Seats Available: " + show.getSeatsAvailable());
        System.out.println("Total Booked: " + CinemaShow.getTotalBooked());

        System.out.println("Trying to book 125 seats...");
        System.out.println(show.book(125));

        System.out.println("Seats Available: " + show.getSeatsAvailable());
        System.out.println("Total Booked: " + CinemaShow.getTotalBooked());

        System.out.println("Trying to book 15 seats...");
        System.out.println(show.book(15));

        System.out.println("Seats Available: " + show.getSeatsAvailable());
        System.out.println("Total Booked: " + CinemaShow.getTotalBooked());

        System.out.println("Trying to cancel 20 seats...");
        show.cancel(20);

        System.out.println("Seats Available: " + show.getSeatsAvailable());
        System.out.println("Total Booked: " + CinemaShow.getTotalBooked());

        System.out.println("Trying to cancel 5 seats...");
        show.cancel(5);

        System.out.println("Seats Available: " + show.getSeatsAvailable());
        System.out.println("Total Booked: " + CinemaShow.getTotalBooked());
    }
}