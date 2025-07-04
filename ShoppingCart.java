import java.util.*;
class ShoppingCart {
    private List<CartItem> items;
    
    public ShoppingCart() {
        this.items = new ArrayList<>();
    }
    
    public void add(Product product, int quantity) {
        if (product.isAvailable(quantity)) {
            items.add(new CartItem(product, quantity));
            System.out.println("Added " + quantity + "x " + product.getName() + " to cart");
        } else {
            System.out.println("Cannot add " + product.getName() + " - insufficient stock or expired");
        }
    }
    
    public List<CartItem> getItems() { return items; }
    
    public boolean isEmpty() { return items.isEmpty(); }
    
    public double getSubtotal() {
        return items.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }
}
