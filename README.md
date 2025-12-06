# Café Java Ordering System

A command-line interface (CLI) application designed to streamline café ordering operations with real-time order management, time-based billing, and comprehensive receipt generation.

## Table of Contents

- [Features](#-features)
- [OOP Concepts Applied](#oop-concepts-applied)
- [Program Structure](#program-structure)
- [Usage Guide](#-usage-guide)
- [Sample Output](#sample-output)
- [Authors](#-authors)
- [License](#-license)

---

## ✨ Features

### 🍽️ Menu Management
- **Three Menu Categories**:
  - **Main Course**: Pasta (Spaghetti, Carbonara), Burger (Plain), Fries (Cheese, Sour Cream)
  - **Drinks**: Milktea, Coffee, Fruit Soda with multiple flavor options and size variations (Small, Medium, Large)
  - **Pastries**: Cake, Donut, Cupcake with various flavor options
- **Dynamic Pricing**: Drink prices adjust based on size selection (Medium +Php 15, Large +Php 25)
- **Variation Selection**: Each item type has specific customization options

### 📋 Order Management
- **View Menu**: Display all available items organized by category with prices
- **Add Items**: Select items from any category, choose variations, and specify quantity
- **View Current Order**: Display formatted order summary with quantities and prices
- **Edit Items**: Modify quantities of existing items in the order
- **Delete Items**: Remove items from the current order
- **Duplicate Prevention**: Automatically combines items with the same variation instead of creating duplicates

### ⏱️ Time Tracking & Billing
- **Automatic Time Recording**: Records time-in when order is created
- **Time-Based Charging**: Calculates additional charges based on stay duration
  - Php 50 for every 30 minutes stayed
  - Automatically calculated at checkout
- **Duration Display**: Shows hours and minutes spent in the café

### 🧾 Checkout & Receipt
- **Professional Receipt**: Generates formatted receipt with:
  - Café branding and header
  - Time in/out and duration
  - Itemized list with quantities and prices
  - Subtotal calculation
  - Time-based charges (if applicable)
  - Total amount
- **Order Reset**: Automatically creates new order after checkout for next customer

### 🎨 User Interface
- **Color-Coded Display**: ANSI color codes for better readability
- **ASCII Art Banner**: Welcome screen with café name
- **Formatted Menus**: Clear, organized display of options and information
- **User-Friendly Navigation**: Intuitive menu system with numbered options

### ✅ Input Validation & Error Handling
- **Choice Validation**: Prevents invalid menu selections
- **Quantity Validation**: Ensures positive quantities
- **Item Not Found Handling**: Graceful handling of missing items
- **Empty Order Protection**: Prevents operations on empty orders

---

## OOP Concepts Applied

### 1️⃣ Inheritance

The project implements a hierarchical class structure with an abstract `MenuItem` class serving as the parent for `Drink`, `MainCourse`, and `Pastry`.

```java
abstract class MenuItem {
    protected String name;
    protected double price;
    protected String variation;
    
    public abstract void selectVariation(Scanner sc);
}

class Drink extends MenuItem {
    private String size;
    
    public Drink(String name, double basePrice) {
        super(name, basePrice);
    }
    
    @Override
    public void selectVariation(Scanner sc) {
        // Drink-specific variation selection
    }
}

class MainCourse extends MenuItem {
    public MainCourse(String name, double price, int choice) {
        super(name, price);
    }
    
    @Override
    public void selectVariation(Scanner sc) {
        // MainCourse-specific variation selection
    }
}

class Pastry extends MenuItem {
    public Pastry(String name, double price) {
        super(name, price);
    }
    
    @Override
    public void selectVariation(Scanner sc) {
        // Pastry-specific variation selection
    }
}
```

### 2️⃣ Polymorphism

Method overriding demonstrates polymorphism - all menu item types provide their own implementations of `selectVariation()`.

```java
// Polymorphic behavior
MenuItem item1 = new Drink("Coffee", 60);
MenuItem item2 = new MainCourse("Pasta", 60, 1);
MenuItem item3 = new Pastry("Cake", 85);

// Same method call, different implementations
item1.selectVariation(sc); // Calls Drink's implementation
item2.selectVariation(sc); // Calls MainCourse's implementation
item3.selectVariation(sc); // Calls Pastry's implementation
```

### 3️⃣ Encapsulation

Private and protected access modifiers encapsulate data with controlled access through getter methods.

```java
class Order {
    private List<OrderItem> items;
    private LocalDateTime timeIn;
    private LocalDateTime timeOut;
    
    public void addItem(MenuItem item, int quantity) {
        // Validation and controlled access
        for (OrderItem oi : items) {
            if (oi.getItem().getDisplayName().equals(item.getDisplayName())) {
                oi.addQuantity(quantity);
                return;
            }
        }
        items.add(new OrderItem(item, quantity));
    }
    
    public double getSubtotal() {
        return items.stream().mapToDouble(OrderItem::getTotalPrice).sum();
    }
}

class OrderItem {
    private MenuItem item;
    private int quantity;
    
    public MenuItem getItem() { return item; }
    public int getQuantity() { return quantity; }
    public double getTotalPrice() { 
        return item.getPrice() * quantity; 
    }
}
```

### 4️⃣ Abstraction

Abstract classes hide implementation details while providing a common interface for all menu items.

```java
abstract class MenuItem {
    protected String name;
    protected double price;
    protected String variation;
    
    // Abstract method - must be implemented by subclasses
    public abstract void selectVariation(Scanner sc);
    
    // Concrete method - shared by all subclasses
    public String getDisplayName() {
        return variation != null ? name + " (" + variation + ")" : name;
    }
}
```

### Additional OOP Concepts

- **Composition**: `Order` contains `OrderItem` objects, and `OrderItem` contains `MenuItem` objects
- **Single Responsibility Principle**: Each class has one clear purpose
- **Open/Closed Principle**: Easy to extend with new item types without modifying existing code

---

## Program Structure

### Main Classes and Their Roles

| Class | Role |
|-------|------|
| `CafeOrderingSystem` | Entry point containing the main method that starts the application |
| `MenuItem` (abstract) | Base class for all menu items with abstract `selectVariation()` method |
| `Drink` | Handles drink items with size and flavor variations |
| `MainCourse` | Handles main course items with type variations |
| `Pastry` | Handles pastry items with flavor variations |
| `Order` | Manages customer orders, checkout, time tracking, and receipt generation |
| `OrderItem` | Represents a single item in an order with quantity |

### Project Architecture

```
finaloop/
├── CafeOrderingSystem.java    # Main application entry point
├── MenuItem.java              # Abstract base class for menu items
├── Drink.java                 # Drink item implementation
├── MainCourse.java            # Main course item implementation
├── Pastry.java                # Pastry item implementation
├── Order.java                 # Order management class
├── OrderItem.java             # Order line item class
│
├── README.md                  # Project documentation (this file)
```

### Class Hierarchy

```
CafeOrderingSystem (Main Application)
    │
    ├── Order (Order Management)
    │     │
    │     └── OrderItem[] (Order Line Items)
    │           │
    │           └── MenuItem (Abstract Base Class)
    │                 │
    │                 ├── Drink (Concrete Subclass)
    │                 ├── MainCourse (Concrete Subclass)
    │                 └── Pastry (Concrete Subclass)
```

---

## 📚 Usage Guide

### Main Menu Options

```
Kiosk:
1. View Menu              - Display all available items
2. Add Item to Order      - Add items to your order
3. View Current Order     - View and manage your order
4. Checkout               - Complete order and generate receipt
5. Exit                   - Exit the application
```

### Step-by-Step Workflow

#### 1. View Menu
- Select option **1** to see all available items organized by category
- View prices and available variations for each item

#### 2. Add Items to Order
- Select option **2**
- Choose category:
  - **1** - Main Course
  - **2** - Drinks
  - **3** - Pastries
- Select specific item (e.g., Coffee, Pasta, Cake)
- Choose variation:
  - **Drinks**: Select size (Small/Medium/Large) → Select flavor
  - **Main Course**: Select type (e.g., Spaghetti/Carbonara)
  - **Pastries**: Select flavor
- Enter quantity
- Choose to add more items (Y/N)

#### 3. View and Manage Order
- Select option **3** to view current order
- If order is not empty, choose action:
  - **1** - Edit an Item: Enter item name and new quantity
  - **2** - Delete an Item: Enter item name to remove
  - **3** - Back: Return to main menu

#### 4. Checkout
- Select option **4** to complete the order
- View receipt with:
  - Time in/out and duration
  - Itemized list
  - Subtotal
  - Time-based charges (if applicable)
  - Total amount
- System automatically resets for next customer

#### 5. Exit
- Select option **5** to exit the application

### Sample Workflow

1. **Start Application** → Welcome screen displayed
2. **View Menu** → Option 1 → Browse available items
3. **Add Coffee** → Option 2 → Category 2 (Drinks) → Coffee → Medium → Ice Caramel → Quantity: 2
4. **Add Pasta** → Option 2 → Category 1 (Main Course) → Pasta → Spaghetti → Quantity: 1
5. **View Order** → Option 3 → Review items and prices
6. **Edit Quantity** → Option 3 → Option 1 → Edit Coffee quantity to 3
7. **Checkout** → Option 4 → View receipt and total
8. **Exit** → Option 5

---

## Sample Output

### Welcome Screen

```
====================================================
        *****     *****     ******    ******      
       *         *     *    *         *           
       *         *******    ******    ******      
       *         *     *    *         *           
        *****    *     *    *         ******      

              Welcome to Café Java
                 BY: JavaBrew
              Time In: 02:30 PM
====================================================

Kiosk:
1. View Menu
2. Add Item to Order
3. View Current Order
4. Checkout
5. Exit
Enter your choice: 
```

### Menu Display

```
=============================== Menu =================================

-------------------------------DRINKS---------------------------------
Milktea    (Salted Caramel, Okinawa, WinterMelon, Matcha)    - Php 55
Coffee     (Ice Caramel, Espresso, Americano, Latte)         - Php 60
Fruit Soda (Lemon, Strawberry, Lychee, Green Apple)          - Php 45

------------------------------PASTRIES--------------------------------
Cake       (Chocolate, Red Velvet, Carrot)                   - Php 85
Donut      (Caramel, Chocolate Sprinkles, Red Velvet)        - Php 45
Cupcake    (Chocolate, Red Velvet, Carrot)                   - Php 50

----------------------------MAIN COURSE------------------------------
Pasta      (Spaghetti, Carbonara)                            - Php 60
Burger     (Plain)                                           - Php 75
Fries      (Cheese, Sour Cream)                              - Php 50

=====================================================================
```

### Order Display

```
================= Current Order =================
2     Coffee (Medium Ice Caramel)        Php 150.00
1     Pasta (Spaghetti)                   Php 60.00
3     Cake (Chocolate)                    Php 255.00
==================================================

What would you like to do?
1. Edit an Item
2. Delete an Item
3. Back
Enter your choice: 
```

### Receipt Sample

```
Time Out: 03:45 PM
Time Stayed: 1 hour 15 minutes
Applying time-based charges...
(Add every after 30 minutes) Extra Charge for long stay: ₱100

==================================================
               C A F É  J A V A           
                O F F I C I A L           
                 R E C E I P T            
===================================================
Time In: 02:30 PM
Time Out: 03:45 PM
Duration: 1h 15m
---------------------------------------------------
Qty  Item                               Price
---------------------------------------------------
2    Coffee (Medium Ice Caramel)      Php 150.00
1    Pasta (Spaghetti)                 Php 60.00
3    Cake (Chocolate)                  Php 255.00
----------------------------------------------------
Subtotal:                              Php 465.00
Time-Based Charge:                     Php 100
----------------------------------------------------
TOTAL AMOUNT:                          Php 565.00
====================================================
            THANK YOU FOR DINING WITH US!        
                PLEASE COME AGAIN :)            
====================================================

Ready for the next customer!
```

---

## 👥 Authors

**Development Team**

- **CRUZAT, Emanuel C.**
- **DEOMAMPO, Crissele**
- **MORTEL, Nathaniel**

This project was developed as part of a final Object-Oriented Programming course project.

---

## 📄 License

This project is created for educational purposes as part of an Object-Oriented Programming course.

---

## 📞 Contact

For questions or collaboration:

- **GitHub**: JavaBrew
- **Email**: emanuelcruzat@gmail.com, crisseledeomampo@gmail.com, nathanielmortel15@gmail.com

---

⭐ **If you find this project helpful, please consider giving it a star!**

**Made with ☕ by JavaBrew Development Team**
