# E-Commerce System

A basic Java-based e-commerce system that handles product management, shopping cart functionality, and checkout processing with support for both shippable and non-shippable products.

## Features

- **Product Management**: Support for both perishable and non-perishable products
- **Shopping Cart**: Add items, calculate totals, and manage cart contents
- **Shipping Calculation**: Automatic weight-based shipping fee calculation
- **Inventory Management**: Real-time stock tracking and availability checking
- **Customer Balance**: Customer account management with balance tracking
- **Checkout Process**: Complete order processing with validation

## Project Structure

```
├── Product.java                           # Abstract base class for all products
├── PerishableProduct.java                 # Products with expiry dates
├── NonPerishableShippableProduct.java     # Non-perishable items requiring shipping
├── NonPerishableNonShippableProduct.java  # Digital/instant products
├── Shippable.java                         # Interface for shippable items
├── Customer.java                          # Customer account management
├── CartItem.java                          # Individual cart item representation
├── ShoppingCart.java                      # Shopping cart functionality
├── ShippingService.java                   # Shipping fee calculation
├── ECommerceSystem.java                   # Main checkout processing
└── ECommerceApp.java                      # Demo application with test cases
```

## Product Types

### 1. Perishable Products
- Products with expiry dates
- Automatically become unavailable after expiry
- Require shipping
- Have weight for shipping calculations

### 2. Non-Perishable Shippable Products
- Physical products that don't expire 
- Require shipping
- Have weight for shipping calculations

### 3. Non-Perishable Non-Shippable Products
- Digital or instant products
- No shipping required
- No weight considerations

## Key Components

### Shopping Cart
- Add products with quantity validation
- Calculate subtotals automatically
- Handle empty cart scenarios

### Shipping Service
- Weight-based shipping calculation ($10 per kg)
- Detailed shipment notices
- Support for multiple items of the same product

### Checkout System
- Comprehensive validation:
  - Cart not empty
  - Products available and not expired
  - Sufficient customer balance
- Real-time inventory updates
- Detailed receipt generation

## Usage Example

```java
// Create products
PerishableProduct cheese = new PerishableProduct("Cheese", 100, 10, 
    LocalDate.now().plusDays(7), 0.2);
NonPerishableShippableProduct tv = new NonPerishableShippableProduct("TV", 500, 3, 15.0);
NonPerishableNonShippableProduct scratchCard = new NonPerishableNonShippableProduct("Mobile Scratch Card", 25, 100);

// Create customer
Customer customer = new Customer("John Doe", 1000);

// Create shopping cart and add items
ShoppingCart cart = new ShoppingCart();
cart.add(cheese, 2);
cart.add(tv, 1);
cart.add(scratchCard, 1);

// Process checkout
ECommerceSystem.checkout(customer, cart);
```

## Test Cases

The application includes comprehensive test cases covering:

1. **Successful Checkout**: Mixed product types with valid inventory and balance
2. **Empty Cart**: Handling empty cart scenarios
3. **Insufficient Balance**: Customer balance validation
4. **Out of Stock**: Inventory availability checking
5. **Expired Products**: Expiry date validation
6. **Non-shippable Items**: Checkout without shipping fees

## Sample Output for test cases

```
=== TEST CASE 1: Successful Checkout ===
Added 2x Cheese to cart
Added 1x Biscuits to cart
Added 1x TV to cart
Added 1x Mobile Scratch Card to cart

=== CHECKOUT PROCESS ===
** Shipment notice **
1x TV 15000.0g
1x Biscuits 700.0g
2x Cheese 200.0g
Total package weight 16.1kg
ERROR: Customer's balance is insufficient
Required: $1036.0, Available: $1000.0

=== TEST CASE 2: Empty Cart ===

=== CHECKOUT PROCESS ===
ERROR: Cart is empty

=== TEST CASE 3: Insufficient Balance ===
Added 2x TV to cart

=== CHECKOUT PROCESS ===
** Shipment notice **
2x TV 15000.0g
Total package weight 30.0kg
ERROR: Customer's balance is insufficient
Required: $1300.0, Available: $100.0

=== TEST CASE 4: Out of Stock ===
Cannot add TV - insufficient stock or expired

=== CHECKOUT PROCESS ===
ERROR: Cart is empty

=== TEST CASE 5: Expired Product ===
Cannot add Milk - insufficient stock or expired

=== CHECKOUT PROCESS ===
ERROR: Cart is empty

=== TEST CASE 6: Non-shippable Items Only ===
Added 5x Mobile Scratch Card to cart

=== CHECKOUT PROCESS ===
** Checkout receipt **
5x Mobile Scratch Card 125
----------------------
Subtotal 125
Shipping 0
Amount 125
Customer balance after payment: $875.0
END.

=== FINAL INVENTORY STATUS ===
Cheese remaining: 10
Biscuits remaining: 5
TV remaining: 3
Scratch Cards remaining: 95
Customer final balance: $875.0
```

## Requirements

- Java 8 or higher (uses LocalDate and Streams)
- No external dependencies required

## Running the Application

1. Compile all Java files:
   ```bash
   javac *.java
   ```

2. Run the demo application:
   ```bash
   java ECommerceApp
   ```

## Error Handling

The system handles various error scenarios:
- Empty shopping cart
- Insufficient customer balance
- Out of stock products
- Expired products
- Invalid quantities

## Future Enhancements

- Database integration for persistent storage
- User authentication and authorization
- Multiple payment methods
- Discount and coupon system
- Order history tracking
- Advanced shipping options (express, overnight, etc.)
