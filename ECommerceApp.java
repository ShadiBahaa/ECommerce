import java.time.LocalDate;

public class ECommerceApp {
    public static void main(String[] args) {
        // Create products
        PerishableProduct cheese = new PerishableProduct("Cheese", 100, 10, LocalDate.now().plusDays(7), 0.2);
        PerishableProduct biscuits = new PerishableProduct("Biscuits", 150, 5, LocalDate.now().plusDays(30), 0.7);
        NonPerishableShippableProduct tv = new NonPerishableShippableProduct("TV", 500, 3, 15.0);
        NonPerishableNonShippableProduct scratchCard = new NonPerishableNonShippableProduct("Mobile Scratch Card", 25, 100);
        
        // Create customer
        Customer customer = new Customer("John Doe", 1000);
        
        // Test Case 1: Successful checkout with mixed products
        System.out.println("=== TEST CASE 1: Successful Checkout ===");
        ShoppingCart cart1 = new ShoppingCart();
        cart1.add(cheese, 2);
        cart1.add(biscuits, 1);
        cart1.add(tv, 1);
        cart1.add(scratchCard, 1);
        
        ECommerceSystem.checkout(customer, cart1);
        
        // Test Case 2: Empty cart
        System.out.println("\n=== TEST CASE 2: Empty Cart ===");
        ShoppingCart cart2 = new ShoppingCart();
        ECommerceSystem.checkout(customer, cart2);
        
        // Test Case 3: Insufficient balance
        System.out.println("\n=== TEST CASE 3: Insufficient Balance ===");
        Customer poorCustomer = new Customer("Jane Smith", 100);
        ShoppingCart cart3 = new ShoppingCart();
        cart3.add(tv, 2);
        ECommerceSystem.checkout(poorCustomer, cart3);
        
        // Test Case 4: Out of stock
        System.out.println("\n=== TEST CASE 4: Out of Stock ===");
        ShoppingCart cart4 = new ShoppingCart();
        cart4.add(tv, 5); // Only 2 TVs left after previous purchase
        ECommerceSystem.checkout(customer, cart4);
        
        // Test Case 5: Expired product
        System.out.println("\n=== TEST CASE 5: Expired Product ===");
        PerishableProduct expiredMilk = new PerishableProduct("Milk", 50, 10, LocalDate.now().minusDays(1), 1.0);
        ShoppingCart cart5 = new ShoppingCart();
        cart5.add(expiredMilk, 1);
        ECommerceSystem.checkout(customer, cart5);
        
        // Test Case 6: Non-shippable items only
        System.out.println("\n=== TEST CASE 6: Non-shippable Items Only ===");
        ShoppingCart cart6 = new ShoppingCart();
        cart6.add(scratchCard, 5);
        ECommerceSystem.checkout(customer, cart6);
        
        System.out.println("\n=== FINAL INVENTORY STATUS ===");
        System.out.println("Cheese remaining: " + cheese.getQuantity());
        System.out.println("Biscuits remaining: " + biscuits.getQuantity());
        System.out.println("TV remaining: " + tv.getQuantity());
        System.out.println("Scratch Cards remaining: " + scratchCard.getQuantity());
        System.out.println("Customer final balance: $" + customer.getBalance());
    }
}
