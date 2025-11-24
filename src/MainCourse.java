import java.util.Scanner;
class MainCourse extends MenuItem {
    public MainCourse(String name, double price) {
        super(name, price);
    }

    @Override
    public void selectVariation(Scanner sc) {
        System.out.println("\nSelect type:");
        if (name.equals("Pasta")) {
            System.out.println("1. Spaghetti\n2. Carbonara");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            variation = (choice == 1) ? "Spaghetti" : "Carbonara";
        } else if (name.equals("Burger")) {
            System.out.println("1. Plain");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            variation = "Plain";
        } else if (name.equals("Fries")) {
            System.out.println("1. Cheese\n2. Sour Cream");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            variation = (choice == 1) ? "Cheese" : "Sour Cream";
        }
    }
}
