package thirdproject;
// This line declares the package "thirdproject" for the current Java file.

import java.util.ArrayList;
// Importing the ArrayList class from the java.util package.

import java.util.List;
// Importing the List interface from the java.util package.

public class Catalog {
// Declaration of a class named "Catalog".

    private List<Product> products = new ArrayList<>();
    // Declaration of a private field "products" of type List<Product> initialized with an ArrayList.

    public void addProduct(Product product) {
    // Declaration of a public method "addProduct" that takes a parameter of type Product.

        products.add(product);
        // Adding the provided product to the list of products.

    }

    // Assuming the SKU is the product name, replace with getSKU() if you have SKU as a property
    public Product getProductByName(String name) {
    // Declaration of a public method "getProductByName" that takes a parameter of type String.

        for (Product product : products) {
        // Starting a for-each loop to iterate over the products in the list.

            if (product.getName().equals(name)) {
            // Checking if the name of the current product matches the provided name.

                return product;
                // Returning the product if a match is found.

            }

        }

        return null;
        // Returning null if no matching product is found.

    }

    public List<Product> getAllProducts() {
    // Declaration of a public method "getAllProducts" that returns a List<Product>.

        return products;
        // Returning the list of all products.

    }

    public void addProducts(List<Product> newProducts) {
    // Declaration of a public method "addProducts" that takes a parameter of type List<Product>.

        products.addAll(newProducts);
        // Adding all the products from the provided list to the existing list of products.

    }

}
