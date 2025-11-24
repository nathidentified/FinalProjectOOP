import java.util.Scanner;
import java.util.Map;

class Drink extends MenuItem{

    private String size;
    private static final Map<String, Double> SIZE_PRICES = Map.of(
            "Small", 0.0,
            "Medium", 15.0,
            "Large", 25.0
    );

    public Drink(String name, double basePrice) {
        super(name, basePrice);
    }

    @Override
    public void selectVariation(Scanner sc) {
        // Select size first
        System.out.println("\nSelect size:");
        System.out.println("1. Small (Base Price)");
        System.out.println("2. Medium (+₱15)");
        System.out.println("3. Large (+₱25)");
        System.out.print("Enter choice: ");
        int sizeChoice = sc.nextInt();
        sc.nextLine();

        switch(sizeChoice) {
            case 1: size = "Small"; break;
            case 2: size = "Medium"; price += 15; break;
            case 3: size = "Large"; price += 25; break;
            default: size = "Small";
        }

        // Select flavor
        System.out.println("\nSelect flavor:");
        if (name.equals("Milktea")) {
            System.out.println("1. Salted Caramel\n2. Okinawa\n3. WinterMelon\n4. Matcha");
        } else if (name.equals("Coffee")) {
            System.out.println("1. Ice Caramel\n2. Espresso\n3. Americano\n4. Latte");
        } else if (name.equals("Fruit Soda")) {
            System.out.println("1. Lemon\n2. Strawberry\n3. Lychee\n4. Green Apple");
        }
        System.out.print("Enter choice: ");
        int flavorChoice = sc.nextInt();
        sc.nextLine();

        String[] milkteaFlavors = {"Salted Caramel", "Okinawa", "WinterMelon", "Matcha"};
        String[] coffeeFlavors = {"Ice Caramel", "Espresso", "Americano", "Latte"};
        String[] sodaFlavors = {"Lemon", "Strawberry", "Lychee", "Green Apple"};

        if (name.equals("Milktea")) {
            variation = size + " " + milkteaFlavors[flavorChoice - 1];
        } else if (name.equals("Coffee")) {
            variation = size + " " + coffeeFlavors[flavorChoice - 1];
        } else if (name.equals("Fruit Soda")) {
            variation = size + " " + sodaFlavors[flavorChoice - 1];
        }
    }
}
