import java.util.*;
class ShippingService {
    private static final double SHIPPING_RATE_PER_KG = 10.0; // $10 per kg
    
    public static double calculateShippingFee(List<Shippable> shippableItems) {
        if (shippableItems.isEmpty()) {
            return 0.0;
        }
        
        double totalWeight = 0.0;
        System.out.println("** Shipment notice **");
        
        Map<String, Integer> itemCounts = new HashMap<>();
        Map<String, Double> itemWeights = new HashMap<>();
        
        // Group items by name and calculate total quantities and weights
        for (Shippable item : shippableItems) {
            String name = item.getName();
            itemCounts.put(name, itemCounts.getOrDefault(name, 0) + 1);
            itemWeights.put(name, item.getWeight());
            totalWeight += item.getWeight();
        }
        
        // Print shipment details
        for (Map.Entry<String, Integer> entry : itemCounts.entrySet()) {
            String name = entry.getKey();
            int count = entry.getValue();
            double weight = itemWeights.get(name);
            System.out.println(count + "x " + name + " " + (weight * 1000) + "g");
        }
        
        System.out.println("Total package weight " + totalWeight + "kg");
        
        return totalWeight * SHIPPING_RATE_PER_KG;
    }
}
