import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class TicketBookingSystem {
    private static final int TOTAL_SEATS = 10;
    private int availableSeats = TOTAL_SEATS;
    private final Lock lock = new ReentrantLock();

    public void bookTicket(String customerName) {
        lock.lock();
        try {
            if (availableSeats > 0) {
                System.out.println(customerName + " booked a seat. Remaining seats: " + (--availableSeats));
            } else {
                System.out.println(customerName + " tried to book, but no seats are available.");
            }
        } finally {
            lock.unlock();
        }
    }
}

class BookingThread extends Thread {
    private final TicketBookingSystem system;
    private final String customerName;

    public BookingThread(TicketBookingSystem system, String customerName, int priority) {
        this.system = system;
        this.customerName = customerName;
        setPriority(priority);
    }

    @Override
    public void run() {
        system.bookTicket(customerName);
    }
}

public class TicketBookingApp {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem();

        // Creating VIP and regular booking threads
        BookingThread vip1 = new BookingThread(system, "VIP Customer 1", Thread.MAX_PRIORITY);
        BookingThread vip2 = new BookingThread(system, "VIP Customer 2", Thread.MAX_PRIORITY);
        BookingThread regular1 = new BookingThread(system, "Regular Customer 1", Thread.NORM_PRIORITY);
        BookingThread regular2 = new BookingThread(system, "Regular Customer 2", Thread.NORM_PRIORITY);

        // Starting threads
        vip1.start();
        vip2.start();
        regular1.start();
        regular2.start();
    }
}
