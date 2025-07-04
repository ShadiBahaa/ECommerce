# E-Commerce System

A comprehensive Java-based e-commerce system that handles product management, shopping cart functionality, and checkout processing with support for both shippable and non-shippable products.

## Features

- **Product Management**: Support for both perishable and non-perishable products
- **Shopping Cart**: Add items, calculate totals, and manage cart contents
- **Shipping Calculation**: Automatic weight-based shipping fee calculation
- **Inventory Management**: Real-time stock tracking and availability checking
- **Customer Balance**: Customer account management with balance tracking
- **Checkout Process**: Complete order processing with validation

## Project Structure

```
src/
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
- Products with expiry dates (e.g., food items)
- Automatically become unavailable after expiry
- Require shipping
- Have weight for shipping calculations

### 2. Non-Perishable Shippable Products
- Physical products that don't expire (e.g., electronics)
- Require shipping
- Have weight for shipping calculations

### 3. Non-Perishable Non-Shippable Products
- Digital or instant products (e.g., gift cards, digital downloads)
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

## Sample Output

```
=== CHECKOUT PROCESS ===
** Shipment notice **
2x Cheese 200.0g
1x TV 15000.0g
Total package weight 15.4kg
** Checkout receipt **
2x Cheese 200
1x Biscuits 150
1x TV 500
1x Mobile Scratch Card 25
----------------------
Subtotal 875
Shipping 154
Amount 1029
Customer balance after payment: $96.0
END.
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

## Design Patterns Used

- **Abstract Factory Pattern**: Product hierarchy with abstract Product class
- **Strategy Pattern**: Different shipping behaviors for product types
- **Interface Segregation**: Shippable interface for shipping-specific functionality

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

## License

This project is available under the MIT License.
