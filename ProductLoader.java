package thirdproject;



import java.util.ArrayList;

import java.util.List;



public class ProductLoader {

 public static List<Product> loadProducts() {

 List<Product> products = new ArrayList<>();



 // Add some sample products (you can modify or extend this list)

 ElectronicProduct laptop = new ElectronicProduct("Laptop", 1200.0, "High-performance laptop", 10);

 ElectronicProduct smartwatch = new ElectronicProduct("Smartwatch", 199.99, "Fitness and health tracker", 15);

 ElectronicProduct headphones = new ElectronicProduct("Headphones", 99.99, "Wireless over-ear headphones", 20);

 ElectronicProduct smartphone = new ElectronicProduct("Smartphone", 799.99, "Flagship smartphone with advanced features", 12);

 ElectronicProduct tablet = new ElectronicProduct("Tablet", 499.99, "10-inch tablet with high-resolution display", 8);

 ClothingProduct tShirt = new ClothingProduct("T-Shirt", 25.0, "Comfortable cotton T-shirt", 50);

 ClothingProduct jeans = new ClothingProduct("Jeans", 39.99, "Slim-fit blue jeans", 30);

 ClothingProduct sneakers = new ClothingProduct("Sneakers", 59.99, "Casual sneakers for everyday wear", 25);

 ClothingProduct dress = new ClothingProduct("Dress", 79.99, "Elegant evening dress", 15);

 ClothingProduct hoodie = new ClothingProduct("Hoodie", 29.99, "Comfortable fleece hoodie", 40);



 products.add(laptop);

 products.add(smartwatch);

 products.add(headphones);

 products.add(smartphone);

 products.add(tablet);

 products.add(tShirt);

 products.add(jeans);

 products.add(sneakers);

 products.add(dress);

 products.add(hoodie);



 return products;

 }

}

