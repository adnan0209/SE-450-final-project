package thirdproject;
// This line declares the package "thirdproject" for the current Java file.

// Concrete product class for electronic products
// This class represents a concrete implementation of the Product interface for clothing products.

public class ClothingProduct implements Product {
// Declaration of the ClothingProduct class implementing the Product interface.

    private String name;
    private double price;
    private String description;
    private int quantityInStock;
    // Declaration of private instance variables to store information about the clothing product.

    // Constructor and other methods

    public ClothingProduct(String name, double price, String description, int quantityInStock) {
    // Constructor for creating an instance of the ClothingProduct class.

        this.name = name;
        this.price = price;
        this.description = description;
        this.quantityInStock = quantityInStock;
        // Initializing instance variables with provided values.

    }

    @Override
    // Annotation indicating that the following method overrides a method in the Product interface.

    public String getName() {
    // Implementation of the getName method from the Product interface.

        return name;
        // Returning the name of the clothing product.

    }

    @Override
    // Annotation indicating that the following method overrides a method in the Product interface.

    public double getPrice() {
    // Implementation of the getPrice method from the Product interface.

        return price;
        // Returning the price of the clothing product.

    }

    @Override
    // Annotation indicating that the following method overrides a method in the Product interface.

    public String getDescription() {
    // Implementation of the getDescription method from the Product interface.

        return description;
        // Returning the description of the clothing product.

    }

    @Override
    // Annotation indicating that the following method overrides a method in the Product interface.

    public int getQuantityInStock() {
    // Implementation of the getQuantityInStock method from the Product interface.

        return quantityInStock;
        // Returning the quantity in stock of the clothing product.

    }

    // toString method to provide a string representation of the ClothingProduct instance.
    public String toString() {
        return "ClothingProduct{" +
                "name='" + getName() + '\'' +
                ", price=" + getPrice() +
                ", description='" + getDescription() + '\'' +
                ", quantityInStock=" + getQuantityInStock() +
                '}';
    }

}
