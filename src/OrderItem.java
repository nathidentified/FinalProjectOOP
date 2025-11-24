class OrderItem {
    private MenuItem item;
    private int quantity;

    public OrderItem(MenuItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public MenuItem getItem() { return item; }
    public int getQuantity() { return quantity; }
    public void addQuantity(int qty) { this.quantity += qty; }
    public double getTotalPrice() { return item.getPrice() * quantity; }
}