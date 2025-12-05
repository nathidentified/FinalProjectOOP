import java.util.Scanner;

abstract class MenuItem {

    protected String name;
    protected double price;
    protected String variation;

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public abstract void selectVariation(Scanner sc);

    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public String getVariation() {
        return variation;
    }

    public String getDisplayName() {
        return variation != null ? name + " (" + variation + ")" : name;
    }
}

