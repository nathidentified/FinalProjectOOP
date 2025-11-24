import java.util.Scanner;
class Pastry extends MenuItem {
    public Pastry(String name, double price) {
        super(name, price);
    }

    @Override
    public void selectVariation(Scanner sc) {
        System.out.println("\nSelect flavor:");
        if (name.equals("Cake") || name.equals("Cupcake")) {
            System.out.println("1. Chocolate\n2. Red Velvet\n3. Carrot");
        } else if (name.equals("Donut")) {
            System.out.println("1. Caramel\n2. Chocolate Sprinkles\n3. Red Velvet");
        }
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        if (name.equals("Cake") || name.equals("Cupcake")) {
            String[] flavors = {"Chocolate", "Red Velvet", "Carrot"};
            variation = flavors[choice - 1];
        } else if (name.equals("Donut")) {
            String[] flavors = {"Caramel", "Chocolate Sprinkles", "Red Velvet"};
            variation = flavors[choice - 1];
        }
    }
}

