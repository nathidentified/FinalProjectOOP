import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

class Order {
    private List<OrderItem> items;
    private LocalDateTime timeIn;
    private LocalDateTime timeOut;

    public Order() {
        items = new ArrayList<>();
        timeIn = LocalDateTime.now();
    }

    public void addItem(MenuItem item, int quantity) {
        // Check if item with same variation exists
        for (OrderItem oi : items) {
            if (oi.getItem().getDisplayName().equals(item.getDisplayName())) {
                oi.addQuantity(quantity);
                return;
            }
        }
        items.add(new OrderItem(item, quantity));
    }

    public void displayOrder() {
        if (items.isEmpty()) {
            System.out.println("\nYour order is empty!");
            return;
        }
        System.out.println("\n========== Current Order ==========");
        for (OrderItem oi : items) {
            System.out.printf("%d  %-30s ₱%.2f\n",
                    oi.getQuantity(),
                    oi.getItem().getDisplayName(),
                    oi.getTotalPrice());
        }
        System.out.println("===================================");
    }

    public double getSubtotal() {
        return items.stream().mapToDouble(OrderItem::getTotalPrice).sum();
    }

    public void checkout() {
        timeOut = LocalDateTime.now();

        Duration duration = Duration.between(timeIn, timeOut);
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;

        // Calculate time-based charge (₱50 for every 30 minutes)
        long totalMinutes = duration.toMinutes();
        double timeCharge = Math.floor(totalMinutes / 30.0) * 50;

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");

        System.out.println("\nTime Out: " + timeOut.format(timeFormatter));
        System.out.println("Time Stayed: " + hours + " hour " + minutes + " minutes");

        if (timeCharge > 0) {
            System.out.println("Applying time-based charges...");
            System.out.printf("(Add every after 30 minutes) Extra Charge for long stay: ₱%.0f\n", timeCharge);
        }

        // Print receipt
        System.out.println("\n==========================================");
        System.out.println("               C A F É  J A V A           ");
        System.out.println("                O F F I C I A L           ");
        System.out.println("                 R E C E I P T            ");
        System.out.println("==========================================");
        System.out.println("Time In: " + timeIn.format(timeFormatter));
        System.out.println("Time Out: " + timeOut.format(timeFormatter));
        System.out.println("Duration: " + hours + "h " + minutes + "m");
        System.out.println("------------------------------------------");
        System.out.println("Items Purchased:");
        System.out.println("------------------------------------------");

        for (OrderItem oi : items) {
            System.out.printf("%-2d %-30s ₱%.2f\n",
                    oi.getQuantity(),
                    oi.getItem().getDisplayName(),
                    oi.getTotalPrice());
        }

        System.out.println("------------------------------------------");
        System.out.printf("Subtotal:                       ₱%.2f\n", getSubtotal());
        if (timeCharge > 0) {
            System.out.printf("Time-Based Charge:                       ₱%.0f\n", timeCharge);
        }
        System.out.println("------------------------------------------");
        System.out.printf("TOTAL AMOUNT:                       ₱%.2f\n", getSubtotal() + timeCharge);
        System.out.println("==========================================");
        System.out.println("     THANK YOU FOR DINING WITH US!        ");
        System.out.println("          PLEASE COME AGAIN 🙂            ");
        System.out.println("==========================================");
    }

    public LocalDateTime getTimeIn() { return timeIn; }
}
