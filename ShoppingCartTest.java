package thirdproject;



import org.junit.Test;



import static org.junit.Assert.assertEquals;



import java.util.List;



public class ShoppingCartTest {



 @Test

 public void testGetTotalAmount() {

 // Load sample products

 List<Product> products = ProductLoader.loadProducts();



 // Create a shopping cart

 ShoppingCart cart = new ShoppingCart();



 // Add some products to the cart

 cart.addItem(products.get(0)); // Laptop

 cart.addItem(products.get(1)); // Smartwatch

 cart.addItem(products.get(5)); // T-Shirt

 cart.addItem(products.get(8)); // Dress



 // Calculate the expected total amount

 double expectedTotalAmount = products.get(0).getPrice() + products.get(1).getPrice()

 + products.get(5).getPrice() + products.get(8).getPrice();



 // Check if the total amount matches the expected value (with a small delta for precision issues)

 assertEquals(expectedTotalAmount, cart.getTotalAmount(), 0.01);

 }



 @Test

 public void testAddItem() {

 // Load sample products

 List<Product> products = ProductLoader.loadProducts();



 // Create a shopping cart

 ShoppingCart cart = new ShoppingCart();



 // Add a product to the cart

 cart.addItem(products.get(2)); // Headphones



 // Check if the cart contains the added product

 assertEquals(1, cart.getItems().size());

 assertEquals(products.get(2), cart.getItems().get(0));

 }



 @Test

 public void testRemoveItem() {

 // Load sample products

 List<Product> products = ProductLoader.loadProducts();



 // Create a shopping cart

 ShoppingCart cart = new ShoppingCart();



 // Add a product to the cart

 Product product = products.get(3); // Smartphone

 cart.addItem(product);



 // Remove the product from the cart

 cart.removeItem(product);



 // Check if the cart is empty after removing the product

 assertEquals(0, cart.getItems().size());

 }



 // Add more test cases as needed

}

