package com.example.ECommerce;
import java.util.*;
class ECommerceSystem {
    
    public static void checkout(Customer customer, ShoppingCart cart) {
        System.out.println("\n=== CHECKOUT PROCESS ===");
        
        // Check if cart is empty
        if (cart.isEmpty()) {
            System.out.println("ERROR: Cart is empty");
            return;
        }
        
        // Check product availability and expiry
        List<Shippable> shippableItems = new ArrayList<>();
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            
            if (!product.isAvailable(item.getQuantity())) {
                if (product.isExpired()) {
                    System.out.println("ERROR: Product " + product.getName() + " is expired");
                } else {
                    System.out.println("ERROR: Product " + product.getName() + " is out of stock");
                }
                return;
            }
            
            // Collect shippable items
            if (product.requiresShipping()) {
                for (int i = 0; i < item.getQuantity(); i++) {
                    shippableItems.add((Shippable) product);
                }
            }
        }
        
        // Calculate totals
        double subtotal = cart.getSubtotal();
        double shippingFee = ShippingService.calculateShippingFee(shippableItems);
        double totalAmount = subtotal + shippingFee;
        
        // Check customer balance
        if (customer.getBalance() < totalAmount) {
            System.out.println("ERROR: Customer's balance is insufficient");
            System.out.println("Required: $" + totalAmount + ", Available: $" + customer.getBalance());
            return;
        }
        
        // Process payment and update inventory
        customer.deductBalance(totalAmount);
        for (CartItem item : cart.getItems()) {
            item.getProduct().reduceQuantity(item.getQuantity());
        }
        
        // Print checkout receipt
        System.out.println("** Checkout receipt **");
        for (CartItem item : cart.getItems()) {
            System.out.println(item.getQuantity() + "x " + item.getProduct().getName() + " " + (int)item.getTotalPrice());
        }
        System.out.println("----------------------");
        System.out.println("Subtotal " + (int)subtotal);
        System.out.println("Shipping " + (int)shippingFee);
        System.out.println("Amount " + (int)totalAmount);
        System.out.println("Customer balance after payment: $" + customer.getBalance());
        System.out.println("END.");
    }
}