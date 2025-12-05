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
        System.out.println("\n================= Current Order =================");
        for (OrderItem oi : items) {
            System.out.printf("%-4d  %-30s Php %.2f\n",
                    oi.getQuantity(),
                    oi.getItem().getDisplayName(),
                    oi.getTotalPrice());
        }
        System.out.println("==================================================");
    }

    public double getSubtotal() {
        return items.stream().mapToDouble(OrderItem::getTotalPrice).sum();
    }

    public void checkout() {
        timeOut = LocalDateTime.now();

        Duration duration = Duration.between(timeIn, timeOut);
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;

        long totalMinutes = duration.toMinutes();
        double timeCharge = Math.floor(totalMinutes / 30.0) * 50;

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");

        System.out.println("\nTime Out: " + timeOut.format(timeFormatter));
        System.out.println("Time Stayed: " + hours + " hour " + minutes + " minutes");

        if (timeCharge > 0) {
            System.out.println("Applying time-based charges...");
            System.out.printf("(Add every after 30 minutes) Extra Charge for long stay: ₱%.0f\n", timeCharge);
        }

        System.out.println("\n==================================================");
        System.out.println("               C A F É  J A V A           ");
        System.out.println("                O F F I C I A L           ");
        System.out.println("                 R E C E I P T            ");
        System.out.println("===================================================");
        System.out.println("Time In: " + timeIn.format(timeFormatter));
        System.out.println("Time Out: " + timeOut.format(timeFormatter));
        System.out.println("Duration: " + hours + "h " + minutes + "m");
        System.out.println("---------------------------------------------------");
        System.out.println("Qty  Item                               Price");
        System.out.println("---------------------------------------------------");

        for (OrderItem oi : items) {
            System.out.printf("%-4d %-30s %7s%.2f\n",
                    oi.getQuantity(),
                    oi.getItem().getDisplayName(),
                    "Php ",
                    oi.getTotalPrice());
        }

        System.out.println("----------------------------------------------------");
        System.out.printf("%-34s %7s%.2f\n", "Subtotal:", "Php ", getSubtotal());

        if (timeCharge > 0) {
            System.out.printf("%-34s %7s%.0f\n", "Time-Based Charge:", "Php ", timeCharge);
        }

        System.out.println("----------------------------------------------------");
        System.out.printf("%-34s %7s%.2f\n", "TOTAL AMOUNT:", "Php ", getSubtotal() + timeCharge);
        System.out.println("====================================================");
        System.out.println("            THANK YOU FOR DINING WITH US!        ");
        System.out.println("                PLEASE COME AGAIN :)            ");
        System.out.println("====================================================");
    }

    public LocalDateTime getTimeIn() {
        return timeIn;
    }

    public void editOrderItem(String editName, int newQty) {
        for (OrderItem oi : items) {
            if (oi.getItem().getDisplayName().equalsIgnoreCase(editName)) {

                if (newQty <= 0) {
                    System.out.println("Quantity must be at least 1.");
                    return;
                }

                oi.addQuantity(newQty);
                System.out.println("Item updated successfully!");
                return;
            }
        }
        System.out.println("Item not found in order.");
    }

    public void removeOrderItem(String deleteName) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getItem().getDisplayName().equalsIgnoreCase(deleteName)) {
                items.remove(i);
                System.out.println("Item removed successfully!");
                return;
            }
        }
        System.out.println("Item not found in order.");
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}

    public LocalDateTime getTimeIn() { return timeIn; }
}

