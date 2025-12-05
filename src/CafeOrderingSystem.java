import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class CafeOrderingSystem {

    private static Scanner sc = new Scanner(System.in);
    private static Order order;

    public static final String RESET = "\u001B[0m";
    public static final String CYAN = "\u001B[36m";
    public static final String YELLOW = "\u001B[33m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String GREEN = "\u001B[32m";
    public static final String BOLD = "\u001B[1m";

    public static void main(String[] args) {
        order = new Order();

        String title =
                CYAN + BOLD +
                        "        *****     *****     ******    ******      \n" +
                        "       *         *     *    *         *           \n" +
                        "       *         *******    ******    ******      \n" +
                        "       *         *     *    *         *           \n" +
                        "        *****    *     *    *         ******      \n" +
                        RESET;



        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");

        System.out.println(YELLOW + "====================================================" + RESET);
        System.out.println(title);
        System.out.println(MAGENTA + BOLD + "              Welcome to Café Java" + RESET);
        System.out.println(CYAN + BOLD + "                 BY: JavaBrew" + RESET);
        System.out.println(GREEN + "              Time In: " + order.getTimeIn().format(timeFormatter) + RESET);
        System.out.println(YELLOW + "====================================================" + RESET);


        boolean running = true;
        while (running) {

            displayKiosk();
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    viewMenu();
                    break;

                case 2:
                    addItemToOrder();
                    break;

                case 3:
                    order.displayOrder();

                    if (order.isEmpty()) {
                        break;
                    }

                    System.out.println("\nWhat would you like to do?");
                    System.out.println("1. Edit an Item");
                    System.out.println("2. Delete an Item");
                    System.out.println("3. Back");
                    System.out.print("Enter your choice: ");

                    int subChoice = sc.nextInt();
                    sc.nextLine();

                    if (subChoice == 1) {
                        System.out.print("Enter the item name to edit: ");
                        String editName = sc.nextLine();

                        System.out.print("Enter the NEW quantity: ");
                        int newQty = sc.nextInt();
                        sc.nextLine();

                        order.editOrderItem(editName, newQty);

                    } else if (subChoice == 2) {
                        System.out.print("Enter the item name to delete: ");
                        String deleteName = sc.nextLine();

                        order.removeOrderItem(deleteName);

                    } else {
                        System.out.println("Returning to main menu...");
                    }
                    break;

                case 4:
                    order.checkout();
                    order = new Order();
                    System.out.println("\nReady for the next customer!");
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
        System.out.println(CYAN + "\nKiosk:" + RESET);
        System.out.println(MAGENTA + "1. View Menu" + RESET);
        System.out.println(MAGENTA +"2. Add Item to Order"+ RESET);
        System.out.println(MAGENTA +"3. View Current Order"+ RESET);
        System.out.println(MAGENTA +"4. Checkout"+ RESET);
        System.out.println(MAGENTA +"5. Exit"+ RESET);
        System.out.print("Enter your choice: ");
    }

    private static void viewMenu() {
        System.out.println(CYAN + BOLD + "\n=============================== Menu =================================" + RESET);
        System.out.println(MAGENTA + BOLD + "\n-------------------------------DRINKS---------------------------------"+ RESET);
        System.out.println(YELLOW + "Milktea    (Salted Caramel, Okinawa, WinterMelon, Matcha)    - Php 55"+ RESET);
        System.out.println(YELLOW+ "Coffee     (Ice Caramel, Espresso, Americano, Latte)         - Php 60"+ RESET);
        System.out.println(YELLOW+ "Fruit Soda (Lemon, Strawberry, Lychee, Green Apple)          - Php 45"+ RESET);

        System.out.println(MAGENTA +BOLD + "\n------------------------------PASTRIES--------------------------------"+ RESET);
        System.out.println(YELLOW +"Cake       (Chocolate, Red Velvet, Carrot)                   - Php 85"+ RESET);
        System.out.println(YELLOW +"Donut      (Caramel, Chocolate Sprinkles, Red Velvet)        - Php 45"+ RESET);
        System.out.println(YELLOW +"Cupcake    (Chocolate, Red Velvet, Carrot)                   - Php 50"+ RESET);

        System.out.println(MAGENTA + BOLD + "\n----------------------------MAIN COURSE------------------------------"+ RESET);
        System.out.println(YELLOW +"Pasta      (Spaghetti, Carbonara)                            - Php 60"+ RESET);
        System.out.println(YELLOW +"Burger     (Plain)                                           - Php 75"+ RESET);
        System.out.println(YELLOW +"Fries      (Cheese, Sour Cream)                              - Php 50"+ RESET);
        System.out.println(YELLOW +"\n====================================================================="+ RESET);
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
                case 1: item = selectMainCourse();
                    break;

                case 2: item = selectDrink();
                    break;

                case 3: item = selectPastry();
                    break;

                default:
                    System.out.println("Invalid category!");
                    continue;
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
        System.out.println("1. Pasta  - Php 60");
        System.out.println("2. Burger - Php 75");
        System.out.println("3. Fries  - Php 50");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1: return new MainCourse("Pasta", 60 + choice);
            case 2: return new MainCourse("Burger", 75 + choice);
            case 3: return new MainCourse("Fries", 50 + choice);
            default: return null;
        }
    }

    private static MenuItem selectDrink() {
        System.out.println("\nDrinks:");
        System.out.println("1. Milktea    - Php 55");
        System.out.println("2. Coffee     - Php 60");
        System.out.println("3. Fruit Soda - Php 45");
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
        System.out.println("1. Cake    - Php 85");
        System.out.println("2. Donut   - Php 45");
        System.out.println("3. Cupcake - Php 50");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1: return new Pastry("Cake", 85);
            case 2: return new Pastry("Donut", 45);
            case 3: return new Pastry("Cupcake", 50);
            default: return null;
        }
    }
}

