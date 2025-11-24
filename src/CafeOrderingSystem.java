import java.util.Scanner;
import java.time.*;
import java.time.format.DateTimeFormatter;
public class CafeOrderingSystem {

    private static Scanner sc = new Scanner(System.in);
    private static Order order;

    public static void main(String[] args) {
        order = new Order();


        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        System.out.println("=== Welcome to Café Java ===");
        System.out.println("Time In: " + order.getTimeIn().format(timeFormatter));

        boolean running = true;
        while (running) {
            displayKiosk();
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: viewMenu(); break;
                case 2: addItemToOrder(); break;
                case 3: order.displayOrder(); break;
                case 4:
                    order.checkout();
                    order = new Order();
                    System.out.println("\nReady for the next customer!");
                    //running = false;
                    break;
                case 5:
                    System.out.println("Thank you for visiting Café Java!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void displayKiosk() {
        System.out.println("\nKiosk:");
        System.out.println("1. View Menu");
        System.out.println("2. Add Item to Order");
        System.out.println("3. View Current Order");
        System.out.println("4. Checkout");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void viewMenu() {
        System.out.println("\n========== Menu ==========");
        System.out.println("\nDRINKS");
        System.out.println("Milktea (Salted Caramel, Okinawa, WinterMelon, Matcha) - ₱55");
        System.out.println("Coffee (Ice Caramel, Espresso, Americano, Latte) - ₱60");
        System.out.println("Fruit Soda (Lemon, Strawberry, Lychee, Green Apple) - ₱45");

        System.out.println("\nPASTRIES");
        System.out.println("Cake (Chocolate, Red Velvet, Carrot) - ₱85");
        System.out.println("Donut (Caramel, Chocolate Sprinkles, Red Velvet) - ₱45");
        System.out.println("Cupcake (Chocolate, Red Velvet, Carrot) - ₱50");

        System.out.println("\nMAIN COURSE");
        System.out.println("Pasta (Spaghetti, Carbonara) - ₱60");
        System.out.println("Burger (Plain) - ₱75");
        System.out.println("Fries (Cheese, Sour Cream) - ₱50");
        System.out.println("==========================");
    }

    private static void addItemToOrder() {
        boolean addingItems = true;

        while (addingItems) {
            System.out.println("\nChoose Category:");
            System.out.println("1. Main Course");
            System.out.println("2. Drinks");
            System.out.println("3. Pastries");
            System.out.print("Enter choice: ");
            int category = sc.nextInt();
            sc.nextLine();

            MenuItem item = null;

            switch (category) {
                case 1: item = selectMainCourse(); break;
                case 2: item = selectDrink(); break;
                case 3: item = selectPastry(); break;
                default: System.out.println("Invalid category!"); continue;
            }

            if (item != null) {
                item.selectVariation(sc);
                System.out.print("Enter quantity: ");
                int qty = sc.nextInt();
                sc.nextLine();
                order.addItem(item, qty);
                System.out.println("Item added to order!");
            }

            System.out.print("\nWould you like to add more items? (Y/N): ");
            String more = sc.nextLine();
            if (more.equalsIgnoreCase("N")) {
                addingItems = false;
            }
        }
    }

    private static MenuItem selectMainCourse() {
        System.out.println("\nMain Course:");
        System.out.println("1. Pasta - ₱60");
        System.out.println("2. Burger - ₱75");
        System.out.println("3. Fries - ₱50");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1: return new MainCourse("Pasta", 60);
            case 2: return new MainCourse("Burger", 75);
            case 3: return new MainCourse("Fries", 50);
            default: return null;
        }
    }

    private static MenuItem selectDrink() {
        System.out.println("\nDrinks:");
        System.out.println("1. Milktea - ₱55");
        System.out.println("2. Coffee - ₱60");
        System.out.println("3. Fruit Soda - ₱45");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1: return new Drink("Milktea", 55);
            case 2: return new Drink("Coffee", 60);
            case 3: return new Drink("Fruit Soda", 45);
            default: return null;
        }
    }

    private static MenuItem selectPastry() {
            System.out.println("\nPastries:");
            System.out.println("1. Cake - ₱85");
            System.out.println("2. Donut - ₱45");
            System.out.println("3. Cupcake - ₱50");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    return new Pastry("Cake", 85);
                case 2:
                    return new Pastry("Donut", 45);
                case 3:
                    return new Pastry("Cupcake", 50);
                default:
                    return null;
            }

    }
}
