import java.util.Scanner;
class MainCourse extends MenuItem {
    public MainCourse(String name, double price) {
        super(name, price);
    }

    @Override
    public void selectVariation(Scanner sc) {

        boolean loop = true;

        while (loop) {

            System.out.println("\nSelect type:");

            if (name.equals("Pasta")) {
                System.out.println("1. Spaghetti\n2. Carbonara");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine();
                if(choice == 1){
                    variation = "Spaghetti";
                    loop = false;
                } else if (choice == 2) {
                    variation = "Carbonara";
                    loop = false;
                }
                else {
                    System.out.println("Invalid choice. Choose only Spaghetti or Carbonara.");
                }
            } else if (name.equals("Burger")) {
                System.out.println("1. Plain");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine();
                if (choice == 1){
                    variation = "Plain";
                    loop = false;
                }else {
                    System.out.println("Invalid choice. Choose only Spaghetti or Carbonara.");
                }
            } else if (name.equals("Fries")) {
                System.out.println("1. Cheese\n2. Sour Cream");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine();
                if (choice == 1){
                    variation = "Cheese";
                    loop = false;
                } else if (choice == 2) {
                    variation = "Sour Cream";
                    loop = false;
                }
                else {
                    System.out.println("Invalid choice. Choose only Cheese and Sour Cream.");
                }
            }

        }

    }
}

